package dao.db;

import java.util.List;

public class DeveloperDaoImpl implements DeveloperDao {

    private static String createDb = "CREATE TABLE IF NOT EXISTS DEVELOPER(" +
            "developerId INTEGER PRIMARY KEY," +
            "developerName TEXT NOT NULL);";

    private static String selectAll = "SELECT * FROM DEVELOPER";

    public DeveloperDaoImpl() {
        JdbcConfig.run(createDb);
        System.out.println("Developers data created");
    }

    @Override
    public void add(Developer developer) {
        String insertData = "INSERT INTO DEVELOPER VALUES " +
                "(" + developer.getDeveloperId() + ", '" + developer.getDeveloperName() + "')";
        JdbcConfig.run(insertData);
        System.out.println("Data added");
    }

    @Override
    public List<Developer> findAll() {
        return JdbcConfig.select(selectAll, -1);
    }

    @Override
    public Developer findById(int developerId) {

        List<Developer> developers = JdbcConfig.select(selectAll, developerId);

        if (developers.size() != 0) {
            System.out.println("Developer: Id " + developerId + ", found");
            return developers.get(0);
        } else {
            System.out.println("Developer: Id " + developerId + ", not found");
            return null;
        }

    }

    @Override
    public void update(Developer developer) {
        String updateData = "UPDATE developer " +
                "SET developerName = '" + developer.getDeveloperName() + "'" +
                " WHERE developerId = " + developer.getDeveloperId();
        JdbcConfig.run(updateData);
        System.out.println("Developer: Id " + developer.getDeveloperId() + ", updated");
    }

    @Override
    public void deleteById(int developerId) {
        String deleteData = "DELETE FROM DEVELOPER " +
                " WHERE developerId = " + developerId;
        JdbcConfig.run(deleteData);
        System.out.println("Developer: Id " + developerId + ", deleted");
    }

}