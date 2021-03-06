package pe.edu.upc.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.entities.Mype;
import pe.edu.upc.entities.Usuario;
import pe.edu.upc.serviceinterface.IFreelancerService;
import pe.edu.upc.serviceinterface.IMypeService;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioService uService;

	@Autowired
	private IFreelancerService fS;

	@Autowired
	private IMypeService mS;

	@GetMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("newUser", new Usuario());
		return "usuarios/user";
	}

	@GetMapping("/list")
	public String listUser(Model model) {
		try {
			model.addAttribute("user", new Usuario());
			model.addAttribute("listaUsuarios", uService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "usuarios/listUser";
	}

	@PostMapping("/savefree")
	public String saveUserfree(@ModelAttribute("newUser") @Valid Usuario user, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			// return "usuarios/user";
		} else {
			String bcryptPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(bcryptPassword);
			int rpta = uService.insert_freelancer(user);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "usuarios/user";
			} else {
				model.addAttribute("mensaje", "Se guard?? correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaUsuarios", uService.list());

		return "redirect:/users/list";
	}

	@GetMapping("/myProfile")
	public String myProfile(Model model) {

		try {
			final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
			Mype mype = mS.findByUsuarioUsername(currentUserName);
			if (mype==null) {
				Freelancers free = fS.findByUsuarioUsername(currentUserName);
				model.addAttribute("free", free);
				return "/usuarios/myProfile";

			} else {
				model.addAttribute("mype", mype);
				return "/usuarios/myProfileMy";

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "/error";
		}

	}

	@PostMapping("/savemype")
	public String saveUsermype(@ModelAttribute("newUser") @Valid Usuario user, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			// return "usuarios/user";
		} else {
			String bcryptPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(bcryptPassword);
			int rpta = uService.insert_mype(user);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "usuarios/user";
			} else {
				model.addAttribute("mensaje", "Se guard?? correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaUsuarios", uService.list());

		return "redirect:/users/list";
	}

//aaaa
}
