package com.challenge_java.lacaja;

import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.repository.IPersonaRepository;
import com.challenge_java.lacaja.service.IProcesadorDeDatosService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;


@SpringBootTest
class LacajaApplicationTests {

	static Logger logger = LogManager.getLogger(LacajaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LacajaApplication.class, args);
		logger.info("===========================================");
		logger.info("============    APP RUNNING    ============");
		logger.info("===========================================");
	}

	@Bean
	public CommandLineRunner initData(IPersonaRepository personaRepository, IProcesadorDeDatosService procesadorDeDatosService){

		return args -> {


			// PRUEBA
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
