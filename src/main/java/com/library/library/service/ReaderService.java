package com.library.library.service;

import com.library.library.dto.ReaderDto;
import com.library.library.exception.ConvertingException;
import com.library.library.exception.NoSuchEntityException;

import java.io.Reader;
import java.util.List;

public interface ReaderService {

    List<ReaderDto> allReaders();

    void add(ReaderDto dto) throws ConvertingException;

    void delete(ReaderDto dto) throws ConvertingException;

    void edit(ReaderDto dto) throws ConvertingException;

    ReaderDto getById(Long id) throws NoSuchEntityException;

    List<ReaderDto> getReadersByParam(String param);

    ReaderDto getByUserName(String username);
}
