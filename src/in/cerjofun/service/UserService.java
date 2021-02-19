package in.cerjofun.service;

import in.cerjofun.dto.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mohan on 19/02/21
 */
public class UserService extends BaseService {

    private static final Map<String,User> users;

    static {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        if (checkUserExists(user.getName())) {
            throwException("Already user exists with the name");
        }
        users.put(user.getName(), user);
    }

    public boolean checkUserExists(String userName) {
        return Objects.nonNull(users.get(userName));
    }

    public void print() {
        System.out.println(users);
    }

    public void markAsCriticUser(String userName) {
        if(!checkUserExists(userName)){
            throwException("User does not exists with the name");
        }
        users.get(userName).setCritic(true);
    }

    public User getUser(String userName) {
        if(!checkUserExists(userName)){
            throwException("User does not exists with the name");
        }
        return users.get(userName);
    }
}
