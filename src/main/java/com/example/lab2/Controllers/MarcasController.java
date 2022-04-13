package com.example.lab2.Controllers;

import com.example.lab2.Entity.Marcas;
import com.example.lab2.Repository.MarcasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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

    //EL jpa databinding no está linkeando el objeto marca que llega desde newFrm
    // Solo funciona si es envia desde edit
    @PostMapping("/save")
    public String guardarMarca(Marcas marcas,RedirectAttributes attr) {
        if (marcas.getId() == 0) {
            attr.addFlashAttribute("msg", "Marca creada exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Marca actualizada exitosamente");
        }
        marcasRepository.save(marcas);
        return "redirect:/marcas";
    }

    @GetMapping("/new")
    public String newMarcaForm(){
        return "marcas/newFrm";
    }


    @GetMapping("/edit")
    public String editarMarcas(Model model,@RequestParam("id") Integer id) {
        Optional<Marcas> optMarca = marcasRepository.findById(id);
        if(optMarca.isPresent()){
            Marcas marca = optMarca.get();
            model.addAttribute("marca",marca);
            return "marcas/editFrm";
        }else  {
            return "redirect:/marcas";
        }
    }

    @PostMapping("/delete")
    public String borrarMarcas(Model model, @RequestParam("id") int id, RedirectAttributes attr) {
        Optional<Marcas> optMarca = marcasRepository.findById(id);
        if(optMarca.isPresent()){
            marcasRepository.deleteById(id);
            attr.addFlashAttribute("msg","Marca borrada exitosamente");
        }
         return "redirect:/marcas";
    }




}
