package com.example.lytx.lytx.services;

import com.example.lytx.lytx.vm.ResultWithSuccess;
import com.example.lytx.lytx.vm.UserVm;

public interface UserService {

    ResultWithSuccess getAlUser();

    ResultWithSuccess saveOrUpdateUser(UserVm userVm);

    ResultWithSuccess deleteUser(Integer userId);
}
