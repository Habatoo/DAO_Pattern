package dao.db;

import java.util.List;

public class DbDeveloperDao implements DeveloperDao {

    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS DEVELOPER(" +
            "developerId INTEGER PRIMARY KEY," +
            "developerName TEXT NOT NULL);";

    private static final String SELECT_ALL = "SELECT * FROM DEVELOPER";

    private static final String INSERT_DATA = "INSERT INTO DEVELOPER VALUES (%d , '%s')";

    private static final String UPDATE_DATA = "UPDATE developer SET developerName = '%s' WHERE developerId = %d";

    private static final String DELETE_DATA = "DELETE FROM DEVELOPER WHERE developerId = %d";

    public DbDeveloperDao() {
        DbClient.run(CREATE_DB);
        System.out.println("Developers data structure create");
    }

    @Override
    public void add(Developer developer) {
        DbClient.run(String.format(INSERT_DATA, developer.getDeveloperId(), developer.getDeveloperName()));
        System.out.println("Data added");
    }

    @Override
    public List<Developer> findAll() {
        return DbClient.select(SELECT_ALL, -1);
    }

    @Override
    public Developer findById(int developerId) {

        List<Developer> developers = DbClient.select(SELECT_ALL, developerId);

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
        DbClient.run(String.format(UPDATE_DATA, developer.getDeveloperName(), developer.getDeveloperId()));
        System.out.println("Developer: Id " + developer.getDeveloperId() + ", updated");
    }

    @Override
    public void deleteById(int developerId) {
        DbClient.run(String.format(DELETE_DATA, developerId));
        System.out.println("Developer: Id " + developerId + ", deleted");
    }

}