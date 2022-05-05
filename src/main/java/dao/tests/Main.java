package dao.tests;

import dao.simple.Developer;

import java.util.ArrayList;
import java.util.List;

class User {
    public int id;
    public String name;

    public User(/* write your code here */ int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User : id " + id + ", name : " + name;
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
        userDao.add(new User( 0, "John"));
        userDao.add(new User( 1, "Jack"));

        User firstUser = userDao.get(0);
        User secondUser = userDao.get(1);
        User noUser = userDao.get(10);

        System.out.println(firstUser);
        System.out.println(secondUser);

        User updateUser = userDao.get(0);
        updateUser.setName("Jim");
        userDao.update(updateUser);
        System.out.println(userDao.get(0));

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
        System.out.println(user + " added");
    }

    @Override
    public User get(int id) {
        User found = findById(id);
        if (found == null) {
            System.out.println("User : id " + id + ", not found");
            return null;
        }
        System.out.println(found + ", found");
        return new User(found.getId(), found.getName());

    }

    @Override
    public void update(User user) {
        User found = findById(user.getId());
        if (found != null) {
            found.setName(user.getName());
            System.out.println(found + ", updated");
        } else {
            System.out.println("User " + user.getId() + ", not found");
        }

    }

    @Override
    public void delete(int id) {
        User found = findById(id);
        if (found != null) {
            users.remove(found);
            System.out.println(found + ", deleted");
        } else {
            System.out.println("User " + id + ", not found");
        }

    }

    private User findById(int id) {
        for (User user : users) {
            if (id == user.getId()) {
                return user;
            }
        }
        return null;
    }

}
