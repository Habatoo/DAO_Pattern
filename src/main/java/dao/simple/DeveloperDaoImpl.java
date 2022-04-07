package dao.simple;

import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoImpl implements DeveloperDao {

    // list is working as a database
    List<Developer> developers;

    public DeveloperDaoImpl() {}

    @Override
    public void create() {
        developers = new ArrayList<>();
        Developer developer_1 = new Developer("Ada", 0);
        Developer developer_2 = new Developer("Rob", 1);
        developers.add(developer_1);
        developers.add(developer_2);
        System.out.println("Developers data created");
    }

    // retrieve list of Developers
    @Override
    public List<Developer> getAll() {
        return developers;
    }

    @Override
    public Developer get(int developerId) {
        return developers.get(developerId);
    }

    @Override
    public void update(Developer developer) {
        developers.get(developer.getDeveloperId()).setDeveloperName(developer.getDeveloperName());
        System.out.println("Developer: Id " + developer.getDeveloperId() + ", updated");
    }

    @Override
    public void delete(int developerId) {
        developers.remove(developerId);
        System.out.println("Developer: Id " + developerId + ", deleted");
    }

}
