package projeto.spring.data.aula;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;
	
	@Test
	public void testeInsert() {
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("javaavancado@javaavancado.com");
		usuarioSpringData.setIdade(31);
		usuarioSpringData.setLogin("teste 123");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("Willian");
		
		interfaceSpringDataUser.save(usuarioSpringData);
		
		System.out.println("Usuario cadastrado -> " + interfaceSpringDataUser.count());
	}
	
	@Test
	public void testeConsulta() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L);
		
		System.out.println("Id: " + usuarioSpringData.get().getId());
		System.out.println("Email: " + usuarioSpringData.get().getEmail());
		System.out.println("Idade: " + usuarioSpringData.get().getIdade());
		System.out.println("Login: " + usuarioSpringData.get().getLogin());
		System.out.println("Nome: " + usuarioSpringData.get().getNome());
		System.out.println("Senha: " + usuarioSpringData.get().getSenha());
		
		System.out.println("\nTelefones: ");
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			System.out.println("-------------------");
			System.out.println("Numero: " + telefone.getNumero());
			System.out.println("Tipo: " + telefone.getTipo());
			System.out.println("Id: " + telefone.getId());
			System.out.println("Nome do usuario: " + telefone.getUsuarioSpringData().getNome());
		}
	}
	
	@Test
	public void testeConsultaTodos() {
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		
		System.out.println("Imprimindo dados de todos usuarios");
		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println("Id: " + usuarioSpringData.getId());
			System.out.println("Email: " + usuarioSpringData.getEmail());
			System.out.println("Idade: " + usuarioSpringData.getIdade());
			System.out.println("Login: " + usuarioSpringData.getLogin());
			System.out.println("Nome: " + usuarioSpringData.getNome());
			System.out.println("Senha: " + usuarioSpringData.getSenha());
			System.out.println("----------------------------------------------");
		}
	}
	
	@Test
	public void testeUpdate() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L);
		
		UsuarioSpringData data = usuarioSpringData.get();
		
		data.setNome("Mateus Freire");
		data.setIdade(25);
		
		interfaceSpringDataUser.save(data);		
	}
	
	@Test
	public void testeDelete() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
		
		interfaceSpringDataUser.delete(usuarioSpringData.get());
	}
	
	@Test
	public void testeConsultaNome() {
		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Marcos");
		
		for (UsuarioSpringData usuarioSpringData : list) {
			System.out.println("Id: " + usuarioSpringData.getId());
			System.out.println("Email: " + usuarioSpringData.getEmail());
			System.out.println("Idade: " + usuarioSpringData.getIdade());
			System.out.println("Login: " + usuarioSpringData.getLogin());
			System.out.println("Nome: " + usuarioSpringData.getNome());
			System.out.println("Senha: " + usuarioSpringData.getSenha());
			System.out.println("----------------------------------------------");
		}
	}
	
	@Test
	public void testeConsultaNomeParam() {	
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Fernando");
		
			System.out.println("Id: " + usuarioSpringData.getId());
			System.out.println("Email: " + usuarioSpringData.getEmail());
			System.out.println("Idade: " + usuarioSpringData.getIdade());
			System.out.println("Login: " + usuarioSpringData.getLogin());
			System.out.println("Nome: " + usuarioSpringData.getNome());
			System.out.println("Senha: " + usuarioSpringData.getSenha());
			System.out.println("----------------------------------------------");
	}
	
	@Test
	public void testeDeletePorNome() {
		interfaceSpringDataUser.deletePorNome("Marcos");
	}
	
	@Test
	public void testeUpdateEmailPorNome() {
		interfaceSpringDataUser.updateEmailPorNome("fernando@gmail.com", "Fernando");
	}
	
	@Test
	public void testeInsertTelefone() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("Casa");
		telefone.setNumero("76538568736");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		interfaceTelefone.save(telefone);
	}
}
