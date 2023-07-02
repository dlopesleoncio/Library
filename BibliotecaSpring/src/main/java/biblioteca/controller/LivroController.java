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
import biblioteca.repositories.LivroRepository;
@RestController
@RequestMapping("/api/livros")
public class LivroController {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	public LivroController(LivroRepository livroRepository) {
		this.livroRepository =  livroRepository;
	}
	
	@GetMapping
	public List<Livro> getLivros() {
		return livroRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> getLivro(@PathVariable Integer id){
		Livro livro = livroRepository.findById(id).orElse(null);
        if (livro != null) {
            return ResponseEntity.ok(livro);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@PostMapping
    @Transactional
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody Livro livro) {
		livroRepository.save(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livro);
    }
	
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Livro> alterarCategoria(@PathVariable int id, @RequestBody Livro novolivro) {
    	Livro livro = livroRepository.findById(id).orElse(null);
        if (livro != null) {
        	livro.setTitulo(novolivro.getTitulo());
        	livro.setAutor(novolivro.getAutor());
        	livro.setAnoPublicacao(novolivro.getAnoPublicacao());
        	livro.setDisponivel(novolivro.getDisponivel());

        	livroRepository.save(livro);
            return ResponseEntity.ok(livro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }	
	
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarProduto(@PathVariable int id) {
    	Livro livro = livroRepository.findById(id).orElse(null);
        if (livro != null) {
        	livroRepository.delete(livro);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
}