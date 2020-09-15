package org.sid.inventoryservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
class Product{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private String name;
	private Double price;
}

@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product,Long>{}

@SpringBootApplication
public class DemoInventory implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoInventory.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productRepository.save(new Product(null, "fraises", 60.0));
		productRepository.save(new Product(null, "mangues", 40.0));
		productRepository.save(new Product(null, "yaourt", 70.0));
		productRepository.findAll().forEach(System.out::println);
	}

}
