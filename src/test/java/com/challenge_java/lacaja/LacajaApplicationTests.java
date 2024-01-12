package com.challenge_java.lacaja;

import com.challenge_java.lacaja.repository.IPersonaRepository;
import org.springframework.boot.test.context.SpringBootTest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LacajaApplicationTests {

	@Autowired
	private IPersonaRepository personaRepository;

	@Test
	void initData_InsertsDataIntoDatabase() {
		// Chequeo si initData insertó datos en la base de datos
		long countBefore = personaRepository.count();

		// Ejecuto la aplicación, lo que también ejecutará el CommandLineRunner
		LacajaApplication.main(new String[]{});

		// Verificar que los datos se insertaron correctamente
		long countAfter = personaRepository.count();
		assertEquals(countBefore, countAfter);
	}
}

