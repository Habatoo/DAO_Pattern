package dao.db;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbClient {

    private final String CONNECTION_URL = "jdbc:sqlite:developer.db";
    private final SQLiteDataSource DATASOURCE = new SQLiteDataSource();

    public DbClient() {
        DATASOURCE.setUrl(CONNECTION_URL);
    }

    public void run(String str) {

        try (Connection con = DATASOURCE.getConnection(); // Statement creation
             Statement statement = con.createStatement()
        ) {
            statement.executeUpdate(str); // Statement execution
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Developer> select(String str, Integer index) {

        List<Developer> developersList = new ArrayList<>();

        try (Connection con = DATASOURCE.getConnection();
             Statement statement = con.createStatement();
             ResultSet developers = statement.executeQuery(str)
        ) {
            while (developers.next()) {
                // Retrieve column values
                int id = developers.getInt("id");
                if (index == -1 || index == id) {
                    String name = developers.getString("name");
                    Developer developer = new Developer(id, name);
                    developersList.add(developer);
                }
            }
            return developersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developersList;
    }

}