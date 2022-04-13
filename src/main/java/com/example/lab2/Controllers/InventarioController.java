package com.example.lab2.Controllers;

import com.example.lab2.Repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventario")
public class InventarioController {
    @Autowired
    InventarioRepository inventarioRepository;

    @GetMapping(value={"","/"})
    public String listarInventario(Model model){
        model.addAttribute("listaInventario",inventarioRepository.findAll());
        return "inventario/lista";
    }


}
