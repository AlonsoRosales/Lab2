package com.example.lab2.Controllers;


import com.example.lab2.Entity.Sede;
import com.example.lab2.Repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Sedes")
public class SedeController {

    @Autowired
    SedeRepository sedeRepository;

    @GetMapping("")
    public String home(){
        return "redirect:/Sedes/listarSedes";
    }

    @GetMapping("/listarSedes")
    public String listarRegiones(Model model) {
        List<Sede> listaSedes = sedeRepository.findAll();
        model.addAttribute("listaSedes",listaSedes);
        return "Sede/listaSedes";
    }

}
