package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Articles;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringShopJpaApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception{
		Category smartphone = categoryRepository.save(new Category("Smartphone"));
		Category tablet = categoryRepository.save(new Category("Tablet"));
		Category pc = categoryRepository.save(new Category("PC"));
		
		articleRepository.save(new Articles("Samsung","S9",250, smartphone));
		articleRepository.save(new Articles("Samsung","S8",250, smartphone));
		articleRepository.save(new Articles("Xiaomi","MI10",250, smartphone));
		
		articleRepository.save(new Articles("Samsung","GalaxyTab",350, tablet));
		articleRepository.save(new Articles("Apple","Ipad",350, tablet));
		
		articleRepository.save(new Articles("Asus","R510",600, pc));
		
		
		for(Articles articles : articleRepository.findByBrand("Samsung")) {
			System.out.println(articles);
		}
		
		for(Articles articles : articleRepository.findByBrandContains("sung")) {
			System.out.println(articles);
		}
		
		for(Articles articles : articleRepository.findByBrandAndPrice("Samsung",250)) {
			System.out.println(articles);
		}
		
		for(Articles articles: articleRepository.findByBrandAndPriceGreaterThan("Samsung",300)){
			System.out.println(articles);
		}
		
		for(Articles articles : articleRepository.findByBrandContainsAndPriceGreaterThan("sung",200)) {
			System.out.println(articles);
		}
		
		
		//1.2 afficher 1 article et tous les articles
		Articles articles = articleRepository.findArticleById(1L); 
		System.out.println(articles);
		
		
        for (Articles allArticles : articleRepository.findAll()) {
            System.out.println(allArticles);
        }
        
        //1.3 renvoyer tous les articles contenants telle description et marque
        for (Articles filtredArticles : articleRepository.findByDescriptionAndBrand("S9", "Samsung")) {
            System.out.println(filtredArticles);
        }
        
      //1.4 supprimer 1 article à partir de l'id
        Long articleIdToDelete = 1L;
        articleRepository.deleteById(articleIdToDelete);
        System.out.println("L'article avec l'ID " + articleIdToDelete + " est supprimé.");
        
      //1.5  mettre à jour un article à partir de l’id
        Long articleIdToUpdate = 2L;
        articleRepository.updateArticleById(articleIdToUpdate, "S8.0.1select", 300);
        System.out.println("L'article avec l'ID " + articleIdToUpdate + " est mis à jour.");
        
      //1.6 afficher les noms des catégories classés par ordre croissant
        for (Category category : categoryRepository.findAllCategoriesSortedByNameAsc()) {
        	System.out.println(category);
        }
        
      // afficher les noms des catégories classés par ordre décroissant
        for (Category category : categoryRepository.findAllCategoriesSortedByNameDesc()) {
        	System.out.println(category);
        }
		
     // 1.7 choix de la méthode : afficher les articles suivant une plage de prix
       for (Articles article : articleRepository.findByPriceRange(300,400)) {
       		System.out.println(article);
       }
	}

}
