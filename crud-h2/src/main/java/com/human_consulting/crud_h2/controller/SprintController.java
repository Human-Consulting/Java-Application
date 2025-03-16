package com.human_consulting.crud_h2.controller;

import com.human_consulting.crud_h2.repository.SprintRepository;
import com.human_consulting.crud_h2.model.Sprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sprints")
public class SprintController {
    @Autowired
    private SprintRepository repository;

    @PostMapping
    public ResponseEntity<Sprint> cadastrar(@RequestBody Sprint sprint){
        if (repository.existsByDescricaoContainingIgnoreCase(sprint.getDescricao())){
            return ResponseEntity.status(409).build();
        }

        Sprint sprintCadastrada = repository.save(sprint);
        return ResponseEntity.status(201).body(sprintCadastrada);
    }

    @GetMapping
    private ResponseEntity<List<Sprint>> listar(){
        List<Sprint> sprints = repository.findAll();

        if (sprints.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(sprints);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Sprint> alterar(@PathVariable Integer id, @RequestBody Sprint sprint){
        if (repository.existsById(id)){
            sprint.setId(id);
            Sprint sprintAlterada = repository.save(sprint);
            return ResponseEntity.status(200).body(sprintAlterada);
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Sprint> deletar(@PathVariable Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }
}
