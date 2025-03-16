package com.human_consulting.crud_h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financeiros")
public class FinanceiroController {
    @Autowired
    private FinanceiroRepository repository;

    @PostMapping
    public ResponseEntity<Financeiro> cadastrar(@RequestBody Financeiro financeiro){
        Financeiro financeiroCadastrado = repository.save(financeiro);
        return ResponseEntity.status(201).body(financeiroCadastrado);
    }

    @GetMapping
    private ResponseEntity<List<Financeiro>> listar(){
        List<Financeiro> financeiro = repository.findAll();

        if (financeiro.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(financeiro);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Financeiro> alterar(@PathVariable Integer id, @RequestBody Financeiro financeiro){
        if (repository.existsById(id)){
            financeiro.setId(id);
            Financeiro financeiroAlterado = repository.save(financeiro);
            return ResponseEntity.status(200).body(financeiroAlterado);
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Financeiro> deletar(@PathVariable Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }
}
