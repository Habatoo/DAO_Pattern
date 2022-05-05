package dao.db;

import java.util.List;

public class DbDeveloperDao implements DeveloperDao {

    private final String CREATE_DB = "CREATE TABLE IF NOT EXISTS DEVELOPER(" +
            "id INTEGER PRIMARY KEY," +
            "name TEXT NOT NULL);";
    private final String SELECT_ALL = "SELECT * FROM DEVELOPER";
    private final String INSERT_DATA = "INSERT INTO DEVELOPER VALUES (%d , '%s')";
    private final String UPDATE_DATA = "UPDATE developer SET name " +
            "= '%s' WHERE id = %d";
    private final String DELETE_DATA = "DELETE FROM DEVELOPER WHERE id = %d";
    private final DbClient dbClient = new DbClient();

    public DbDeveloperDao() {
        dbClient.run(CREATE_DB);
        System.out.println("Developers data structure create");
    }

    @Override
    public void add(Developer developer) {
        dbClient.run(String.format(
                INSERT_DATA, developer.getId(), developer.getName()));
        System.out.println("Developer: Id " + developer.getId() +
                ", name: " + developer.getName() + " added");
    }

    @Override
    public List<Developer> findAll() {
        return dbClient.select(SELECT_ALL, -1);
    }

    @Override
    public Developer findById(int id) {

        List<Developer> developers = dbClient.select(SELECT_ALL, id);

        if (developers.size() != 0) {
            System.out.println("Developer: Id " + id + ", found");
            return developers.get(0);
        } else {
            System.out.println("Developer: Id " + id + ", not found");
            return null;
        }

    }

    @Override
    public void update(Developer developer) {
        dbClient.run(String.format(
                UPDATE_DATA, developer.getName(), developer.getId()));
        System.out.println("Developer: Id " + developer.getId() + ", updated");
    }

    @Override
    public void deleteById(int id) {
        dbClient.run(String.format(DELETE_DATA, id));
        System.out.println("Developer: Id " + id + ", deleted");
    }

}