package com.example.lytx.lytx.controller;

import com.example.lytx.lytx.services.UserService;
import com.example.lytx.lytx.vm.ResultWithSuccess;
import com.example.lytx.lytx.vm.UserVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(AddressConstants.UserEndPoint)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = AddressConstants.saveOrUpdate)
    public ResultWithSuccess saveOrUpdate(@RequestBody @Valid UserVm entity) {
        ResultWithSuccess saved = userService.saveOrUpdateUser(entity);
        return saved;
    }

    @RequestMapping(method = RequestMethod.GET, value = AddressConstants.findAll)
    public ResultWithSuccess findAll() {
        ResultWithSuccess resultWithSuccess = userService.getAlUser();
        return resultWithSuccess;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = AddressConstants.delete + "/{userId}")
    public ResultWithSuccess findByName(@PathVariable("userId") Integer userId) {
        ResultWithSuccess resultWithSuccess = userService.deleteUser(userId);
        return resultWithSuccess;
    }

}
