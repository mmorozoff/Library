package com.library.library.service;

import com.library.library.dto.GeneralUserDto;
import com.library.library.dto.UserDto;
import com.library.library.exception.ConvertingException;
import com.library.library.exception.NoSuchEntityException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService{

    List<UserDto> allUsers();

    List<GeneralUserDto> allUserGeneralInfo();

    void add(UserDto dto) throws Exception;

    UserDto getByUserName(String username) throws ConvertingException;

    void edit(UserDto dto) throws ConvertingException;

    UserDto getById(Long id) throws NoSuchEntityException;
}
