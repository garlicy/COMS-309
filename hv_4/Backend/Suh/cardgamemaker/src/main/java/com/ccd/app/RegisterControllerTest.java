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
///**
// * JUnit Test Class
// * @author jsuh_mac
// *
// */
//@RunWith(SpringRunner.class)
//public class RegisterControllerTest {
//	
//	@TestConfiguration
//	static class RegisterControllerTestConfiguration {
//		
//		@Bean
//		public RegisterController registerController() {
//			return new RegisterController();
//		}
//	}
//	
//	@AutoWired
//	private RegisterController regController;
//	
//	@Bean
//	private UserRepository userRepo;
//	
//	@Before
//	public void start() {
//		User u = new User();
//		u.setUsername("riess");
//		u.addFriend("friend1");
//		u.addFriend("friend2");
//		u.addFriend("friend3");
//		String friends = "friend1 friend2 friend3 ";
//		
//		Mockito.when(regController.getFriendsList(u.getUsername)).thenReturn(friends);
//	}
//	
//	@Test
//	public void start() {
//		String name = "riess";
//		String friends = regController.getFriendsList(name);
//		assertThat(friends.isEqualTo("friend1 friend2 friend3 "));
//		
//	}
//	
//	
//	
//	
//}
