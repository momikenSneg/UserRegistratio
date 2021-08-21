package ru.azoft.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.azoft.test.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
