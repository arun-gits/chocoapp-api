package com.chocoapp.chocoappapi;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chocoapp.chocoappapi.model.User;
import com.chocoapp.chocoappapi.repository.UserRepository;
import com.chocoapp.chocoappapi.service.UserService;

@RestController
public class ChocosController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("chocoapp/user/registration")
	public String registerUser(@RequestBody User user) {
		System.out.println(user);
		String message = UserService.addUser(user);
		return message;
	}
	
	@PostMapping("choco/user/login")
	public String loginUser (@RequestBody User user) {
		System.out.println(user);
		return "login works "+user;
		
	}
	
	@GetMapping("demo/addchoco")
	public String addChoco(HttpServletRequest request) {
		String name = request.getParameter("name");
		System.out.println("Chocolate is added " + name);
		return name;
	}
	 
	@GetMapping("demo/listchoco")
	public String displayChoco(@RequestParam("name") String name) {
		System.out.println("List chocolates");
		return "Dairy Milk "+name;
	}
	//@PostMapping("post")
	//public String register(@RequestBody User user)
	@PostMapping("demo/registerpost")
	public User displayUser(@RequestBody User user) {
		System.out.println(user);
		return user;
	}
	
	public void updatePrice() {
		System.out.println("Update choco price");
	}
	
	public void deleteChoco() {
		System.out.println("Delete chocolates");
	}
	
	@GetMapping("demo/listplace")
	public List<String> getPlace(){
		List<String> list = List.of("Marina Beach","Kalpakkam","Pondicherry");
		
		return list;
	}
	
	@GetMapping("demo/searchplace")
	public String searchPlace(@RequestParam("place") String place) {
		List<String> list = List.of("Marina Beach","Kalpakkam","Pondicherry");
		if(!list.contains(place)) {
			return "No such place in the list";
			}
		List<String> places = list.stream().filter(p-> p.contains(place)).
				collect(Collectors.toList());
		return places.toString();
	
	}
	
	@PatchMapping("demo/changename/{name}/{id}")
	public String changeName(@PathVariable("name") String name, @PathVariable("id") int id) {
		return name+"  "+id;
	}
	
	
	@PostMapping("spring/test")
	public User login(@RequestBody User user) {
		Optional<User> userObj = userRepository.findByMailAndPassword(user.getMail(), user.getPassword());
		
		if(userObj.isPresent()) {
			System.out.println(userObj.get());
			return userObj.get();
		}
		else {
			return null;
		}
	}
}
