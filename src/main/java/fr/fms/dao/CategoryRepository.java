package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.fms.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
	//1.6 afficher les noms des catégories classés par ordre croissant
	@Query("SELECT c FROM Category c ORDER BY c.name ASC")
	List<Category> findAllCategoriesSortedByNameAsc();
	
	//afficher les noms des catégories classés par ordre décroissant
	@Query("SELECT c FROM Category c ORDER BY c.name DESC")
	List<Category> findAllCategoriesSortedByNameDesc();
}

