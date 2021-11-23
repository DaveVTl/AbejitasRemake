package pe.edu.upc.controllers;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Reviews;
import pe.edu.upc.entities.Trabajo;
import pe.edu.upc.serviceinterface.IFreelancerService;
import pe.edu.upc.serviceinterface.IMypeService;
import pe.edu.upc.serviceinterface.IReviewsService;
import pe.edu.upc.serviceinterface.IScoreService;
import pe.edu.upc.serviceinterface.ITrabajoService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
	private IReviewsService rS;
	@Autowired
	private ITrabajoService tS;
	@Autowired
	private IScoreService sS;
	@Autowired
	private IMypeService mS;
	
	
	@GetMapping("/new")
	public String newReview(Model model) {
		model.addAttribute("review", new Reviews());
		model.addAttribute("listaTrabajos", tS.list());
		model.addAttribute("listaScores", sS.list());
		model.addAttribute("listaMypes", mS.list());
		
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
	public String saveReview(@ModelAttribute("review") @Valid Reviews tipo, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaTrabajos", tS.list());
			model.addAttribute("listaScores", sS.list());
			model.addAttribute("listaMypes", mS.list());
	
			return "review/review";
		} else {
			int rpta = rS.insert(tipo);
			if (rpta > 0) {
				model.addAttribute("review", tipo);
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
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Reviews objPro = rS.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/avances/list";
		} else {
			model.addAttribute("listaTrabajos", tS.list());
			model.addAttribute("listaScores", sS.list());
			model.addAttribute("review", objPro);
			return "review/review";
		}
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				rS.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un review");
		}
		model.put("listReviews", rS.list());
;
		return "/review/listReviews";
	}
	//aaaaa
	@GetMapping("/form/{id}")
	public String formOrder(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Trabajo> trabajo = tS.findById(id);
			if (!trabajo.isPresent()) {
				model.addAttribute("info", "Cliente no existe");
				return "redirect:/trabajos/list";
			} else {
				Reviews a = new Reviews();
				a.setTrabajo(trabajo.get());
				model.addAttribute("review", a);
				model.addAttribute("listaScores", sS.list());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "review/review";
	}
}
