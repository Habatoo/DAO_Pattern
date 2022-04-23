package dao.tests;


import java.util.ArrayList;
import java.util.List;

class User {
    public String name;
    public int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }
}

interface UserDao {
    // write your code here
    void add(User user);
    User get(int id);
    void update(User user);
    void delete(int id);
}

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        userDao.add(new User("John", 0));
        userDao.add(new User("Jack", 1));

        User firstUser = userDao.get(0);
        User secondUser = userDao.get(1);

        System.out.println("First user : id " + firstUser.id + ", name " + firstUser.name);
        System.out.println("Second user : id " + secondUser.id + ", name " + secondUser.name);

        userDao.update(new User("Jim", firstUser.id));
        System.out.println("First user : id " + firstUser.id + ", name " + firstUser.name);

        userDao.delete(firstUser.id);
    }
}

class UserDaoImpl implements UserDao {
    private List<User> users;

    public UserDaoImpl() {
        users = new ArrayList<>();
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User get(int id) {
        return users.get(id);
    }

    @Override
    public void update(User user) {
        users.get(user.id).name = user.name;
    }

    @Override
    public void delete(int id) {
        users.remove(id);
        System.out.println("User with id " + id + " deleted");
    }

}
