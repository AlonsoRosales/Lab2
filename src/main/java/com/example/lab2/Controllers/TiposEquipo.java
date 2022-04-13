package com.example.lab2.Controllers;


import com.example.lab2.Entity.Sede;
import com.example.lab2.Entity.Tipo;
import com.example.lab2.Repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tipos")
public class TiposEquipo {

    @Autowired
    TipoRepository tipoRepository;

    @GetMapping("")
    public String home(){
        return "redirect:/tipos/listarTipos";
    }

    @GetMapping("/listarTipos")
    public String listarSedes(Model model) {
        List<Tipo> listaTipos = tipoRepository.findAll();
        model.addAttribute("listaTipos", listaTipos);


        return "tiposEquipo/listarEquipos";
    }


}
