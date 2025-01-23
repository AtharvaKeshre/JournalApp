package com.atharva.journalapp.service;

import com.atharva.journalapp.entity.User;
import com.atharva.journalapp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSources;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

//    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
//    @ValueSource(strings = {
//            "Atharva",
//            "Amisha",
//            "Joshi",
//            "Adarsh"
//    })
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
//        assertNotNull(userRepo.findByUserName(user),"failed to find user" + user);
//        assertEquals("Atharva",userRepo.findByUserName("Atharva"));

    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "Atharva",
            "Amisha",
            "Joshi",
            "Adarsh"
    })
    public void testFindByUserName(String userName) {
        assertNotNull(userRepo.findByUserName(userName),"failed to find user" + userName);
//        assertEquals("Atharva",userRepo.findByUserName("Atharva"));

    }
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,10,12",
            "1,3,4"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a+b);
    }
}
