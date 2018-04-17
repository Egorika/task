package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "Пользователь с таким email уже зарегистрирован");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Пользователь успешно зарегистрирован");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findUserById(id);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("/admin/edit");
		return modelAndView;
	}
	
	

	@RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
	public ModelAndView editUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/admin/home");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("user", user);
			modelAndView.setViewName("redirect:/admin/home");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<User> allUsers = userService.findAll();
		modelAndView.addObject("userName",
				"Выполнен вход как админ: " + user.getName() + " " + user.getSurname() + " (" + user.getEmail() + ")");
		modelAndView.addObject("allUsers", allUsers);
		modelAndView.addObject("successMessage", "Пользователь успешно изменён");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping("/admin/remove/{id}")
	public String removePerson(@PathVariable("id") Long id) {
		User user = userService.findUserById(id);
		userService.deleteUser(user);
		return "redirect:/admin/home";
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ModelAndView info(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findUserById(id);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("/user");
		return modelAndView;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userRedirect() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return "redirect:/user/" + user.getId();
	}
}
