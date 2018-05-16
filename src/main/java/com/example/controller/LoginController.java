package com.example.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Role;
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
		String message = new String("Пользователь успешно зарегистрирован");
		if (userService.findUserByEmail(user.getEmail()) != null) {
			message = "Ошибка! Пользователь с таким email уже зарегистрирован";
			modelAndView.addObject("failureMessage", message);
			bindingResult.rejectValue("email", "error.user", "Ошибка!");
		}
		if (userService.findUserByPassportId(user.getPassportid()) != null) {
			message = "Ошибка! Пользователь с таким идентификационным номером уже зарегистрирован";
			modelAndView.addObject("failureMessage", message);
			bindingResult.rejectValue("passportid", "error.user", "Ошибка!");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", message);
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
	public ModelAndView editUser(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		String message = new String("Пользователь успешно изменён");
		if (userService.findUserByEmail(user.getEmail(), user.getId()) != null) {
			message = "Ошибка! Пользователь с таким email уже зарегистрирован!";
			redirectAttributes.addFlashAttribute("failureMessage", message);
			bindingResult.rejectValue("email", "error.user", "Ошибка!");
		}
		if (userService.findUserByPassportId(user.getPassportid(), user.getId()) != null) {
			message = "Ошибка! Пользователь с таким идентификационным номером уже зарегистрирован!";
			redirectAttributes.addFlashAttribute("failureMessage", message);
			bindingResult.rejectValue("passportid", "error.user", "Ошибка!");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/admin/home");
		} else {
			userService.saveUser(user);
			redirectAttributes.addFlashAttribute("successMessage", message);
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
		modelAndView.setViewName("admin/home");
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

	@RequestMapping(value = "/admin/remove/{id}", method = RequestMethod.GET)
	public ModelAndView removePerson(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findUserById(id);
		userService.deleteUser(user);
		modelAndView.setViewName("redirect:/admin/home");
		return modelAndView;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ModelAndView info(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findUserById(id);
		modelAndView.addObject("user", user);
		modelAndView.addObject("date", new Date());
		modelAndView.setViewName("/user");
		return modelAndView;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView userRedirect() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if (user.getActive() == 1)
			modelAndView.setViewName("redirect:/admin/home");
		modelAndView.setViewName("redirect:/user/" + user.getId());
		return modelAndView;
	}
}
