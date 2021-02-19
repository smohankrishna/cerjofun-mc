package in.cerjofun.service;

/**
 * Created by mohan on 19/02/21
 */
public class BaseService {

    protected void throwException(String message) {
        throw new RuntimeException(message);
    }
}
