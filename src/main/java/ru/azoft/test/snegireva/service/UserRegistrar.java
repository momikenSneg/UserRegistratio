package ru.azoft.test.snegireva.service;

import ru.azoft.test.snegireva.models.User;

public interface UserRegistrar {
    String register(User user);
    String getMessage(String message);
}
