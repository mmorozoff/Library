package com.library.library.service;

import com.library.library.dto.LibrarianDto;
import com.library.library.exception.ConvertingException;
import com.library.library.exception.NoSuchEntityException;

import java.util.List;

public interface LibrarianService {

    void add(LibrarianDto dto) throws ConvertingException;

    void delete(LibrarianDto dto) throws ConvertingException;

    void edit(LibrarianDto dto) throws ConvertingException;

    LibrarianDto getById(Long id) throws NoSuchEntityException;

    List<LibrarianDto> getLibrariansByParam(String param);
}
