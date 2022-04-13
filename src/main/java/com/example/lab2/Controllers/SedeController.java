package com.example.lab2.Controllers;


import com.example.lab2.Entity.Sede;
import com.example.lab2.Entity.Trabajadores;
import com.example.lab2.Repository.SedeRepository;
import com.example.lab2.Repository.TrabajadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sedes")
public class SedeController {

    @Autowired
    SedeRepository sedeRepository;

    @Autowired
    TrabajadoresRepository trabajadoresRepository;

    @GetMapping("")
    public String home(){
        return "redirect:/sedes/listarSedes";
    }

    @GetMapping("/listarSedes")
    public String listarSedes(Model model) {
        List<Sede> listaSedes = sedeRepository.findAll();
        model.addAttribute("listaSedes",listaSedes);
        return "Sede/listaSedes";
    }

    @GetMapping("/nuevo")
    public String nuevaSede(){
        return "Sede/nuevaSede";
    }

    @PostMapping("/crear")
    public String crearSede(Sede sede, RedirectAttributes attributes){
        sedeRepository.save(sede);
        attributes.addFlashAttribute("msg","Sede creada exitosamente");
        return "redirect:/sedes/listarSedes";
    }

    @GetMapping("/editar")
    public String editarSedes(@RequestParam("id") String id, Model model){
        Optional<Sede> sede = sedeRepository.findById(Integer.parseInt(id));

        if(sede.isPresent()){
            Sede sede1 = sede.get();
            model.addAttribute("sede",sede1);
            model.addAttribute("listaTrabajadores",trabajadoresRepository.buscarTrabajadoresPorSede(id));
            return "Sede/editarSede";
        }
        return "redirect:/sedes/listarSedes";
    }

    @PostMapping("/updateSede")
    public String updateSede(Sede sede, RedirectAttributes attributes){
        sedeRepository.save(sede);
        attributes.addFlashAttribute("msg1","Sede editada exitosamente");
        return "redirect:/sedes/listarSedes";
    }


    @GetMapping("/borrar")
    public String borrarSede(@RequestParam("id") String id,RedirectAttributes attributes){
        sedeRepository.deleteById(Integer.parseInt(id));
        attributes.addFlashAttribute("msg2","Sede borrada exitosamente");
        return "redirect:/sedes/listarSedes";
    }


}
