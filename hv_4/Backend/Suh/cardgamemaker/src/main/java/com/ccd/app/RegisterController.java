package com.ccd.app;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ccd.app.model.User;

/**
 * Controller of using User, Role database
 * @author jsuh_mac
 *
 */
@Controller	// This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class RegisterController {
	@Autowired // This means to get the bean called userRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	
//	@Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserValidator userValidator;


	/**
	 * Test URL
	 * @return Hello
	 */
	@GetMapping(path="/hello")
	public @ResponseBody String sayHello() {
		return "Hello";
	}
	/**
	 * URL to register
	 * @param username Input username
	 * @param email Input email
	 * @param password Input password
	 * @param usericon Input usericon
	 * @return
	 */
	@PostMapping(path="/register") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String username
			, @RequestParam String email, @RequestParam String password, @RequestParam String usericon) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setUsername(username);
		n.setEmail(email);
		n.setPassword(password);
		n.setUsericon(usericon);
		userRepository.save(n);
		return "Saved";
	}
	/**
	 * Test URL to return all
	 * @return
	 */

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
//	@GetMapping("/login")
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "login";
//    }
	
	/**
	 * URL to login
	 * @param username username to login
	 * @param password password to login
	 * @return If successful, return usericon, if not, return null
	 */
	@PostMapping(path="/login") // Map ONLY POST Requests
	public @ResponseBody String login (@RequestParam String username
			,@RequestParam String password) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		//throws UsernameNotFoundException{
			User user = userRepository.findByUsername(username);
			if(user.getPassword().equals(password)) {
				return user.getUsericon();
			}
			else {
				return null;
			}
		//}
	}
	
	/**
	 * URL to get specific user by username
	 * @param username specific username
	 * @return User that has the specific username
	 */
	@PostMapping(path="/get") // Map ONLY POST Requests
	public @ResponseBody User get (@RequestParam String username) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		//throws UsernameNotFoundException{
			return userRepository.findByUsername(username);
			
		//}
	}
	/**
	 * URL to change password
	 * @param username username of the User to change password
	 * @param password Changing password
	 */
	
	@PostMapping(path="/changepassword")
	public @ResponseBody void changePassword(@RequestParam String username, 
			@RequestParam String newPassword) {
		User user = userRepository.findByUsername(username);
			user.setPassword(newPassword);
			userRepository.save(user);
	}
	/**
	 * URL to get user's friend list
	 * @param username User to get friend list of
	 * @return JSONObject of user's friends
	 */
	@GetMapping(path="/getfriends")
	public @ResponseBody JSONObject getFriendsList(@RequestParam String username) {
		User u = userRepository.findByUsername(username);
		ArrayList<String> friends = u.getFriends();
		int size = friends.size();
		
		JSONObject ja = new JSONObject();
		ja.put("Total", size);
		JSONArray arr = new JSONArray();
		
		for(int i = 0; i < size; ++i) {
			User friend = userRepository.findByUsername(friends.get(i));
			JSONObject jo = new JSONObject();
			jo.put("username", friend.getUsername());
			jo.put("usericon", friend.getUsericon());
			
			arr.put(jo);			
		}
		ja.put("array", arr);
		return ja;
	}
	
	/**
	 * URL to add a friend to a user's friend list
	 * @param username User to add friend to
	 * @param newFriend Username of friend to add
	 * @return 
	 */
	@PostMapping(path="/addfriend")
	public @ResponseBody String addFriend(@RequestParam String username,
			@RequestParam String friendName) {
		User u = userRepository.findByUsername(username);
		u.addFriend(friendName);
		return "Friend Added";
	}
	
	/**
	 * URL to remove a friend from a user's friend list
	 * @param username User to remove friend
	 * @param friendUsername Username of friend to remove
	 */
	@PostMapping(path="/removefriend")
	public @ResponseBody String removeFriend(@RequestParam String username,
			@RequestParam String friendName) {
		User u = userRepository.findByUsername(username);
		u.removeFriend(friendName);
		return "Friend Removed";
	}
	
	@PostMapping(path="/getinfo")
	public @ResponseBody JSONObject getInfo(@RequestParam String username) {
		JSONObject jo = new JSONObject();
		User u = userRepository.findByUsername(username);
		
		jo.put("password", u.getPassword());
		jo.put("email", u.getEmail());
		jo.put("usericon", u.getUsericon());
		
		return jo;
	}
	
	
	@GetMapping(path="/search/users")
	public @ResponseBody JSONObject searchUsers(@RequestParam String search) {
		List<User> users = userRepository.findAll();
		JSONObject result = new JSONObject();
		JSONArray ja = new JSONArray();
		
		result.put("Total", users.size());
		for(int i = 0; i < users.size(); ++i) {
			JSONObject jo = new JSONObject();
			jo.put("username", users.get(i).getUsername());
			ja.put(jo);
		}
		result.put("array", ja);
		return result;	
	}
	
	
}
