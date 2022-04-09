package dao.simple;

import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoImpl implements DeveloperDao {

    // list is working as a database
    List<Developer> developers;

    public DeveloperDaoImpl() {
        developers = new ArrayList<>();
        System.out.println("Developers data created");
    }

    @Override
    public List<Developer> findAll() {
        return developers;
    }

    @Override
    public Developer findById(int developerId) {
        for (Developer developer : developers) {
            if (developerId == developer.getDeveloperId()) {
                System.out.println("Developer: Id " + developerId + ", found");
                return developer;
            }
        }
        System.out.println("Developer: Id " + developerId + ", not found");
        return null;
    }

    @Override
    public void add(Developer developer) {
        developers.add(developer);
        System.out.println("Developer: Id " + developer.getDeveloperId() +
                ", name: " + developer.getDeveloperName() + " added");
    }

    @Override
    public void update(Developer developer) {
        developers.get(developer.getDeveloperId()).setDeveloperName(developer.getDeveloperName());
        System.out.println("Developer: Id " + developer.getDeveloperId() + ", updated");
    }

    @Override
    public void deleteById(int developerId) {
        for (Developer developer : developers) {
            if (developerId == developer.getDeveloperId()) {
                developers.remove(developer);
                System.out.println("Developer: Id " + developerId + ", deleted");
                return;
            }
        }
        System.out.println("Developer: Id " + developerId + ", not found");

    }

}