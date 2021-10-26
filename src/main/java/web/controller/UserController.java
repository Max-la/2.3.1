package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.Model.User;
import web.Servise.UserServiceImpl;

@Controller
@Transactional
public class UserController {

	private final UserServiceImpl userService;

	@Autowired
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String allUser(Model model){
		model.addAttribute("getall",userService.getAllUser());
		return "/users";
	}

	@GetMapping("/new")
	public String newUser(Model model){
		model.addAttribute("user",new User());
		return "new";
	}

	@PostMapping()
	public String create(@ModelAttribute("user") User user){
		userService.add(user);
		return "redirect:/";
	}

	@GetMapping(value = "/{id}/user-update")
	public String updateUserForm(@PathVariable("id") Long id,Model model){
		User user = userService.getUser(id);
		model.addAttribute("user",user);
		return "user-update";
	}

	@PostMapping("/{id}")
	public String updateUser(@ModelAttribute("user") User user){
		userService.updateUser(user);
		return "redirect:/";
	}

	@DeleteMapping(value = "/{id}")
	public String deleteUser(@PathVariable(value = "id") Long id){
		userService.delete(id);
		return "redirect:/";
	}
}
