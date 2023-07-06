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
public class Usuario implements Serializable  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String cpf;
    private String nome;
    private String telefone;
    private int qtdLivros;
    private String email;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Favorite> emprestimos;
    
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getQtdLivros() {
		return qtdLivros;
	}
	public void setQtdLivros(int qtdLivros) {
		this.qtdLivros = qtdLivros;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	//public List<BookUserManager> getbookUserManager() {
	//	return emprestimos;
	//}
	//public void setEmprestimos(List<BookUserManager> emprestimos) {
	//	this.emprestimos = emprestimos;
	//}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	
}
