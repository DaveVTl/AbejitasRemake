package pe.edu.upc;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pe.edu.upc.entities.Usuario;
import pe.edu.upc.repositories.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private UserRepository repo;
	@Test
	public void crearUsuarioTest() {
		Usuario us= new Usuario();
		us.setId(1);
		us.setEnabled(null);
		us.setPassword("123");
		us.setUsername("ara");
		Usuario retorno = repo.save(us);
		
		assertTrue(retorno.getPassword().equalsIgnoreCase(us.getUsername()));
		
	}

}
