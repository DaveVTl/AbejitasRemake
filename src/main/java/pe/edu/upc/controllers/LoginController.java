package pe.edu.upc.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.entities.Mype;
import pe.edu.upc.entities.Usuario;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuarioService uService;
	
	@GetMapping(value = { "/login", "/" })
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		if (principal != null) {
			return "inicio";
		}

		if (error != null) {
			model.addAttribute("error",
					"Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}

		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
		}

		return "login";
	}
	
	@GetMapping("/login/new")
	public String newUser(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("newMype", new Mype());
		return "registro";
	}
	
	@PostMapping("/login/save")
	public String saveUser(@ModelAttribute("newUser") @Valid Usuario user, BindingResult result, Model model)
			throws Exception {
		if (result.hasErrors()) {
			return "usuarios/user";
		} else {
			String bcryptPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(bcryptPassword);
			int rpta = uService.insert_mype(user);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("newUser", new Usuario());
				return "registro";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
			}
		}
		return "redirect:/mype/new";
	}
	
	@GetMapping("/login/newfree")
	public String newUserFreelancer(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("newFreelancer", new Freelancers());
		return "registrofree";
	}
	
	@PostMapping("/login/savefree")
	public String saveUserFreelancer(@ModelAttribute("newUserFree") @Valid Usuario user, BindingResult result, Model model)
			throws Exception {
		if (result.hasErrors()) {
			return "usuarios/user";
		} else {
			String bcryptPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(bcryptPassword);
			int rpta = uService.insert_freelancer(user);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("newUserFree", new Usuario());
				return "registro";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
			}
		}
		return "redirect:/freelancers/new";
	}

//aaaaa
	
	
}
