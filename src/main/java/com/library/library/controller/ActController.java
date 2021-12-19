package com.library.library.controller;

import com.library.library.dto.ActDto;
import com.library.library.dto.BookDto;
import com.library.library.dto.ReaderDto;
import com.library.library.models.Book;
import com.library.library.service.ActService;
import com.library.library.service.BookService;
import com.library.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ActController {

    @Autowired
    private ActService actService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService service;

    @RequestMapping(value="/acts", method = RequestMethod.GET)
    public String getAllActs(Model model, HttpServletRequest request){
        List<ActDto> acts = actService.allActs();
        if (request.isUserInRole("READER")) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            ReaderDto user = service.getByUserName(username);
            for (int i = 0; i < acts.size(); i++) {
                if (!acts.get(i).getReaderName().equals(user.getFirstName() + " " + user.getLastName())) {
                    acts.remove(i);
                    i--;
                }
            }
        }
        model.addAttribute("acts", acts);
        return "/acts";
    }

    @RequestMapping(value = "/editAct", method = RequestMethod.GET)
    public String editAct(@RequestParam(value = "id", required = true) Long id, Model model) {
        ActDto dto = actService.getById(id);
        model.addAttribute("act", dto);
        return "/editAct";
    }

    @RequestMapping(value = "/deleteAct", method = RequestMethod.GET)
    public String deleteAct(@RequestParam (value = "id", required = false) Long id,  Model model) throws Exception {
        if(id != null){
            ActDto dto = actService.getById(id);
            actService.delete(dto);
        }
        return "redirect:/acts";
    }

    @RequestMapping(value="/editAct", method = RequestMethod.POST)
    public String editAct(@ModelAttribute ActDto dto, BindingResult errors, Model model) throws Exception {
        actService.edit(dto);
        return "redirect:/acts";
    }

    @RequestMapping(value = "/saveFile", method = RequestMethod.GET)
    public String saveFile(@RequestParam (value = "id", required = true) Long id, Model model){
        if(id != null){
            ActDto dto = actService.getById(id);

        }
        return "fdsfd";
    }

    @RequestMapping(value = "/statsAct", method = RequestMethod.GET)
    public String statsAct(Model model) {
        List<ActDto> base = actService.allActs();
        ArrayList<BookDto> acts = new ArrayList<BookDto>();
        for (int i = 0; i < base.size(); i++)
        {
            int count = 0;
            for (int j = 0; j < base.size(); j++)
            {
                if (base.get(i).getBookName().equals(base.get(j).getBookName()))
                {
                    count++;
                }
            }

            BookDto stat = new BookDto();
            stat.setBookCount((long) count);
            stat.setBookName(base.get(i).getBookName());
            if (!IsContain(acts, stat)) {
                acts.add(stat);
            }
        }
        model.addAttribute("acts", acts);
        return "/statsAct";
    }

    private static boolean IsContain(List<BookDto> list, BookDto book)
    {
        for(var p:list) if (p.getBookName().equals(book.getBookName())) return true; return false;
    }

}
