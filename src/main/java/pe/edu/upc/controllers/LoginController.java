package pe.edu.upc.controllers;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Usuario;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping
public class LoginController {

	@Autowired
	private IUsuarioService uS;
	
	@GetMapping(value = { "/login", "/" })
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		if (principal != null) {
			return "redirect:/anuncios/list";
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
	
	@GetMapping(value = { "/registro", "/" })
	public String registro(Model model) {
		model.addAttribute("usuario",new Usuario());
		return "registro";
	}
	
	
	@PostMapping(value = { "/registro", "/" })
	public String registro(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) {

		if(result.hasErrors()) {
			return "redirect:/registro";
		}else {
			model.addAttribute("usuario", uS.register(usuario));
		}

		return "redirect:/login";
	}
	
	
}

