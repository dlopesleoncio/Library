package biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import biblioteca.models.Favorite;

@Repository
@EnableJpaRepositories
public interface FavoritesRepository extends JpaRepository<Favorite, Integer>  {
	
    @Query("SELECT f FROM Favorite f WHERE f.usuario = :id")
    List<Favorite> findAllByUserId(Integer id);	
}
