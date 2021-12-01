package com.library.library.controller;

import com.library.library.dto.*;
import com.library.library.models.User;
import com.library.library.service.ActService;
import com.library.library.service.BookService;
import com.library.library.service.ReaderService;
import com.library.library.service.UserService;
import com.library.library.service.impl.ActServiceImpl;
import org.hibernate.criterion.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ActService actService;
    @Autowired
    private ReaderService service;
    private static final Logger logger = Logger.getLogger(String.valueOf(ActServiceImpl.class));
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String showBooksByName(@RequestParam (value = "search", required = false, defaultValue = "") String name, Model model, HttpServletRequest request) {
        List<BookDto> books =  bookService.getBooksByName(name);

        for (int i = 0; i < books.size(); i++)
        {
            if (books.get(i).getBookCount() < 0 && request.isUserInRole("READER"))
            {
                books.remove(i);
                i--;
            }
        }

        model.addAttribute("result", books);
        model.addAttribute("search", name);
        return "/books";
    }

    @RequestMapping(value="/addBook", method = RequestMethod.GET)
    public String addBook(Model model){
        BookDto dto = new BookDto();
        model.addAttribute("book", dto);
        return "/addBook";
    }

    @RequestMapping(value="/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute BookDto dto, BindingResult errors, Model model) throws Exception {
        bookService.add(dto);
        return "redirect:/books";
    }

    @RequestMapping(value = "/editBook", method = RequestMethod.GET)
    public String editBook(@RequestParam(value = "id", required = true) Long id, Model model) {
        BookDto dto = bookService.getById(id);
        model.addAttribute("book", dto);
        return "/editBook";
    }

    @RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
    public String deleteBook(@RequestParam (value = "id", required = false) Long id,  Model model){
        if(id != null){
            BookDto dto = bookService.getById(id);
            bookService.delete(dto);
        }
        return "redirect:/books";
    }

    @RequestMapping(value = "/takeBook", method = RequestMethod.GET)
    public String takeBook(@RequestParam (value = "id", required = false) Long id,  Model model) throws ParseException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ReaderDto user = service.getByUserName(username);
        BookDto dto = bookService.getById(id);
        dto.setBookCount(dto.getBookCount() - 1);
        bookService.edit(dto);
        ActDto dtoAct = new ActDto();
        dtoAct.setReaderName(user.getFirstName() + " " + user.getLastName());
        dtoAct.setBookName(dto.getBookName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        dtoAct.setStartDate(dateFormat.format(new Date()));
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.DAY_OF_MONTH, 30);
        dtoAct.setFinishDate(dateFormat.format(instance.getTime()));
        actService.add(dtoAct);
        return "redirect:/books";
    }

    @RequestMapping(value="/editBook", method = RequestMethod.POST)
    public String editBook(@ModelAttribute BookDto dto, BindingResult errors, Model model) throws Exception {
        bookService.edit(dto);
        return "redirect:/books";
    }
}
