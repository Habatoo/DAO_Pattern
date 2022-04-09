package dao.db;

public class DaoPatternDbDemo {

    public static void main(String[] args) {

        DeveloperDao developerDao = new DeveloperDaoImpl(); // Developers data created

        // add the data
        developerDao.add(new Developer("Ada", 0)); // Developer: Id 0, name: Ada added
        developerDao.add(new Developer("Rob", 1)); // Developer: Id 1, name: Rob added

        // print all developers
        for (Developer developer : developerDao.findAll()) {
            System.out.println("Developer: [Id " + developer.getDeveloperId() +  // Developer: [Id 0, Name : Ada ]
                    ", Name : " + developer.getDeveloperName() + " ]");          // Developer: [Id 1, Name : Rob ]
        }

        // find developer by id
        developerDao.findById(0);  // Developer: Id 0, found
        developerDao.findById(10); // Developer: Id 10, not found

        // update developer data
        Developer developer = developerDao.findById(0);
        developer.setDeveloperName("Adelaida");
        developerDao.update(developer); // Developer: Id 0, updated

        //delete the developer
        developerDao.deleteById(0); // Developer: Id 0, deleted
        developerDao.findById(0); // Developer: Id 0, not found

    }

}
