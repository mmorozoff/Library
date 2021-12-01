package com.library.library.controller;

import com.library.library.dto.*;
import com.library.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
    @Autowired
    private UserService service;

    @Autowired
    private ReaderService readerService;

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String register(Model model){
        ReaderDto dto = new ReaderDto();
        model.addAttribute("usr", dto);
        return "main/registration";
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute ReaderDto dto, BindingResult errors, Model model) throws Exception {
        dto.setUserRole("ROLE_READER");
        if(service.getByUserName(dto.getLogin()) == null){
            readerService.add(dto);
        }
        return "redirect:/login";
    }
}
