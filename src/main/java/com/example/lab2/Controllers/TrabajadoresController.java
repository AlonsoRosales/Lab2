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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trabajadores")
public class TrabajadoresController {

    @Autowired
    TrabajadoresRepository trabajadoresRepository;

    @Autowired
    SedeRepository sedeRepository;

    @GetMapping("/listar")
    public String listarTrabajadores(Model model){
        List<Trabajadores> listaTrabajadores = trabajadoresRepository.findAll();
        model.addAttribute("listaTrabajadores", listaTrabajadores);
        return "trabajadores/lista";
    }

    @GetMapping("/agregar")
    public String agregarTrabajadores(Model model){
        List<Sede> listaSedes = sedeRepository.findAll();
        model.addAttribute("listaSedes",listaSedes);
        return "trabajadores/agregar";
    }

    @PostMapping("/save")
    public String saveTrabajadores(Trabajadores trabajador){
        trabajadoresRepository.save(trabajador);
        return "redirect:/trabajadores/listar";
    }

    @GetMapping("/editar")
    public String editarTrabajadores(@RequestParam("dni") String dni,Model model){
        Optional<Trabajadores> optionalTrabajador = trabajadoresRepository.findById(dni);
        if (optionalTrabajador.isPresent()) {
            Trabajadores trabajador = optionalTrabajador.get();
            model.addAttribute("trabajador", trabajador);
            List<Sede> listaSedes = sedeRepository.findAll();
            model.addAttribute("listaSedes",listaSedes);
            return "trabajadores/editar";
        } else {
            return "redirect:/trabajadores/listar";
        }
    }

    @GetMapping("/borrar")
    public String borrarTrabajadores(@RequestParam("dni") String dni) {
        Optional<Trabajadores> optionalTrabajador = trabajadoresRepository.findById(dni);
        if (optionalTrabajador.isPresent()) {
            trabajadoresRepository.deleteById(dni);
        }
        return "redirect:/trabajadores/listar";
    }
}
