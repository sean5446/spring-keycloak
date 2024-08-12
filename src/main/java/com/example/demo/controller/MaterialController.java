package com.example.demo.controller;

import com.example.demo.entity.Material;
import com.example.demo.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("")
    public String list(Principal principal, Model model) {
        model.addAttribute("materials", materialService.findAll());
        model.addAttribute("username", principal.getName());
        return "material/materials";
    }

    @GetMapping("/addMaterialModal")
    public String addMaterial() {
        return "material/modal :: modal";
    }

    @GetMapping("/getFakeMaterial")
    public String getFakeMaterial(Model model) {
        var material = materialService.createFakeMaterial();
        model.addAttribute("material", material);
        return "material/new-material-form :: frmNewMaterial";
    }

    @PostMapping("")
    public String save(@RequestBody Material material, Model model) {
        materialService.save(material);
        return "material/new-material-row :: row";
    }

}
