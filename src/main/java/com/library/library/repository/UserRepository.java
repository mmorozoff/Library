package com.library.library.repository;

import com.library.library.dto.UserDto;
import com.library.library.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    UserDto getByLogin(String login);
}
