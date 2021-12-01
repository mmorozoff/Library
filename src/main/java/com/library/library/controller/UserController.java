package com.library.library.controller;

import com.library.library.dto.*;
import com.library.library.models.Librarian;
import com.library.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class UserController {
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private UserService service;

    @RequestMapping(value = "/librarians", method = RequestMethod.GET)
    public String showLibrarians(@RequestParam (value = "search", required = false, defaultValue = "") String name, Model model) {
        List<LibrarianDto> librarians =  librarianService.getLibrariansByParam(name);
        model.addAttribute("result", librarians);
        model.addAttribute("search", name);
        return "/librarians";
    }

    @RequestMapping(value = "/blockReader", method = RequestMethod.GET)
    public String blockReader(@RequestParam(value = "id", required = false) Long id, Model model){
        if(id != null){
            ReaderDto dto = readerService.getById(id);
            if (dto.getUserRole().equals("ROLE_BLOCKED"))
                dto.setUserRole("ROLE_READER");
            else
                dto.setUserRole("ROLE_BLOCKED");
            readerService.edit(dto);
        }
        return "redirect:/readers";
    }

    @RequestMapping(value="/addLibrarian", method = RequestMethod.GET)
    public String addLibrarian(Model model){
        LibrarianDto dto = new LibrarianDto();
        model.addAttribute("librarian", dto);
        return "/addLibrarian";
    }

    @RequestMapping(value="/addLibrarian", method = RequestMethod.POST)
    public String addLibrarian(@ModelAttribute LibrarianDto dto, BindingResult errors, Model model) throws Exception {
        if(service.getByUserName(dto.getLogin()) == null){
            dto.setUserRole("ROLE_LIBRARIAN");
            librarianService.add(dto);
        }
        return "redirect:/librarians";
    }

    @RequestMapping(value = "/editLibrarian", method = RequestMethod.GET)
    public String editLibrarian(@RequestParam(value = "id", required = true) Long id, Model model) {
        LibrarianDto dto = librarianService.getById(id);
        model.addAttribute("librarian", dto);
        return "/editLibrarian";
    }

    @RequestMapping(value="/editLibrarian", method = RequestMethod.POST)
    public String editLibrarian(@ModelAttribute LibrarianDto dto, BindingResult errors, Model model) throws Exception {
        dto.setUserRole("ROLE_LIBRARIAN");
        librarianService.edit(dto);
        return "redirect:/librarians";
    }

    @RequestMapping(value = "/deleteLibrarian", method = RequestMethod.GET)
    public String deleteLibrarian(@RequestParam (value = "id", required = false) Long id,  Model model){
        if(id != null){
            LibrarianDto dto = librarianService.getById(id);
            librarianService.delete(dto);
        }
        return "redirect:/librarians";
    }

    @RequestMapping(value = "/editReader", method = RequestMethod.GET)
    public String editReader(@RequestParam(value = "id", required = true) Long id, Model model) {
        ReaderDto dto = readerService.getById(id);
        model.addAttribute("reader", dto);
        return "/editReader";
    }

    @RequestMapping(value="/editReader", method = RequestMethod.POST)
    public String editReader(@ModelAttribute ReaderDto dto, BindingResult errors, Model model) throws Exception {
        dto.setUserRole("ROLE_READER");
        readerService.edit(dto);
        return "redirect:/readers";
    }

    @RequestMapping(value = "/readers", method = RequestMethod.GET)
    public String showReaders(@RequestParam (value = "search", required = false, defaultValue = "") String name, Model model) {
        List<ReaderDto> readers =  readerService.getReadersByParam(name);
        model.addAttribute("result", readers);
        model.addAttribute("search", name);
        return "/readers";
    }

    @RequestMapping(value = "/deleteReader", method = RequestMethod.GET)
    public String deleteReader(@RequestParam (value = "id", required = false) Long id,  Model model){
        if(id != null){
            ReaderDto dto = readerService.getById(id);
            readerService.delete(dto);
        }
        return "redirect:/readers";
    }
}
