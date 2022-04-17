package dao.simple;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDeveloperDao implements DeveloperDao {

    // list is working as a database
    private List<Developer> developers;

    public InMemoryDeveloperDao() {
        developers = new ArrayList<>();
        System.out.println("Developers data structure created");
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
        if (null != findById(developer.getDeveloperId())) {
                developers.get(developer.getDeveloperId()).setDeveloperName(developer.getDeveloperName());
                System.out.println("Developer: Id " + developer.getDeveloperId() + ", updated");
                return;
        }
        System.out.println("Developer: Id " + developer.getDeveloperId() + ", not found");
    }

    @Override
    public void deleteById(int developerId) {
        Developer developer = findById(developerId);
        if (null != developer) {
            developers.remove(developer);
            System.out.println("Developer: Id " + developerId + ", deleted");
            return;
        }
        System.out.println("Developer: Id " + developerId + ", not found");

    }

}