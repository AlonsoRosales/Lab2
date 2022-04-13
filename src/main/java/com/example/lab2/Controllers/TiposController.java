package com.example.lab2.Controllers;


import com.example.lab2.Entity.Sede;
import com.example.lab2.Entity.Tipo;
import com.example.lab2.Repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tipos")
public class TiposController {

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

    @GetMapping("/nuevoTipos")
    public String nuevoTipo(){
        return "tiposEquipo/nuevoEquipos";
    }

    @PostMapping("/crearTipos")
    public String crearTipo(Tipo tipo){
        tipoRepository.save(tipo);
        return "redirect:/tipos/listarTipos";
    }

    @GetMapping("/borrarTipos")
    public String borrarTipos(@RequestParam("id") String id){
        tipoRepository.deleteById(Integer.parseInt(id));
        return "redirect:/tipos/listarTipos";
    }



    @GetMapping("/editarTipos")
    public String editarTipos(@RequestParam("id") String id, Model model){
        Optional<Tipo> tipo = tipoRepository.findById(Integer.parseInt(id));

        if(tipo.isPresent()){
            Tipo tipo1 = tipo.get();

            model.addAttribute("tipo",tipo1);
            return "tiposEquipo/editarEquipos";
        }
        return "redirect:/tipos/listarTipos";
    }

    @PostMapping("/updateTipos")
    public String updateTipo(Tipo tipo){
        tipoRepository.save(tipo);
        return "redirect:/tipos/listarTipos";
    }


}
