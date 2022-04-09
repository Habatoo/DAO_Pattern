package dao.db;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcConfig {

    private static String connectionString = "jdbc:sqlite:developer.db";
    private static SQLiteDataSource dataSource = new SQLiteDataSource();

    public static void run(String str) {

        dataSource.setUrl(connectionString);

        try (Connection con = dataSource.getConnection(); // Statement creation
             Statement statement = con.createStatement()
        ) {
            statement.executeUpdate(str); // Statement execution
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<Developer> select(String str, Integer index) {

        dataSource.setUrl(connectionString);
        List<Developer> developersList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet developers = statement.executeQuery(str)
        ) {
            while (developers.next()) {
                // Retrieve column values
                int id = developers.getInt("developerId");
                if (index == -1 || index == id) {
                    String name = developers.getString("developerName");
                    Developer developer = new Developer(name, id);
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