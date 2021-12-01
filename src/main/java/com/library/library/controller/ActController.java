package com.library.library.controller;

import com.library.library.dto.ActDto;
import com.library.library.dto.BookDto;
import com.library.library.service.ActService;
import com.library.library.service.BookService;
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
public class ActController {

    @Autowired
    private ActService actService;
    @Autowired
    private BookService bookService;

    @RequestMapping(value="/acts", method = RequestMethod.GET)
    public String getAllActs(Model model){
        List<ActDto> acts = actService.allActs();
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

}
