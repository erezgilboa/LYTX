package com.example.lytx.lytx.converter;

import com.example.lytx.lytx.entities.User;
import com.example.lytx.lytx.vm.UserVm;

import java.util.ArrayList;
import java.util.List;

public class UserConverter implements EntityConverter<UserVm, User> {
    @Override
    public User convertFromF(UserVm userVm) {
        if (userVm == null)
            return null;
        return new User(userVm.getId(), userVm.getName(), userVm.getEmail(), userVm.getPassword());
    }

    @Override
    public UserVm convertFromDB(User user) {
        if (user == null)
            return null;
        return new UserVm(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    @Override
    public List<User> convertListFromF(List<UserVm> userVms) {
        if (userVms == null)
            return null;
        List<User> users = new ArrayList<>();
        for (UserVm uservm : userVms) {
            if (uservm != null)
                users.add(convertFromF(uservm));
        }
        return users;
    }

    @Override
    public List<UserVm> convertListFromDB(List<User> users) {
        if (users == null)
            return null;
        List<UserVm> userVms = new ArrayList<>();
        for (User user : users) {
            if (user != null)
                userVms.add(convertFromDB(user));
        }
        return userVms;

    }
}
