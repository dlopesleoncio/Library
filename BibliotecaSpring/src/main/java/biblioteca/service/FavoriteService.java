/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.service;

import biblioteca.models.Favorite;
import biblioteca.models.Livro;
import biblioteca.models.Usuario;
import biblioteca.repositories.FavoritesRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author ficdev
 */
@Service
public class FavoriteService {
    
	private FavoritesRepository favoritepository;
    private LivroService livroService;
    private UsuarioService usuarioService;
    
    
    public ResponseEntity<Favorite> cadastrarFavorite(Favorite favorites){
        Livro livro = favorites.getLivro();
		Usuario usuario = favorites.getUsuario();
		
		Livro livroexistente = livroService.getLivrobyIdService(livro.getId());
                
		if(livroexistente != null) {
			favorites.setLivro(livroexistente);
		}
		
		Usuario usuarioExistente = usuarioService.getUsuarioByIdService(usuario.getId());
		if(usuarioExistente != null) {
			favorites.setUsuario(usuarioExistente);
		}
		
		favoritepository.save(favorites);
        return ResponseEntity.status(HttpStatus.CREATED).body(favorites);
    }
    
    
    public List<Favorite> getFavoritesByUserIdService(Integer Id){
    	return favoritepository.findAllByUserId(Id);
    	
    }
}
