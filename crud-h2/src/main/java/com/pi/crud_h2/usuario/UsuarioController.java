package com.pi.crud_h2.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        if (repository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        Usuario usuarioCadastrado = repository.save(usuario);
        return ResponseEntity.status(201).body(usuarioCadastrado);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping
    private ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = repository.findAll();

        if (usuarios.size() > 0) {
            return ResponseEntity.status(200).body(usuarios);
        }

        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Usuario> alterar(@PathVariable Integer id, @RequestBody Usuario usuarioParaAlterar, @PathVariable Integer idLogado) {
        if (repository.existsById(id) && verificarPermissao(idLogado)) {
                usuarioParaAlterar.setId(id);
                Usuario usuarioAlterado = repository.save(usuarioParaAlterar);
                return ResponseEntity.status(200).body(usuarioAlterado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Usuario> deletar(@PathVariable Integer id, @PathVariable Integer idLogado) {
        if (repository.existsById(id) && verificarPermissao(idLogado)) {
                repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/verificar/{id}")
    private Boolean verificarPermissao(@PathVariable Integer idLogado){
        String cargoUsuario = repository.findById(idLogado).get().getCargo();

        if (cargoUsuario.equalsIgnoreCase("Diretor") || cargoUsuario.equalsIgnoreCase("Gestor") || cargoUsuario.equalsIgnoreCase("Consultor Principal")){
            return true;
        }

        return false;
    }
}
