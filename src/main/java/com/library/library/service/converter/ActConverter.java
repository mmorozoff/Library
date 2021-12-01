package com.library.library.service.converter;

import com.library.library.dto.ActDto;
import com.library.library.exception.ConvertingException;
import com.library.library.models.Act;
import com.library.library.models.Librarian;
import com.library.library.models.Reader;
import com.library.library.models.Book;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ActConverter {

    public ActDto convertToAllActInfoDto(Act act){
        ActDto dto = new ActDto();
        dto.setId(act.getId());
        dto.setReaderName(act.getReader());
        dto.setBookName(act.getBook());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(act.getStartDate());
        dto.setStartDate(date);
        date = dateFormat.format(act.getFinishDate());
        dto.setFinishDate(date);
        return dto;
    }

    public Act convertToAct(ActDto dto) throws ConvertingException, ParseException {
        throwExceptionIfDtoIsNotValid(dto);

        Act act = new Act();
        act.setId(dto.getId());
        act.setBook(dto.getBookName());
        act.setReader(dto.getReaderName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dto.getStartDate());
        act.setStartDate(date);
        date = simpleDateFormat.parse(dto.getFinishDate());
        act.setFinishDate(date);
        return act;
    }

    private void throwExceptionIfDtoIsNotValid(ActDto dto) {
        if(dto == null){
            throw new ConvertingException("Act must be not empty.");
        }
    }

}
