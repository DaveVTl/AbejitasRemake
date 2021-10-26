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

import pe.edu.upc.entities.TipoTrabajo;
import pe.edu.upc.serviceinterface.ITipoTrabajoService;


@Controller
@RequestMapping("/tipotrabajo")
public class TipoTrabajoController {
	

		@Autowired
		private ITipoTrabajoService cT;

		
		@GetMapping("/new")
		public String newTipoT(Model model) {
			model.addAttribute("tipotrabajo",new TipoTrabajo());
			return "tipotrabajo/tipotrabajo";
		}
		@GetMapping("/list")
		public String listCategories(Model model) {
			try {
				model.addAttribute("tipotrabajo", new TipoTrabajo());
				model.addAttribute("listaTipoTrabajo", cT.list());
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
			}
			return "tipotrabajo/listTipoTra";
		}
		
		@PostMapping("/save")
		public String saveMarca(@Valid TipoTrabajo tipo, BindingResult result, Model model, SessionStatus status)
				throws Exception {
			if (result.hasErrors()) {
				return "tipotrabajo/tipotrabajo";
			} else {
				int rpta = cT.insert(tipo);
				if (rpta > 0) {
					model.addAttribute("tipotrabajo", tipo);
					model.addAttribute("mensaje", "Ya existe");
					return "tipotrabajo/tipotrabajo";
				} else {
					model.addAttribute("mensaje", "Se guardó correctamente");
					status.setComplete();
				}
			}
			model.addAttribute("tipotrabajo", new TipoTrabajo());
			return "redirect:/tipotrabajo/list";
		}
}
