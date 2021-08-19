package ru.azoft.test.snegireva.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.azoft.test.snegireva.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
