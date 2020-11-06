//package com.ccd.app.mockito;
//
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
////import org.mockito.junit.MockitoJUnitRunner;
//
//import com.ccd.app.UserRepository;
//import com.ccd.app.UserServiceImplication;
//import com.ccd.app.model.User;	
//
///**
// * Test class
// * @author jsuh_mac
// *
// */
////import org.junit.runner.RunWith;
////@RunWith(MockitoJUnitRunner.class)
//public class MockitoUnitTest {
//		@InjectMocks
//		UserServiceImplication userService;
//
//		@Mock
//		UserRepository repo;
//
//		@Before
//		public void init() {
//			MockitoAnnotations.initMocks(this);
//		}
//
//		/**
//		 * when mocked the database without database, see if the application is working fine
//		 */
//		@Test
//		public void findByIdTest() {
//			when(repo.getUserById(1)).thenReturn(new User(1, "jDoe", "123456", "jDoe@gmail.com", "1"));
//
//			User usr = userService.getUserById(1);
//
//			assertEquals("jDoe", usr.getUsername());
//			assertEquals("123456", usr.getPassword());
//			assertEquals("jDoe@gmail.com", usr.getEmail());
//			assertEquals("1", usr.getUsericon());
//		}
//
//		/**
//		 * when mocked the database without database, see if the application is working fine
//		 */
//		@Test
//		public void getAllUserTest() {
//			List<User> list = new ArrayList<User>();
//			User usrOne = new User(1, "John", "1234", "john@gmail.com", "1");
//			User usrTwo = new User(2, "Alex", "abcd", "alex@yahoo.com", "2");
//			User usrThree = new User(3, "Steve", "efgh", "steve@gmail.com", "3");
//
//			list.add(usrOne);
//			list.add(usrTwo);
//			list.add(usrThree);
//
//			when(repo.getUserList()).thenReturn(list);
//
//			List<User> usrList = userService.getUserList();
//
//			assertEquals(3, usrList.size());
//			verify(repo, times(1)).getUserList();
//		}
//
//		@After
//		public void after() {
//			System.out.println("JUnit done");
//		}
//
//	}
