package pt.iade.mypastry.repositories;

import java.util.ArrayList;

import pt.iade.mypastry.models.User;

public class UserRepository {
    private static ArrayList<User> users = new ArrayList<User>();

    public static void populate() {
        users.add(new User("Jane Doe", "janedoe@gmail.com", 1));
    }

    public static ArrayList<User> getUsers(){
        return users;
    }

    public static User getUser(int id){
        for (User u : users){
            if (u.getId() == id){
                return u;
            }
        }
        return null;
    }
    public static void addUser(User user) {
        users.add(user);
    }

    public static boolean removeUser(int id) {
        return users.removeIf(u -> u.getId() == id);
    }


}
