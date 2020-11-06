//package com.ccd.app;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThat;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.stereotype.Service;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.ccd.app.model.User;
//
//
//@RunWith(SpringRunner.class)
//public class UserServiceTest {
//
//	/**
//	 * Test cases to check if User Service is working fine
//	 * @author jsuh_mac
//	 *
//	 */
//	@TestConfiguration
//	static class UserServiceTestConfiguration {
//	
//		@Bean
//		public UserService userService() {
//			return new UserServiceImplication();
//		}
//	}
//	
//	@Autowired
//	private UserService userService;
//	
//	@MockBean
//	private UserRepository userRepository;
//	
//	
//	
//	
//	@Before
//	public void start() {
//		User me = new User();
//		String name = "riess";
//		String password = "password";
//		String email = "rradtke@iastate.edu";
//		me.setUsername(name);
//		me.setPassword(password);
//		me.setEmail(email);
//		
//		Mockito.when(userRepository.findByUsername(name)).thenReturn(me);
//	}
//	
//	
//	@Test
//	public void attempt() {
//		String n = "riess";
//		User u = userService.findByUsername(n);
//		assertThat(u.getUsername().isEqualTo(n));
//	}
//	
//	@Test
//	public void attempt2() {
//		String p = "password";
//		User u = userService.findByUsername(n);
//		assertThat(u.getPassword().isEqualTo(p));
//	}
//	
//}