package com.pi.crud_h2.entrega;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
    @Autowired
    private EntregaRepository repository;

    @PostMapping
    public ResponseEntity<Entrega> cadastrar(@RequestBody Entrega entrega){
        if (repository.existsByDescricaoContainingIgnoreCase(entrega.getDescricao())){
            return ResponseEntity.status(409).build();
        }

        Entrega entregaCadastrada = repository.save(entrega);
        return ResponseEntity.status(201).body(entregaCadastrada);
    }

    @GetMapping
    private ResponseEntity<List<Entrega>> listar(){
        List<Entrega> entregas = repository.findAll();

        if (entregas.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(entregas);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Entrega> alterar(@PathVariable Integer id, @RequestBody Entrega entrega){
        if (repository.existsById(id)){
            entrega.setId(id);
            Entrega entregaAlterada = repository.save(entrega);
            return ResponseEntity.status(200).body(entregaAlterada);
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Entrega> deletar(@PathVariable Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }
}
