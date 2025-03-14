package com.pi.crud_h2.empresa;

import com.pi.crud_h2.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaRepository repository;

    @PostMapping
    public ResponseEntity<Empresa> cadastrar(@RequestBody Empresa empresa){
        if (repository.existsByCnpj(empresa.getCnpj())){
            return ResponseEntity.status(409).build();
        }

        Empresa empresaCadastrada = repository.save(empresa);
        return ResponseEntity.status(201).body(empresaCadastrada);
    }

    @GetMapping
    private ResponseEntity<List<Empresa>> listar() {
        List<Empresa> empresas = repository.findAll();

        if (empresas.isEmpty()){
            return ResponseEntity.status(204).build();
        }

       return ResponseEntity.status(200).body(empresas);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Empresa> alterar(@PathVariable Integer id, @RequestBody Empresa empresa){
        if (repository.existsById(id)){
            empresa.setId(id);
            Empresa empresaAlterada = repository.save(empresa);
            return ResponseEntity.status(200).body(empresaAlterada);
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Empresa> deletar(@PathVariable Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }
}
