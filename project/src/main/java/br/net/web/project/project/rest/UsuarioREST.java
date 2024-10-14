package br.net.web.project.project.rest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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

import br.net.web.project.project.model.Cliente;
import br.net.web.project.project.model.Endereco;
import br.net.web.project.project.model.Funcionario;
import br.net.web.project.project.model.Login;
import br.net.web.project.project.model.Usuario;

@CrossOrigin
@RestController
public class UsuarioREST {

    public static List<Usuario> listaUsuarios = new ArrayList<>();
    public static List<Funcionario> listaFuncionarios = new ArrayList<>();
    public static List<Cliente> listaClientes = new ArrayList<>();


    @GetMapping("/usuarios")
    public List<Usuario> obterTodosUsuarios() {
        return listaUsuarios;
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable("id") int id) {
        Usuario u = listaUsuarios.stream().filter(usu -> usu.getId() == id).findAny().orElse(null);
            if (u==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            else
                return ResponseEntity.ok(u);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
        Usuario u = listaUsuarios.stream().filter(usu -> usu.getEmail().equals(usuario.getEmail())).findAny().orElse(null);
        if (u != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        u = listaUsuarios.stream().max(Comparator.comparing(Usuario::getId)).orElse(null);
        if (u == null)
            usuario.setId(1);
        else
            usuario.setId(u.getId() + 1);
            listaUsuarios.add(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        Usuario u = listaUsuarios.stream().filter(usu -> usu.getId() == id).findAny().orElse(null);
        if (u != null) {
            u.setNome(usuario.getNome());
            u.setEmail(usuario.getEmail());
            u.setSenha(usuario.getSenha());
            u.setPerfil(usuario.getPerfil());
            return ResponseEntity.ok(u);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> remover(@PathVariable("id") int id) {
        Usuario usuario = listaUsuarios.stream().filter(usu -> usu.getId() == id).findAny().orElse(null);
        if (usuario != null) {
            listaUsuarios.removeIf(u -> u.getId() == id);
            return ResponseEntity.ok(usuario);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        Usuario usuario = listaUsuarios.stream()
                .filter(u -> u.getEmail().equals(login.getEmail()) && 
                            verificarSenha(login.getSenha(), u.getSenha()))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos.");
        }

        String perfil = usuario instanceof Cliente ? "Cliente" : "Funcionário";
        return ResponseEntity.ok("Login realizado com sucesso. Perfil: " + perfil);
    }

    private boolean verificarSenha(String senhaDigitada, String senhaArmazenada) {
        // Comparar senha digitada com senha armazenada (descriptografada)
        // Implementação necessária
    }

    @GetMapping("/listaFuncionarios")
    public ResponseEntity<List<Funcionario>> obterTodasPessoas() {
        return ResponseEntity.ok(listaFuncionarios);
    }

    @GetMapping("/listaFuncionarios/{id}")
    public ResponseEntity<Funcionario> obterPessoaPorId(@PathVariable("id") int id) {
        Funcionario p = listaFuncionarios.stream().filter(pess -> pess.getId() == id).findAny().orElse(null);
        if (p==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.ok(p);
    }

    @PostMapping("/listaFuncionarios")
    public ResponseEntity<Funcionario> inserirFuncionario(@RequestBody Funcionario funcionario) {
        if (listaFuncionarios.stream().anyMatch(f -> f.getEmail().equals(funcionario.getEmail()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    
        funcionario.setId(listaFuncionarios.size() + 1);
        listaFuncionarios.add(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }

    @PutMapping("/listaFuncionarios/{id}")
    public ResponseEntity<Funcionario> alterar(@PathVariable("id") int id, @RequestBody Funcionario funcionario) {
        Funcionario p = listaFuncionarios.stream().filter(func -> func.getId() == id).findAny().orElse(null);
        if (p != null) {
            p.setNome(funcionario.getNome());
            p.setDataNascimento(funcionario.getDataNascimento());
            return ResponseEntity.ok(p);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/listaFuncionarios/{id}")
    public ResponseEntity<String> removerFuncionario(@PathVariable("id") int id) {
        Funcionario funcionario = listaFuncionarios.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    
        if (funcionario == null || listaFuncionarios.size() == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possível remover este funcionário.");
        }
    
        listaFuncionarios.remove(funcionario);
        return ResponseEntity.ok("Funcionário removido com sucesso.");
    }
    
    private String criptografarSenha(String senha) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        md.update(salt);
        byte[] hashedPassword = md.digest(senha.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedPassword);
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException("Erro ao criptografar senha", e);
    }
}


    @PostMapping("/clientes/autocadastro")
    public ResponseEntity<String> autocadastro(@RequestBody Cliente cliente) {
        // Verificar se o CPF ou e-mail já existe
        if (listaClientes.stream().anyMatch(c -> c.getCpf().equals(cliente.getCpf()) || 
                                                c.getEmail().equals(cliente.getEmail()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF ou E-mail já cadastrado");
        }

        // Gerar senha aleatória de 4 dígitos
        String senhaGerada = String.format("%04d", new Random().nextInt(10000));
        cliente.setSenha(criptografarSenha(senhaGerada));

        // Consultar API ViaCEP para preencher endereço
        Endereco endereco = viaCepService.consultarCep(cliente.getCep());
        cliente.setEnderecoCompleto(endereco);

        listaUsuarios.add(cliente);

        // Enviar senha por e-mail (simulação)
         emailService.enviarSenha(cliente.getEmail(), senhaGerada);

        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso. Verifique seu e-mail para a senha.");
    }




    static {
        listaUsuarios.add(
            new Usuario(1, "administr", "admin", "admin", "ADMIN"));
        listaUsuarios.add(
            new Usuario(2, "gerent", "gerente", "gerente", "GERENTE"));
        listaUsuarios.add(
            new Usuario(3, "funcion", "func", "func", "FUNC"));
    }
}
