package org.sid.billingservice;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
class Bill{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date billingDate;
	private long customerId;
	@Transient // attribut qui n'est pas persistant
	private Customer customer;
	@OneToMany(mappedBy = "bill")
	private Collection <ProductItem> ProductItems;
	
}

@Entity @NoArgsConstructor @AllArgsConstructor @Data
class ProductItem{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long productId;
	@Transient
	private Product product;
	private int quantity;
	private double price;
	@ManyToOne
	private Bill bill;
}
@RepositoryRestResource
interface BillRepository extends JpaRepository<Bill,Long>{}
@RepositoryRestResource
interface ProductItemRepository extends JpaRepository<ProductItem,Long>{}

@Data
class Customer{
	private Long id;
	private String name;
	private String email;
}

@Data
class Product{
	private Long id;
	private String name;
	private double price;
}


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
@Autowired
BillRepository billRepository;
@Autowired
ProductItemRepository productItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Bill bill = billRepository.save(new Bill(null, new Date(), 1L, null, null));
		productItemRepository.save(new ProductItem(null,1L,null,2,500.0,bill));
		productItemRepository.save(new ProductItem(null,2L,null,7,900.0,bill));
		productItemRepository.save(new ProductItem(null,3L,null,3,400.0,bill));
	}

}
