package com.example.lytx.lytx.services;

import com.example.lytx.lytx.converter.UserConverter;
import com.example.lytx.lytx.dao.UserDao;
import com.example.lytx.lytx.entities.User;
import com.example.lytx.lytx.vm.ResultWithSuccess;
import com.example.lytx.lytx.vm.UserVm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private Map<Integer, User> usersMap = new ConcurrentHashMap<>();

    @Autowired
    UserConverter userConverter;


    @Autowired
    UserDao userDao;


    @Override
    public ResultWithSuccess getAlUser() {
        logger.info("get all users");
        List<User> users = new ArrayList<>();
        for (User user : usersMap.values()) {
            users.add(user);
        }
        return new ResultWithSuccess(userConverter.convertListFromDB(users), true, "");
    }

    @Override
    public ResultWithSuccess saveOrUpdateUser(UserVm userVm) {
        logger.info("save user:" + userVm.toString());
        if (userVm == null) {
            logger.error("descriptor is null");
            return new ResultWithSuccess(null, false, "user is null");
        }
        try {
            User user = userConverter.convertFromF(userVm);
            User saved = userDao.save(user);
            usersMap.put(saved.getId(), saved);
            return new ResultWithSuccess(userConverter.convertFromDB(saved), true, "");
        } catch (Exception e) {
            logger.error("error to save user:" + e.toString());
            return new ResultWithSuccess(null, false, "error to save user:" + e.toString());
        }
    }

    @Override
    public ResultWithSuccess deleteUser(Integer userId) {
        logger.info("remove user with id:" + userId);
        if (userId == null)
            return new ResultWithSuccess(null, false, "user id is null");
        try {
            userDao.deleteById(userId);
            usersMap.remove(userId);
            return new ResultWithSuccess(null, true, "user was deleted");

        } catch (Exception e) {
            logger.error("error to delete user:" + e.toString());
            return new ResultWithSuccess(null, false, "error to delete user:" + e.toString());
        }
    }

    @PostConstruct
    public void init() {
        List<User> users = userDao.findAll();
        for (User user : users)
            usersMap.put(user.getId(), user);
    }
}
