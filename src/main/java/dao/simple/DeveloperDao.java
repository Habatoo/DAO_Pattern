package dao.simple;

import java.util.List;

public interface DeveloperDao {

    public List<Developer> getAll();
    public Developer get(int developerId);
    public void update(Developer developer);
    public void delete(Developer developer);

}
