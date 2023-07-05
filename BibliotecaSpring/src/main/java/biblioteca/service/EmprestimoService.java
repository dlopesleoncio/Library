/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.service;

import biblioteca.models.Emprestimo;
import biblioteca.models.Livro;
import biblioteca.models.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author ficdev
 */
public class EmprestimoService {
    
    private LivroService livroService;
    private UsuarioService usuarioService;
    
    
    public void cadastrarEmprestimo(Emprestimo emprestimo){
        	Livro livro = emprestimo.getLivro();
		Usuario usuario = emprestimo.getUsuario();
		
		Livro livroexistente = livroService.getLivrobyIdService(livro.getId());
                
		if(livroexistente != null) {
			emprestimo.setLivro(livroexistente);
		}
		
		Usuario usuarioExistente = usuarioService
		if(usuarioExistente != null) {
			emprestimo.setUsuario(usuarioExistente);
		}
		
		emprestimoRepository.save(emprestimo);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo);
    }
}
