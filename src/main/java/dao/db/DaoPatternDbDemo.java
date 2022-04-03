package dao.db;


public class DaoPatternDbDemo {

    public static void main(String[] args) {

        DeveloperDao developerDao = new DeveloperDaoImpl();

        // print all developers
        for (Developer developer : developerDao.getAll()) {
            System.out.println("Developer: [Id : " + developer.getDeveloperId() +  // Developer: [Id : 0, Name : Ada ]
                    ", Name : " + developer.getDeveloperName() + " ]");            // Developer: [Id : 1, Name : Rob ]
        }

        System.out.println();
        System.out.println("update developer data");

        // update developer data
        Developer developer = developerDao.getAll().get(0);
        developer.setDeveloperName("Adelaida");
        developerDao.update(developer); // Developer: Id 0, updated

        // print all developers
        for (Developer dev : developerDao.getAll()) {
            System.out.println("Developer: [Id : " + dev.getDeveloperId() +  // Developer: [Id : 0, Name : Adelaida ]
                    ", Name : " + dev.getDeveloperName() + " ]");            // Developer: [Id : 1, Name : Rob ]
        }

        System.out.println();
        System.out.println("get 0");
        //get the developer
        Developer d = developerDao.get(0);
        System.out.println("Developer: [Id : " + d.getDeveloperId() +
                ", Name : " + d.getDeveloperName() + " ]"); // Developer: [Id : 0, Name : Adelaida ]

        System.out.println();
        System.out.println("delete 0");
        developerDao.delete(developer);
        // print all developers
        for (Developer dev1 : developerDao.getAll()) {
            System.out.println("Developer: [Id : " + dev1.getDeveloperId() +  // Developer: [Id : 0, Name : Ada ]
                    ", Name : " + dev1.getDeveloperName() + " ]");            // Developer: [Id : 1, Name : Rob ]
        }

    }

}
