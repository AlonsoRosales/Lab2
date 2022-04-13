package com.example.lab2.Controllers;

import com.example.lab2.Repository.MarcasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marcas")
public class MarcasController {

    @Autowired
    MarcasRepository marcasRepository;

    @GetMapping(value={"","/"})
    public String listaMarcas(Model model){
        model.addAttribute("listaMarcas",marcasRepository.findAll());
        return "marcas/list";
    }
}
