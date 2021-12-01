package com.library.library.service.converter;

import com.library.library.dto.ReaderDto;
import com.library.library.dto.GeneralUserDto;
import com.library.library.dto.LibrarianDto;
import com.library.library.dto.UserDto;
import com.library.library.exception.ConvertingException;
import com.library.library.models.*;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public GeneralUserDto convertToGeneralUserInfoDto(User user){
        GeneralUserDto dto = new GeneralUserDto();
        dto.setId(user.getId());
        dto.setUserRole(user.getUserRole().getValue());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    public UserDto convertToAllUserInfoDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserRole(user.getUserRole().getValue());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setLogin(user.getLogin());
        dto.setUserPassword(user.getUserPassword());
        return dto;
    }

    public User convertToUser(UserDto dto){
        User user = new User();
        user = getBaseUser(user, dto);
        return user;
    }


    public Reader convertToReader(ReaderDto dto) throws ConvertingException{
        throwExceptionIfDtoIsNotValid(dto);

        Reader reader = new Reader();
        reader = (Reader)getBaseUser(reader, dto);
        reader.setEmail(dto.getEmail());
        return reader;
    }

    public ReaderDto convertToReaderInfoDto(Reader reader){
        ReaderDto dto = new ReaderDto();
        dto.setId(reader.getId());
        dto.setEmail(reader.getEmail());
        dto.setUserRole(reader.getUserRole().getValue());
        dto.setFirstName(reader.getFirstName());
        dto.setLastName(reader.getLastName());
        dto.setLogin(reader.getLogin());
        dto.setUserPassword(reader.getUserPassword());
        return dto;
    }

    public Librarian convertToLibrarian(LibrarianDto dto) throws ConvertingException{
        throwExceptionIfDtoIsNotValid(dto);

        Librarian librarian = new Librarian();
        librarian = (Librarian) getBaseUser(librarian, dto);
        librarian.setEmail(dto.getEmail());
        librarian.setPhone(dto.getPhone());
        return librarian;
    }

    public LibrarianDto convertToLibrarianInfoDto(Librarian librarian){
        LibrarianDto dto = new LibrarianDto();
        dto.setId(librarian.getId());
        dto.setEmail(librarian.getEmail());
        dto.setPhone(librarian.getPhone());
        dto.setUserRole(librarian.getUserRole().getValue());
        dto.setFirstName(librarian.getFirstName());
        dto.setLastName(librarian.getLastName());
        dto.setLogin(librarian.getLogin());
        dto.setUserPassword(librarian.getUserPassword());
        return dto;
    }

    private User getBaseUser(User user, UserDto dto){
        user.setId(dto.getId());
        user.setUserRole(RoleEnum.valueOf(dto.getUserRole()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setLogin(dto.getLogin());
        user.setUserPassword(dto.getUserPassword());
        return user;
    }

    private void throwExceptionIfDtoIsNotValid(UserDto dto) throws ConvertingException {
        if (dto == null)
            throw new ConvertingException("Must be not null.");
        if (dto.getLogin() == null)
            throw new ConvertingException("Login must be not null.");
        if (dto.getFirstName() == null || dto.getLastName() == null)
            throw new ConvertingException("Name must be not null.");
    }

}
