package biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.models.Livro;
import biblioteca.models.Usuario;
import biblioteca.repositories.LivroRepository;
import biblioteca.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository =  usuarioRepository;
	}
	
	@GetMapping
	public List<Usuario> getLivros() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable long id){
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@PostMapping
    @Transactional
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
		usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
	
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> alterarCategoria(@PathVariable long id, @RequestBody Usuario novoUsuario) {
    	Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
        	usuario.setNome(novoUsuario.getNome());
        	usuario.setCpf(novoUsuario.getCpf());
        	usuario.setEmail(novoUsuario.getEmail());
        	//usuario.setEmprestimos(novoUsuario.getEmprestimos());
        	usuario.setQtdLivros(novoUsuario.getQtdLivros());
        	usuario.setTelefone(usuario.getTelefone());
        	usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }	
	
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarProduto(@PathVariable long id) {
    	Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
        	usuarioRepository.delete(usuario);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
	
}
