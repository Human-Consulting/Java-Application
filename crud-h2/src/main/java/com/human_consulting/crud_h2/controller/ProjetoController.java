package com.human_consulting.crud_h2.controller;

import com.human_consulting.crud_h2.repository.ProjetoRepository;
import com.human_consulting.crud_h2.model.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    @Autowired
    private ProjetoRepository repository;

    @PostMapping
    public ResponseEntity<Projeto> cadastrar(@RequestBody Projeto projeto){
        if (repository.existsByDescricaoContainingIgnoreCase(projeto.getDescricao())){
            return ResponseEntity.status(409).build();
        }

        Projeto projetoCadastrado = repository.save(projeto);
        return ResponseEntity.status(201).body(projetoCadastrado);
    }

    @GetMapping
    private ResponseEntity<List<Projeto>> listar(){
        List<Projeto> projetos = repository.findAll();

        if (projetos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(projetos);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Projeto> alterar(@PathVariable Integer id, @RequestBody Projeto projeto){
        if (repository.existsById(id)){
            projeto.setId(id);
            Projeto projetoAlterado = repository.save(projeto);
            return ResponseEntity.status(200).body(projetoAlterado);
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Projeto> deletar(@PathVariable Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }
}
