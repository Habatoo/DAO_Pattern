package dao.simple;

import java.util.List;

public interface DeveloperDao {

    List<Developer> findAll();
    Developer findById(int developerId);
    void add(Developer developer);
    void update(Developer developer);
    void deleteById(int developerId);

}
