package entityfactory;

import entity.User;
import entityenum.UserEntity;

/**
 * Factory class for creating instances of {@link User}.
 * This class provides a method to create a {@link User} object
 * with predefined email and password values obtained from the {@link UserEntity} enum.
 */

/**
 * Factory class for creating instances of {@link User}.
 * This class provides a method to create a {@link User} object
 * with predefined email and password values obtained from the {@link UserEntity} enum.
 */

public class UserFactory {
    public static User createUser(){
        return new User(UserEntity.EMAIL.getFieldName(),
                UserEntity.PASSWORD.getFieldName());
    }
}
