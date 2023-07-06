package biblioteca.controller;

import java.util.ArrayList;
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

import biblioteca.models.Favorite;
import biblioteca.models.Livro;
import biblioteca.models.Usuario;

import biblioteca.repositories.FavoritesRepository;
import biblioteca.repositories.LivroRepository;
import biblioteca.repositories.UsuarioRepository;
import biblioteca.service.FavoriteService;


@RestController
@RequestMapping("/api/favoritos")
public class FavoriteController {
	
	
	@Autowired
	private FavoritesRepository favoritesrepository;
	
	@Autowired
	private FavoriteService favoriteService;
	
	//@Autowired
/*	public EmprestimoController(EmprestimoRepository emprestimoRepository,LivroRepository livroRepository,UsuarioRepository usuarioRepository  ) {
		this.emprestimoRepository =  emprestimoRepository;
		this.livroRepository = livroRepository;
		this.usuarioRepository = usuarioRepository;
	}*/
	@Autowired
	public FavoriteController(FavoritesRepository favoritesrepository) {
		this.favoritesrepository =  favoritesrepository;
	}
	

	@GetMapping
	public List<Favorite> getFavorites() {
		return favoritesrepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Favorite> getFavorite(@PathVariable Integer id){
		Favorite favorites = favoritesrepository.findById(id).orElse(null);
        if (favorites != null) {
            return ResponseEntity.ok(favorites);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@GetMapping("/user/{id}")
	public List<Livro> getFavoritesByUserId(@PathVariable Integer id){
		 List<Favorite> favoritos= favoritesrepository.findAllByUserId(id);
		 
		 //---Tudo isso tem que ficar dentro do Favorite Service----
		 List<Livro> livros = new ArrayList<>();
		 for( Favorite favorito:favoritos){
			 
			 livros.add(favorito.getLivro());
			 
		 }
		 return livros;
	}
	
    @PostMapping
    @Transactional
    public ResponseEntity<Favorite> cadastrarEmprestimo(@RequestBody Favorite favorites) {
    	return favoriteService.cadastrarFavorite(favorites);
    }
	
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Favorite> alterarEmprestimo(@PathVariable int id, @RequestBody Favorite novoFavorites) {
    	Favorite emprestimo = favoritesrepository.findById(id).orElse(null);
        if (emprestimo != null) {
        	emprestimo.setLivro(emprestimo.getLivro());
        	emprestimo.setUsuario(novoFavorites.getUsuario());
        	favoritesrepository.save(emprestimo);
            return ResponseEntity.ok(emprestimo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }	
	
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable int id) {
    	Favorite favorite = favoritesrepository.findById(id).orElse(null);
        if (favorite != null) {
        	favoritesrepository.delete(favorite);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
