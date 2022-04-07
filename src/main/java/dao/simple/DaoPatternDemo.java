package dao.simple;

public class DaoPatternDemo {
    public static void main(String[] args) {
        DeveloperDao developerDao = new DeveloperDaoImpl();

        developerDao.create(); // Developers data created

        // print all developers
        for (Developer developer : developerDao.getAll()) {
            System.out.println("Developer: [Id : " + developer.getDeveloperId() +  // Developer: [Id : 0, Name : Ada ]
                    ", Name : " + developer.getDeveloperName() + " ]");            // Developer: [Id : 1, Name : Rob ]
        }

        // update developer data
        Developer developer = developerDao.getAll().get(0);
        developer.setDeveloperName("Adelaida");
        developerDao.update(developer); // Developer: Id 0, updated

        //get the developer
        developerDao.get(0);
        System.out.println("Developer: [Id : " + developer.getDeveloperId() +
                ", Name : " + developer.getDeveloperName() + " ]"); // Developer: [Id : 0, Name : Adelaida ]

        //delete the developer
        developerDao.delete(developer.getDeveloperId());
        System.out.println("Developer: [Id : " + developer.getDeveloperId() +
                ", Name : " + developer.getDeveloperName() + " ]"); // Developer: Id 0, deleted
                                                                    // Developer: [Id : 0, Name : Adelaida ]
    }
}
