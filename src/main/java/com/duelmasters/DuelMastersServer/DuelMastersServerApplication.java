package com.duelmasters.DuelMastersServer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.duelmasters.DuelMastersServer.Repository.CardRepository;
import com.duelmasters.DuelMastersServer.Service.DAO.UserService;

@SpringBootApplication
public class DuelMastersServerApplication {
	
	static UserService userService;
	
	public DuelMastersServerApplication(UserService service) {
		userService = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(DuelMastersServerApplication.class, args);
		
		//User user = new User("Markus", "markus@email.com", "TheLegend27", null, 10000, null, null);
		//userService.createUser(user);
	}

	@Bean
	CommandLineRunner runner(CardRepository repository){
		return args -> {
			/*
			Card card = new Card(
				"Alcadeias, Lord of Spirits",
					"Light",
					"Angel Command",
					6,
					12500,
					2,
					true,
					"Players can't cast spells other than light spells."
			);

			Card card2 = new Card(
					"Ballom, Master of Death",
					"Dark",
					"Demon Command",
					8,
					12000,
					2,
					true,
					"When you put this creature into the battle zone," +
							" destroy all creatures except darkness creatures."
			);

			if(repository.findByName("Ballom, Master of Death").isPresent()){
				System.out.println(card2.getName() + " card already exists!");
			}
			else{
				System.out.println(card2.getName() + " card is now inserted!");
				repository.insert(card2);
			}
			 */
		};
	}

}
