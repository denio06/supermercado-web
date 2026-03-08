package com.supermercado.supermercado_web.repository;

import com.supermercado.supermercado_web.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCategoria_Id(Long categoriaId);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}