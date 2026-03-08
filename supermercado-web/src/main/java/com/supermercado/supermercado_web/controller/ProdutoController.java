package com.supermercado.supermercado_web.controller;

import com.supermercado.supermercado_web.service.CategoriaService;
import com.supermercado.supermercado_web.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/produtos")
    public String listarProdutos(
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) Long categoriaId,
            Model model) {

        var produtos = busca != null && !busca.isEmpty()
                ? produtoService.buscarPorNome(busca)
                : categoriaId != null
                ? produtoService.buscarPorCategoria(categoriaId)
                : produtoService.listarTodos();

        model.addAttribute("produtos", produtos);
        model.addAttribute("categorias", categoriaService.listarTodas());
        model.addAttribute("busca", busca);
        model.addAttribute("categoriaId", categoriaId);

        return "produtos";
    }
}