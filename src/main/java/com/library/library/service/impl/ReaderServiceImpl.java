package com.library.library.service.impl;

import com.library.library.exception.ConvertingException;
import com.library.library.exception.NoSuchEntityException;
import com.library.library.models.Reader;
import com.library.library.dto.*;
import com.library.library.repository.ReaderRepository;
import com.library.library.service.ReaderService;
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
public class ReaderServiceImpl implements ReaderService {
    private static final Logger logger = Logger.getLogger(String.valueOf(ReaderServiceImpl.class));


    private ReaderRepository readerRepository;
    private UserConverter userConverter;

    public ReaderServiceImpl(ReaderRepository readerRepository, UserConverter userConverter) {
        this.readerRepository = readerRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<ReaderDto> allReaders() {
        logger.info("Show readers");
        return StreamSupport.stream(readerRepository
        .findAll().spliterator(), false)
                .map(reader -> userConverter.convertToReaderInfoDto(reader))
                .sorted(Comparator.comparing(ReaderDto::getId))
                .collect(Collectors.toList());
    }

    public ReaderDto getByUserName(String username) throws ConvertingException {
        logger.info("Get user by name: " + username);
        List<ReaderDto> users = this.allReaders();
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getLogin().equals(username))
                return users.get(i);
        }
        return null;
    }

    @Transactional
    @Override
    public void add(ReaderDto dto) throws ConvertingException {
        logger.info("Add reader id = " + dto.getId());
        Reader reader = userConverter.convertToReader(dto);
        readerRepository.save(reader);
    }

    @Transactional
    @Override
    public void delete(ReaderDto dto) throws ConvertingException {
        logger.info("Delete act id = " + dto.getId());
        Reader reader = userConverter.convertToReader(dto);
        readerRepository.delete(reader);
    }

    @Transactional
    @Override
    public void edit(ReaderDto dto) throws ConvertingException {
        logger.info("Edit reader id = " + dto.getId());
        Reader reader = userConverter.convertToReader(dto);
        readerRepository.save(reader);
    }

    @Override
    public ReaderDto getById(Long id) throws NoSuchEntityException {
        logger.info("Get reader by id = " + id);
        Reader reader = readerRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(String.format("Can't find entity by id = %id", id)));
        return userConverter.convertToReaderInfoDto(reader);
    }

    @Override
    public List<ReaderDto> getReadersByParam(String param) {
        logger.info("Get reader by param: " + param);
        List<ReaderDto> result = StreamSupport.stream(readerRepository
                .findAll().spliterator(), false)
                .map(reader -> userConverter.convertToReaderInfoDto(reader))
                .filter(reader -> reader.getFirstName().contains(param)
                || reader.getLastName().contains(param)
                || reader.getLogin().contains(param)
                || reader.getUserPassword().contains(param)
                || reader.getEmail().contains(param))
                .collect(Collectors.toList());
        logger.info("readers setted");
        return result;
    }
}
