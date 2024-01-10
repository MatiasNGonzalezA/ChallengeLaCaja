package com.challenge_java.lacaja;

import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.repository.IPersonaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.challenge_java.lacaja.repository")
@EntityScan("com.challenge_java.lacaja.model")
public class LacajaApplication {

	static Logger logger = LogManager.getLogger(LacajaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LacajaApplication.class, args);
		logger.info("===========================================");
		logger.info("============    APP RUNNING    ============");
		logger.info("===========================================");
	}


	@Bean
	public CommandLineRunner initData(IPersonaRepository personaRepository){

		return args -> {

			Persona persona1 = new Persona("Matias", 25, "Quilmes");
			Persona persona2 = new Persona("Raul", 30, "Moreno");
			Persona persona3 = new Persona("Marcos", 35, "Palermo");
			Persona persona4 = new Persona("Jorge", 21, "Recoleta");
			Persona persona5 = new Persona("Micaela", 28, "Avellaneda");


			personaRepository.save(persona1);
			personaRepository.save(persona2);
			personaRepository.save(persona3);
			personaRepository.save(persona4);
			personaRepository.save(persona5);


		};
	}

}
