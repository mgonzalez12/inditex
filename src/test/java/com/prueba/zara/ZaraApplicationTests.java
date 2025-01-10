package com.prueba.zara;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ZaraApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	@DirtiesContext
	void testContextAfterSomethingChanged() {
		// Aquí puedes realizar algunas pruebas sobre el contexto después de los cambios
		assertNotNull(context);
	}

}
