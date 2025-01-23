package com.atharva.journalapp.service;

import com.atharva.journalapp.entity.User;
import com.atharva.journalapp.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadUserByUserNametest(){

        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().roles(new ArrayList<>()).userName("Ak").password("qweqwe").build());
        UserDetails user = userDetailsServiceImpl.loadUserByUsername("AK");
        Assertions.assertNotNull(user);
    }
}
