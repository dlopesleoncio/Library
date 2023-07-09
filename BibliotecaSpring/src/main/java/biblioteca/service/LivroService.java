/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.service;

import biblioteca.models.Livro;
import biblioteca.repositories.LivroRepository;
import static java.util.Objects.isNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author ficdev
 */
@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;
    
    
    public Livro getLivrobyIdService(Long id){
        return livroRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Livro não encontrado"));
                
    }
  
    
    public ResponseEntity<Void> deletarLivroService(Long id){
        
	   	Livro livro = getLivrobyIdService(id);
	        livroRepository.delete(livro);
	        return ResponseEntity.noContent().build();
    }
   
    
    public ResponseEntity<Livro> alterarLivroService(Long id, Livro novolivro){
        Livro livro = livroRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Livro não encontrado"));
        if (isNull(livro)) {
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
    
}