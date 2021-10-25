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

import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.serviceinterface.IFreelancerService;

@Controller
@RequestMapping("/freelancers")
public class FreelancerController {
	@Autowired
	private IFreelancerService fS;
	
	@GetMapping("/new")
	public String newCategory(Model model) {
		model.addAttribute("freelancer", new Freelancers());
		return "freelancer/freelancer";/*vista --> formulario para regisrar categoria*/
	}
	
	@GetMapping("/list")
	public String listFreelancers(Model model) {
		try {
			model.addAttribute("freelancer", new Freelancers());
			model.addAttribute("listaFreelancers", fS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "freelancer/listFreelancers";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Freelancers freelancer, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "freelancer/freelancer";
		} else {
			int rpta = fS.insert(freelancer);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "freelancer/freelancer";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("freelancer", new Freelancers());
		return "redirect:/freelancers/list";
	}
}
