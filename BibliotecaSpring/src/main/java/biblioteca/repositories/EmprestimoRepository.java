package biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import biblioteca.models.Emprestimo;

@Repository
@EnableJpaRepositories
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer>  {

}
