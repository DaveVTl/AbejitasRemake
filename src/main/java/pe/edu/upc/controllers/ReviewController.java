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

import pe.edu.upc.entities.Reviews;
import pe.edu.upc.serviceinterface.IReviewsService;
import pe.edu.upc.serviceinterface.ITrabajoService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
	private IReviewsService rS;
	@Autowired
	private ITrabajoService tS;

	@GetMapping("/new")
	public String newReview(Model model) {
		model.addAttribute("review", new Reviews());
		model.addAttribute("listaTrabajos", tS.list());
		return "review/review";
	}

	@GetMapping("/list")
	public String listReviews(Model model) {
		try {
			model.addAttribute("review", new Reviews());
			model.addAttribute("listaReviews", rS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "review/listReviews";
	}

	@PostMapping("/save")
	public String saveReview(@Valid Reviews tipo, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaTrabajos", tS.list());
			return "review/review";
		} else {
			int rpta = rS.insert(tipo);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "review/review";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("reviews", new Reviews());
		return "redirect:/reviews/list";
	}
}
