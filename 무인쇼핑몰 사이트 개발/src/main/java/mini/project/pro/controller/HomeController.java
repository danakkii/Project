package mini.project.pro.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mini.project.pro.mapper.UserMapper;
import mini.project.pro.model.User;



@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @PostMapping("/")
    public String home(Model mdoel, @RequestParam Map <String> map){
        
    
    }
}

    
    