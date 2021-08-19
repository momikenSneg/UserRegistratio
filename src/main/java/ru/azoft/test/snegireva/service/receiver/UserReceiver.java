package ru.azoft.test.snegireva.service.receiver;

import ru.azoft.test.snegireva.models.User;

public interface UserReceiver {
    User getUser(String id);
}
