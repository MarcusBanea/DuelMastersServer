package com.duelmasters.DuelMastersServer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.duelmasters.DuelMastersServer.Repository.CardRepository;

@SpringBootApplication
public class DuelMastersServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuelMastersServerApplication.class, args);
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
