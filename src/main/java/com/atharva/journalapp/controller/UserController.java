package com.atharva.journalapp.controller;

import com.atharva.journalapp.api.response.WeatherResponse;
import com.atharva.journalapp.entity.JournalEntry;
import com.atharva.journalapp.entity.User;
import com.atharva.journalapp.repository.UserRepo;
import com.atharva.journalapp.repository.UserRepoImpl;
import com.atharva.journalapp.service.EmailService;
import com.atharva.journalapp.service.JournalEntryService;
import com.atharva.journalapp.service.UserService;
import com.atharva.journalapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Autowired
    private EmailService emailService;

//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAll();
//    }
//    @GetMapping("{username}")
//    public User getUserByUserName(@PathVariable String username) {
//        return userService.findByUserName(username);
//    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.OK);

    }


//    @DeleteMapping
//    public ResponseEntity<?> deleteUserById() {
//        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
//        userRepo.deleteByUserName(authentication.getName());
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userService.findByUserName(name);
        List<JournalEntry> userEntries = user.getJournalEntry();
        for(JournalEntry element : userEntries){
            journalEntryService.deleteById(element.getId(), name);
        }
        userRepo.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/weather/{city}")
    public ResponseEntity<?> getWeatherDetails(@PathVariable String city) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather(city);
        String greeting = "";
        if(weatherResponse != null){
            greeting = " Weather feels like for: "+city +" is "+ weatherResponse.getCurrent().getTemperature();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }
    @GetMapping("/query/{userName}")
    public ResponseEntity<?> queryUser(@PathVariable String userName) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        List<User> userForSA = userRepoImpl.getUserForSA(userName);
        return new ResponseEntity<>(userForSA, HttpStatus.OK);
    }

    @GetMapping("/send-email")
    public void sendEmail(){
        emailService.sendEmail("keshre.a@northeastern.edu","Testing SMTP java SpringBoot","Hi, this a a test !");
    }

}
