package biblioteca.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Livro implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
    private String autor;
    private long nroPaginas;
    private boolean disponivel;
    private String categoria;
    private int anoPublicacao;
    private String img_paths;
    
	@OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Emprestimo> emprestimos;
	
	

    
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public boolean getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	public int getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getNroPaginas() {
		return nroPaginas;
	}
	public void setNroPaginas(long nroPaginas) {
		this.nroPaginas = nroPaginas;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getImg_paths() {
		return img_paths;
	}
	public void setImg_paths(String img_paths) {
		this.img_paths = img_paths;
	}
	
	
	
	
	
}
