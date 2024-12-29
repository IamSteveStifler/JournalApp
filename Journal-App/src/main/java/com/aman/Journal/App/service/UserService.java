package com.aman.Journal.App.service;

import com.aman.Journal.App.entity.Journal;
import com.aman.Journal.App.entity.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {

    Boolean saveUser(User user);
    List<User> getAllUser();
    User getUserByName(String userName);
    Boolean updateUserByUserName(String userName, User user);
    Boolean deleteUserByUserName(String userName);

}
