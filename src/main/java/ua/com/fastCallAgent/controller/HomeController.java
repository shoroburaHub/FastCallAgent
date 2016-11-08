package ua.com.fastCallAgent.controller;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.fastCallAgent.parser.Task;
import ua.com.fastCallAgent.service.ClientService;

import java.text.ParseException;

/**
 * Created by koko on 29.08.16.
 */
@Controller
public class HomeController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String home(Model model) throws ParseException, SchedulerException {
        model.addAttribute("clients", clientService.findAll());
//        model.addAttribute("time", LocalDate.now());
//        Parser.startParsing();
        Task.execute();
        return "home";
    }

    @RequestMapping(value = "/changeStatus/{id}", method = RequestMethod.GET)
    public void changeStatus(@PathVariable String id){

    }

    @RequestMapping(value = "/deleteRecord/{id}", method = RequestMethod.GET)
    public String  deleteRecord(@PathVariable String id){
        clientService.delete(Integer.parseInt(id));
        return "redirect:/home";
    }
}
