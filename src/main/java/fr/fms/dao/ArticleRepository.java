package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fr.fms.entities.Articles;

public interface ArticleRepository extends JpaRepository<Articles,Long> {
	public List<Articles> findByBrand(String brand);
	public List<Articles> findByBrandContains(String brand);
	public List<Articles> findByBrandAndPrice(String brand, double price);
	public List<Articles> findByBrandAndPriceGreaterThan(String brand, double price);
	public List<Articles> findByBrandContainsAndPriceGreaterThan(String brand, double price);
	public List<Articles> findByCategoryId(Long categoryId);
	
	//1.2 afficher 1 article et tous les articles
	@Query("SELECT a FROM Articles a WHERE a.id = :id")
    Articles findArticleById(@Param("id") Long id);
	
	 @Query("SELECT a FROM Articles a")
	 List<Articles> findAllArticles();
	 
	 //1.3 renvoyer tous les articles contenants telle description et marque
	 @Query("SELECT a FROM Articles a WHERE a.description LIKE %:description% AND a.brand = :brand")
	 List<Articles> findByDescriptionAndBrand(@Param("description") String description, @Param("brand") String brand);
	 
	 // 1.5 update par Id 
	 @Modifying
	 @Transactional
	 @Query("update Articles a SET a.description = :description, a.price = :price where a.id = :id ")
	 void updateArticleById(@Param("id") Long id, @Param("description") String description, @Param("price") double price);
	 
	 
	 // 1.7 choix de la méthode : afficher les articles suivant une plage de prix
	 @Query("SELECT a FROM Articles a WHERE a.price BETWEEN :minPrice AND :maxPrice")
	 List<Articles> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
	 	 
}
