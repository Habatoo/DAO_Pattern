package dao.db;

import java.util.List;

public interface DeveloperDao {

    void create();
    List<Developer> getAll();
    Developer get(int developerId);
    void update(Developer developer);
    void delete(int developerId);

}
