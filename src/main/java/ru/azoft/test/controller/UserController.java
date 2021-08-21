package ru.azoft.test.controller;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.azoft.test.models.User;
import ru.azoft.test.service.receiver.UserReceiver;
import ru.azoft.test.service.registrar.UserRegistrar;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRegistrar registrar;
    private final UserReceiver receiver;

    public UserController(@Qualifier("userRegistrar") UserRegistrar registrar,
                          @Qualifier("userReceiver") UserReceiver receiver) {
        this.registrar = registrar;
        this.receiver = receiver;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        if (user == null || user.getLogin() == null ||
                user.getPassword() == null || user.getEmail() == null ||
                user.getFirstName() == null || user.getLastName() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong format of user");

        EmailValidator validator = EmailValidator.getInstance();
        if (!validator.isValid(user.getEmail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong email format");

        String id = registrar.register(user);

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String userId){
        if (userId == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        User user = receiver.getUser(userId);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
