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

import pe.edu.upc.entities.Mype;
import pe.edu.upc.serviceinterface.IMypeService;

@Controller
@RequestMapping("/mype")
public class MypeController {
	@Autowired
	private IMypeService mC;
	
	
	@GetMapping("/new")
	public String newMype(Model model) {
		model.addAttribute("mype", new Mype());
		return "mype/mype";
	}
	
	@GetMapping("/list")
	public String listMypes(Model model) {
		try {
			model.addAttribute("mype", new Mype());
			model.addAttribute("listaMypes", mC.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "mype/listMype";
	}
	
	@PostMapping("/save")
	public String saveMarca(@Valid Mype mype, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "mype/mype";
		} else {
			int rpta = mC.insert(mype);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "mype/mype";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("mype", new Mype());
		return "redirect:/mype/list";
	}
}
