package ru.azoft.test.service.receiver;

import ru.azoft.test.models.User;

public interface UserReceiver {
    User getUser(String id);
}
