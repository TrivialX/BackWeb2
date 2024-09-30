package br.net.web.project.project.rest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.web.project.project.model.Funcionario;
import br.net.web.project.project.model.Login;
import br.net.web.project.project.model.Usuario;

@CrossOrigin
@RestController
public class UsuarioREST {

    public static List<Usuario> lista = new ArrayList<>();
    public static List<Funcionario> funcionarios = new ArrayList<>();

    @GetMapping("/usuarios")
    public List<Usuario> obterTodosUsuarios() {
        return lista;
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable("id") int id) {
        Usuario u = lista.stream().filter(usu -> usu.getId() == id).findAny().orElse(null);
            if (u==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            else
                return ResponseEntity.ok(u);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
        Usuario u = lista.stream().filter(usu -> usu.getLogin().equals(usuario.getLogin())).findAny().orElse(null);
        if (u != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        u = lista.stream().max(Comparator.comparing(Usuario::getId)).orElse(null);
        if (u == null)
            usuario.setId(1);
        else
            usuario.setId(u.getId() + 1);
            lista.add(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        Usuario u = lista.stream().filter(usu -> usu.getId() == id).findAny().orElse(null);
        if (u != null) {
            u.setNome(usuario.getNome());
            u.setLogin(usuario.getLogin());
            u.setSenha(usuario.getSenha());
            u.setPerfil(usuario.getPerfil());
            return ResponseEntity.ok(u);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> remover(@PathVariable("id") int id) {
        Usuario usuario = lista.stream().filter(usu -> usu.getId() == id).findAny().orElse(null);
        if (usuario != null) {
            lista.removeIf(u -> u.getId() == id);
            return ResponseEntity.ok(usuario);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Login login) {
        Usuario usuario = lista.stream().
            filter(usu -> usu.getLogin().equals(login.getLogin()) && 
                          usu.getSenha().equals(login.getSenha())).
            findAny().orElse(null);
        if (usuario==null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        else
            return ResponseEntity.ok(usuario);
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> obterTodasPessoas() {
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> obterPessoaPorId(@PathVariable("id") int id) {
        Funcionario p = funcionarios.stream().filter(pess -> pess.getId() == id).findAny().orElse(null);
        if (p==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.ok(p);
    }

    @PostMapping("/funcionarios")
    public ResponseEntity<Funcionario> inserirPessoa(@RequestBody Funcionario funcionario) {
        Funcionario p = funcionarios.stream().filter(func -> func.getNome().equals(funcionario.getNome())).findAny().orElse(null);
        if (p != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        p = funcionarios.stream().max(Comparator.comparing(Funcionario::getId)).orElse(null);
        if (p == null)
            funcionario.setId(1);
        else
            funcionario.setId(p.getId() + 1);
            funcionarios.add(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> alterar(@PathVariable("id") int id, @RequestBody Funcionario funcionario) {
        Funcionario p = funcionarios.stream().filter(func -> func.getId() == id).findAny().orElse(null);
        if (p != null) {
            p.setNome(funcionario.getNome());
            p.setIdade(funcionario.getIdade());
            p.setDataNascimento(funcionario.getDataNascimento());
            p.setMotorista(funcionario.getMotorista());
            return ResponseEntity.ok(p);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> removerPessoa(@PathVariable("id") int id) {
        Funcionario funcionario = funcionarios.stream().filter(pess -> pess.getId() == id).findAny().orElse(null);
    if (funcionario != null) {
        funcionarios.removeIf(p -> p.getId() == id);
    return ResponseEntity.ok(funcionario);
    }
    else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    }

    static {
        lista.add(
            new Usuario(1, "administr", "admin", "admin", "ADMIN"));
        lista.add(
            new Usuario(2, "gerent", "gerente", "gerente", "GERENTE"));
        lista.add(
            new Usuario(3, "funcion", "func", "func", "FUNC"));
    }
}
