package pe.edu.upc.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Rol;
import pe.edu.upc.serviceinterface.IRoleService;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/rols")
public class RoleController {

	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IRoleService rService;

	@GetMapping("/new")
	public String newRole(Model model) {
		model.addAttribute("rol", new Rol());
		model.addAttribute("listaUsuarios", uService.list());
		return "role/role";
	}

	@PostMapping("/save")
	public String saveRole(@Valid Rol rol, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "rol/rol";
		} else {
		rService.insert(rol);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaRols", rService.list());

		return "rol/rol";

	}

	@GetMapping("/list")
	public String listRole(Model model) {
		try {
			model.addAttribute("rol", new Rol());
			model.addAttribute("listaRols", rService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "rol/listRol";
	}

}
