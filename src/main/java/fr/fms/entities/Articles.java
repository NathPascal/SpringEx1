package fr.fms.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Articles implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String brand;
	private String description;
	private double price;
	
	@ManyToOne
	private Category category;
	
	public Articles() {
	}
	
	//public Articles(String brand, String description, double price) {
		//this.brand = brand;
       // this.description = description;
        //this.price = price;
	//}
	
	//public Articles(Long id, String brand, String description, double price) {
		//this.id = id;
		//this.brand = brand;
		//this.description = description;
		//this.price = price;
	//}
	
	public Articles(String brand, String description, double price, Category category) {
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	
	@Override
    public String toString() {
       return "Articles [id=" + id + ", brand=" + brand + ", description=" + description + ", price=" + price + ", category=" + category + "]";
		
    }

}	
	