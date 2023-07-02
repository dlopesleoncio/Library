package biblioteca.controller;

import java.util.List;

import org.apache.el.stream.Optional;
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

import biblioteca.models.Emprestimo;
import biblioteca.models.Livro;
import biblioteca.models.Usuario;
import biblioteca.repositories.EmprestimoRepository;
import biblioteca.repositories.LivroRepository;
import biblioteca.repositories.UsuarioRepository;


@RestController
@RequestMapping("/api/emprestimo")
public class EmprestimoController {
	
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//@Autowired
/*	public EmprestimoController(EmprestimoRepository emprestimoRepository,LivroRepository livroRepository,UsuarioRepository usuarioRepository  ) {
		this.emprestimoRepository =  emprestimoRepository;
		this.livroRepository = livroRepository;
		this.usuarioRepository = usuarioRepository;
	}*/
	@Autowired
	public EmprestimoController(EmprestimoRepository emprestimoRepository) {
		this.emprestimoRepository =  emprestimoRepository;
	}
	
	
	
	
	@GetMapping
	public List<Emprestimo> getEmprestimos() {
		return emprestimoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Emprestimo> getEmprestimo(@PathVariable Integer id){
		Emprestimo emprestimo = emprestimoRepository.findById(id).orElse(null);
        if (emprestimo != null) {
            return ResponseEntity.ok(emprestimo);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@PostMapping
    @Transactional
    public ResponseEntity<Emprestimo> cadastrarLivro(@RequestBody Emprestimo emprestimo) {
		Livro livro = emprestimo.getLivro();
		Usuario usuario = emprestimo.getUsuario();
		
		Livro livroexistente = livroRepository.findById(livro.getId()).orElse(null);
		if(livroexistente != null) {
			emprestimo.setLivro(livroexistente);
		}
		
		Usuario usuarioExistente = usuarioRepository.findById(usuario.getId()).orElse(null);
		if(usuarioExistente != null) {
			emprestimo.setUsuario(usuarioExistente);
		}
		
		emprestimoRepository.save(emprestimo);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo);
    }
	
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Emprestimo> alterarCategoria(@PathVariable int id, @RequestBody Emprestimo novoEmprestimo) {
    	Emprestimo emprestimo = emprestimoRepository.findById(id).orElse(null);
        if (emprestimo != null) {
        	emprestimo.setLivro(emprestimo.getLivro());
        	emprestimo.setUsuario(novoEmprestimo.getUsuario());
        	emprestimo.setDataEmprestimo(novoEmprestimo.getDataEmprestimo());
        	emprestimo.setDataDevolucao(novoEmprestimo.getDataDevolucao());

        	emprestimoRepository.save(emprestimo);
            return ResponseEntity.ok(emprestimo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }	
	
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarProduto(@PathVariable int id) {
    	Emprestimo emprestimo = emprestimoRepository.findById(id).orElse(null);
        if (emprestimo != null) {
        	emprestimoRepository.delete(emprestimo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}