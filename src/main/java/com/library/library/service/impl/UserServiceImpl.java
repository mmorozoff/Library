package com.library.library.service.impl;

import com.library.library.dto.GeneralUserDto;
import com.library.library.dto.UserDto;
import com.library.library.exception.ConvertingException;
import com.library.library.exception.NoSuchEntityException;
import com.library.library.models.User;
import com.library.library.repository.UserRepository;
import com.library.library.service.UserService;
import com.library.library.service.converter.UserConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(String.valueOf(ActServiceImpl.class));


    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserServiceImpl (UserRepository userRepository, UserConverter userConverter){
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDto> allUsers() {
        logger.info("Show users");
        return StreamSupport.stream(userRepository
                .findAll().spliterator(), false)
                .map(user->userConverter.convertToAllUserInfoDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralUserDto> allUserGeneralInfo() {
        logger.info("Show users general info");
        return StreamSupport.stream(userRepository
                .findAll().spliterator(), false)
                .map((user)->userConverter.convertToGeneralUserInfoDto(user))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(UserDto dto) throws Exception {
        logger.info("Add user id = " + dto.getId());
        User user = userConverter.convertToUser(dto);
        userRepository.save(user);
    }

    @Override
    public UserDto getByUserName(String username) throws ConvertingException {
        logger.info("Get user by name: " + username);
        List<UserDto> users = this.allUsers();
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getLogin() == username)
                return users.get(i);
        }
        return null;
    }

    @Transactional
    @Override
    public void edit(UserDto dto) throws ConvertingException {
        logger.info("Edit user id: " + dto.getId());
        User user = userConverter.convertToUser(dto);
        userRepository.save(user);
    }

    @Override
    public UserDto getById(Long id) throws NoSuchEntityException {
        logger.info("Edit user id: " + id);
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return userConverter.convertToAllUserInfoDto(user);
    }
}
