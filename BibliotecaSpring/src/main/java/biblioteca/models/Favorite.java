package biblioteca.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Favorite implements Serializable  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="id_livro")
	private Livro livro;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")	
	private Usuario usuario;

	private String dataFavorite;

	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getDataBookUserManager() {
		return dataFavorite;
	}
	public void setDataFavorite(String dataFavorite) {
		this.dataFavorite = dataFavorite;
	}

	
	
}
