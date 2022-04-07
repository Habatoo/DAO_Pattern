package dao.db;

import java.util.List;

public class DeveloperDaoImpl implements DeveloperDao {

    private static String createDb = "CREATE TABLE IF NOT EXISTS DEVELOPER(" +
            "developerId INTEGER PRIMARY KEY," +
            "developerName TEXT NOT NULL);";

    private static String insertData = "INSERT INTO DEVELOPER VALUES " +
            "(0, 'Ada')," +
            "(1, 'Bob')";

    private static String selectAll = "SELECT * FROM DEVELOPER";

    public DeveloperDaoImpl() {}

    @Override
    public void create() {
        JdbcConfig.run(createDb);
        JdbcConfig.run(insertData);
        System.out.println("Developers data created");
    }

    // retrieve list of Developers
    @Override
    public List<Developer> getAll() {
        return JdbcConfig.select(selectAll, -1);
    }

    @Override
    public Developer get(int developerId) {
        return JdbcConfig.select(selectAll, developerId).get(0);
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
    public void delete(int developerId) {
        String deleteData = "DELETE FROM DEVELOPER " +
                " WHERE developerId = " + developerId;
        JdbcConfig.run(deleteData);
        System.out.println("Developer: Id " + developerId + ", deleted");
    }

}
