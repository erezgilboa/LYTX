package com.example.lytx.lytx;

import com.example.lytx.lytx.services.UserService;
import com.example.lytx.lytx.vm.ResultWithSuccess;
import com.example.lytx.lytx.vm.UserVm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class LytxApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void createNewUser() {


        UserVm userVm = createRandomUser();
        ResultWithSuccess resultWithSuccess = userService.saveOrUpdateUser(userVm);
        assertTrue(resultWithSuccess.isSuccess(), "create user failed:" + resultWithSuccess.getReason());
        assertTrue(resultWithSuccess.getResult() != null, "saved object is null");
        UserVm saved = (UserVm) resultWithSuccess.getResult();
        compare2Users(saved, userVm);

    }

    @Test
    void deleteUser() {
        UserVm userVm = createRandomUser();
        ResultWithSuccess resultWithSuccess = userService.saveOrUpdateUser(userVm);
        assertTrue(resultWithSuccess.isSuccess(), "create user failed:" + resultWithSuccess.getReason());
        assertTrue(resultWithSuccess.getResult() != null, "saved object is null");
        UserVm saved = (UserVm) resultWithSuccess.getResult();
        ResultWithSuccess deleteSuccess = userService.deleteUser(saved.getId());
        assertTrue(deleteSuccess.isSuccess(), deleteSuccess.getReason());
        ResultWithSuccess resultGetAll = userService.getAlUser();
        List<UserVm> userVmList = (List<UserVm>) resultGetAll.getResult();
        Optional<UserVm> saved1 = userVmList.stream().
                filter(p -> p.getId().equals(saved.getId())).
                findFirst();
        assertFalse(saved1.isPresent(), "user didn't deleted");
    }

    @Test
    void getAllUsers() {


        UserVm userVm1 = createRandomUser();
        UserVm userVm2 = createRandomUser();
        UserVm userVm3 = createRandomUser();
        ResultWithSuccess resultWithSuccess1 = userService.saveOrUpdateUser(userVm1);
        ResultWithSuccess resultWithSuccess2 = userService.saveOrUpdateUser(userVm2);
        ResultWithSuccess resultWithSuccess3 = userService.saveOrUpdateUser(userVm3);
        assertTrue(resultWithSuccess1.isSuccess(), "create user1 failed:" + resultWithSuccess1.getReason());
        assertTrue(resultWithSuccess2.isSuccess(), "create user2 failed:" + resultWithSuccess2.getReason());
        assertTrue(resultWithSuccess3.isSuccess(), "create user3 failed:" + resultWithSuccess3.getReason());
        ResultWithSuccess resultWithSuccess = userService.getAlUser();
        List<UserVm> userVmList = (List<UserVm>) resultWithSuccess.getResult();
        UserVm saved1 = userVmList.stream().
                filter(p -> p.getId().equals(userVm1.getId())).
                findFirst().get();
        UserVm saved2 = userVmList.stream().
                filter(p -> p.getId().equals(userVm2.getId())).
                findFirst().get();
        UserVm saved3 = userVmList.stream().
                filter(p -> p.getId().equals(userVm3.getId())).
                findFirst().get();
        compare2Users(saved1, userVm1);
        compare2Users(saved2, userVm2);
        compare2Users(saved3, userVm3);


    }

    private UserVm createRandomUser() {
        UserVm userVm = new UserVm();
        userVm.setEmail(getSaltString(10) + "@gmail.com");
        userVm.setName(getSaltString(8));
        userVm.setPassword(getSaltString(9));
        userVm.setId(createUniqueNumber(11111111, 999999999));
        return userVm;
    }

    private Integer createUniqueNumber(int min, int max) {
        //Generate random int value from 50 to 100
        System.out.println("Random value in int from " + min + " to " + max + ":");
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    protected String getSaltString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    private void compare2Users(UserVm user1, UserVm user2) {
        assertEquals(user1.getId(), user2.getId(), "id not equal after save");
        assertEquals(user1.getEmail(), user2.getEmail(), "email not equal after save");
        assertEquals(user1.getName(), user2.getName(), "name not equal after save");
        assertEquals(user1.getPassword(), user2.getPassword(), "password not equal after save");
    }

}
