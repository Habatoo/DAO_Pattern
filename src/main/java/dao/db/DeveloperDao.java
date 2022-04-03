package dao.db;

import java.util.List;

public interface DeveloperDao {

    List<Developer> getAll();
    Developer get(int developerId);
    void update(Developer developer);
    void delete(Developer developer);

}
