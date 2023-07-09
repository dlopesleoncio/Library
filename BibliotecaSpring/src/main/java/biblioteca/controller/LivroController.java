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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.models.Livro;
import biblioteca.repositories.LivroRepository;
import biblioteca.service.LivroService;
@RestController
@RequestMapping("/api/livros")
public class LivroController {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private LivroService livroService;        
        
	public LivroController(LivroRepository livroRepository) {
		this.livroRepository =  livroRepository;
	}
	
	@GetMapping
	public List<Livro> getLivros() {
		return livroRepository.findAll();
	}
	
	@GetMapping("categoria/{categoriaPath}")
	public List<Livro> geyLivroByCategoria(@PathVariable String categoriaPath){
		return livroRepository.findAllLivroByCategoria(categoriaPath);
	}
	
	
	@GetMapping("nomes")
	@ResponseBody
	public List<Livro> getLivros(@RequestParam String titulo) {
		return livroRepository.findAllLivroByTituloContainingIgnoreCaseAndImgpathIsNotNull(titulo);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> getLivro(@PathVariable Long id){
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
    public ResponseEntity<Livro> alterarLivro(@PathVariable Long id, @RequestBody Livro novolivro) {
        return livroService.alterarLivroService(id, novolivro);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {

        return livroService.deletarLivroService(id);
    }
	
}