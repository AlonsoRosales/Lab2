package com.example.lab2.Controllers;

import com.example.lab2.Entity.Inventario;
import com.example.lab2.Entity.Marcas;
import com.example.lab2.Repository.InventarioRepository;
import com.example.lab2.Repository.MarcasRepository;
import com.example.lab2.Repository.SedeRepository;
import com.example.lab2.Repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inventario")
public class InventarioController {
    @Autowired
    InventarioRepository inventarioRepository;
    @Autowired
    SedeRepository sedeRepository;
    @Autowired
    MarcasRepository marcasRepository;
    @Autowired
    TipoRepository tipoRepository;

    @GetMapping(value={"","/"})
    public String listarInventario(Model model){
        model.addAttribute("listaInventario",inventarioRepository.findAll());
        return "inventario/lista";
    }
    @GetMapping("/new")
    public String newInventario(Model model){
        model.addAttribute("listaSedes",sedeRepository.findAll());
        model.addAttribute("listaMarcas",marcasRepository.findAll());
        model.addAttribute("listaEquipos",tipoRepository.findAll());
        List<String> listaEstados = new ArrayList<>();
        listaEstados.add("Almacen");
        listaEstados.add("Pedido");
        listaEstados.add("Vendido");
        model.addAttribute("listaEstados",listaEstados);
        return "inventario/newFrm";
    }



    @GetMapping("/edit")
    public String editarInventario(Model model,@RequestParam("id") Integer id) {
        Optional<Inventario> optInventario = inventarioRepository.findById(id);
        if(optInventario.isPresent()){
            Inventario inventario = optInventario.get();
            model.addAttribute("inventario",inventario);
            model.addAttribute("listaSedes",sedeRepository.findAll());
            model.addAttribute("listaMarcas",marcasRepository.findAll());
            model.addAttribute("listaEquipos",tipoRepository.findAll());
            List<String> listaEstados = new ArrayList<>();
            if(inventario.getEstado().equalsIgnoreCase("Almacen")){
                listaEstados.add("Almacen");
                listaEstados.add("Pedido");
                listaEstados.add("Vendido");
            }else if(inventario.getEstado().equalsIgnoreCase("Pedido")){
                listaEstados.add("Pedido");
                listaEstados.add("Almacen");
                listaEstados.add("Vendido");
            }else{
                listaEstados.add("Vendido");
                listaEstados.add("Almacen");
                listaEstados.add("Pedido");
            }
            model.addAttribute("listaEstados",listaEstados);
            return "inventario/editar";
        }else  {
            return "redirect:/marcas";
        }
    }


    @PostMapping("/save")
    public String guardarInventario(Inventario inventario, RedirectAttributes attr) {
        attr.addFlashAttribute("msg1", "Inventario actualizada exitosamente");
        inventarioRepository.save(inventario);
        return "redirect:/inventario";
    }

    @PostMapping("/register")
    public String registrarinventario(Inventario inventario, RedirectAttributes attr) {
        attr.addFlashAttribute("msg", "Inventario creada exitosamente");
        inventarioRepository.save(inventario);
        return "redirect:/inventario";
    }
    @GetMapping("/delete")
    public String borrarInventario(Model model,@RequestParam("id") Integer id, RedirectAttributes attr){
        Optional<Inventario> optionalInventario = inventarioRepository.findById(id);
        if (optionalInventario.isPresent()){
            inventarioRepository.deleteById(id);
            attr.addFlashAttribute("msg2","Inventario borrado exitosamente");
        }
        return "redirect:/inventario";
    }

}
