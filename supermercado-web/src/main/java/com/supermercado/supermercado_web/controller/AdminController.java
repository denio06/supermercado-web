package com.supermercado.supermercado_web.controller;

import com.supermercado.supermercado_web.model.Categoria;
import com.supermercado.supermercado_web.model.Produto;
import com.supermercado.supermercado_web.service.CategoriaService;
import com.supermercado.supermercado_web.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalProdutos", produtoService.listarTodos().size());
        model.addAttribute("totalCategorias", categoriaService.listarTodas().size());
        return "admin/dashboard";
    }

    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "admin/produtos";
    }

    @GetMapping("/produtos/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/produto-form";
    }

    @PostMapping("/produtos/salvar")
    public String salvarProduto(@ModelAttribute Produto produto) {
        produtoService.salvar(produto);
        return "redirect:/admin/produtos";
    }

    @GetMapping("/produtos/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        produtoService.buscarPorId(id).ifPresent(p -> model.addAttribute("produto", p));
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/produto-form";
    }

    @GetMapping("/produtos/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoService.deletar(id);
        return "redirect:/admin/produtos";
    }

    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/categorias";
    }

    @GetMapping("/categorias/nova")
    public String novaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/categoria-form";
    }

    @PostMapping("/categorias/salvar")
    public String salvarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.salvar(categoria);
        return "redirect:/admin/categorias";
    }

    @GetMapping("/categorias/deletar/{id}")
    public String deletarCategoria(@PathVariable Long id) {
        categoriaService.deletar(id);
        return "redirect:/admin/categorias";
    }
}