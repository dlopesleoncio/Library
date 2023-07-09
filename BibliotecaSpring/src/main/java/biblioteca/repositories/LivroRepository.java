package biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import biblioteca.models.Livro;

@Repository
@EnableJpaRepositories
public interface LivroRepository extends JpaRepository<Livro, Long> {
	List<Livro> findAllLivroByTituloContainingIgnoreCaseAndImgpathIsNotNull(String titulo);
	
	List<Livro> findAllLivroByCategoria(String categoria);
}
