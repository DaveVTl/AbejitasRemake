package pe.edu.upc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Admin;
import pe.edu.upc.serviceinterface.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IAdminService aC;
	
	@GetMapping("/new")
	public String newMype(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin/admin";
	}
	
	@GetMapping("/list")
	public String listMypes(Model model) {
		try {
			model.addAttribute("admin", new Admin());
			model.addAttribute("listaAdmin", aC.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "Admin/listAdmin";
	}
	
	@PostMapping("/save")
	public String saveMarca(@Valid Admin admin, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "admin/admin";
		} else {
			int rpta = aC.insert(admin);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "admin/admin";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("admin", new Admin());
		return "redirect:/admin/list";
	}
}
