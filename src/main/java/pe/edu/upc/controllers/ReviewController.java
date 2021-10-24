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

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
	private IReviewsService rS;

	@GetMapping("/new")
	public String newTipoT(Model model) {
		model.addAttribute("review", new Reviews());
		return "reviews/reviews";
	}

	@GetMapping("/list")
	public String listCategories(Model model) {
		try {
			model.addAttribute("reviews", new Reviews());
			model.addAttribute("listaReviews", rS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "reviews/listReviews";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Reviews tipo, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "reviews/reviews";
		} else {
			int rpta = rS.insert(tipo);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "reviews/reviews";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("reviews", new Reviews());
		return "redirect:/reviews/list";
	}
}
