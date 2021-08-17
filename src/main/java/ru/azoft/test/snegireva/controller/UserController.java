package ru.azoft.test.snegireva.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.azoft.test.snegireva.models.User;

@Controller
@RequestMapping("/user")
public class UserController {
    @PostMapping("/register")
    public ResponseEntity<String> loadTemplate(@RequestBody User user){
        if (user == null || user.getLogin() == null || user.getPassword() == null)            //TODO check more fields
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong format of user");

        return ResponseEntity.status(HttpStatus.OK).body("User created");
    }
}
