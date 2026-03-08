package com.supermercado.supermercado_web.controller;

import com.supermercado.supermercado_web.model.Usuario;
import com.supermercado.supermercado_web.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Usuario usuario, Model model) {
        if (usuarioService.emailJaExiste(usuario.getEmail())) {
            model.addAttribute("erro", "Email já cadastrado!");
            return "cadastro";
        }
        usuarioService.salvar(usuario);
        return "redirect:/login";
    }
}