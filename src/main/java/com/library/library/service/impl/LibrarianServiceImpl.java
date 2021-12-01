package com.library.library.service.impl;

import com.library.library.dto.GeneralUserDto;
import com.library.library.dto.LibrarianDto;
import com.library.library.exception.ConvertingException;
import com.library.library.exception.NoSuchEntityException;
import com.library.library.models.Librarian;
import com.library.library.repository.LibrarianRepository;
import com.library.library.service.LibrarianService;
import com.library.library.service.converter.UserConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class LibrarianServiceImpl implements LibrarianService {
    private static final Logger logger = Logger.getLogger(String.valueOf(LibrarianServiceImpl.class));


    private LibrarianRepository librarianRepository;
    private UserConverter userConverter;

    public LibrarianServiceImpl(LibrarianRepository librarianRepository, UserConverter userConverter) {
        this.librarianRepository = librarianRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<LibrarianDto> allLibrarians() {
        logger.info("Show librarians");
        return StreamSupport.stream(librarianRepository
                .findAll().spliterator(), false)
                .map(librarian -> userConverter.convertToLibrarianInfoDto(librarian))
                .sorted(Comparator.comparing(LibrarianDto::getId))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void add(LibrarianDto dto) throws ConvertingException {
        logger.info("Add librarian id = " + dto.getId());
        Librarian librarian = userConverter.convertToLibrarian(dto);
        librarianRepository.save(librarian);
    }

    @Transactional
    @Override
    public void delete(LibrarianDto dto) throws ConvertingException {
        logger.info("Delete librarian id = " + dto.getId());
        Librarian librarian = userConverter.convertToLibrarian(dto);
        librarianRepository.delete(librarian);
    }

    @Transactional
    @Override
    public void edit(LibrarianDto dto) throws ConvertingException {
        logger.info("Edit librarian id = " + dto.getId());
        Librarian librarian = userConverter.convertToLibrarian(dto);
        librarianRepository.save(librarian);
    }

    @Override
    public LibrarianDto getById(Long id) throws NoSuchEntityException {
        logger.info("Get librarian by id: " + id);
        Librarian librarian = librarianRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return userConverter.convertToLibrarianInfoDto(librarian);
    }

    @Override
    public List<LibrarianDto> getLibrariansByParam(String param) {
        logger.info("Get librarians by param: " + param);
        return StreamSupport.stream(librarianRepository
                .findAll().spliterator(), false)
                .map(librarian -> userConverter.convertToLibrarianInfoDto(librarian))
                .filter(librarian -> librarian.getFirstName().contains(param)
                        || librarian.getLastName().contains(param)
                        || librarian.getLogin().contains(param)
                        || librarian.getUserPassword().contains(param)
                        || librarian.getPhone().contains(param)
                        || librarian.getEmail().contains(param)
                        || librarian.getUserRole().contains(param))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralUserDto> getGeneralLibrariansInfo(){
        logger.info("Get general librarians info");
        return StreamSupport.stream(librarianRepository
                .findAll().spliterator(), false)
                .map(librarian -> userConverter.convertToGeneralUserInfoDto(librarian))
                .sorted(Comparator.comparing(GeneralUserDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<GeneralUserDto> getLibrariansGeneralInfoByParam(String param){
        logger.info("Get librarians general info by param: " + param);
        return StreamSupport.stream(librarianRepository
                .findAll().spliterator(), false)
                .map(librarian -> userConverter.convertToGeneralUserInfoDto(librarian))
                .filter(librarian -> librarian.getFirstName().contains(param)
                        || librarian.getLastName().contains(param))
                .sorted(Comparator.comparing(GeneralUserDto::getId))
                .collect(Collectors.toList());
    }

}
