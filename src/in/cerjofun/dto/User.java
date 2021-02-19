package in.cerjofun.dto;

/**
 * Created by mohan on 19/02/21
 */
public class User {

    private String name;
    private boolean critic;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCritic() {
        return critic;
    }

    public void setCritic(boolean critic) {
        this.critic = critic;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", critic=" + critic +
                '}';
    }
}
