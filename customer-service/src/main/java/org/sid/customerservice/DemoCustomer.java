package org.sid.customerservice;
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
@Data @NoArgsConstructor @AllArgsConstructor @ToString
class Customer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;		
}

@RepositoryRestResource
interface CustomerRepository extends JpaRepository<Customer, Long>{}

@SpringBootApplication
public class DemoCustomer implements CommandLineRunner {
	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoCustomer.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerRepository.save(new Customer(null,"michel","michelsobgui@gmail.com"));
		customerRepository.save(new Customer(null, "patou", "patou@hotmail.com"));
		customerRepository.save(new Customer(null, "ivan", "ivan@yahoo.com"));
		customerRepository.save(new Customer(null, "karelle", "karelle@poly.ca"));
		// affichage des donnees
		customerRepository.findAll().forEach(System.out::println);
	}

}
