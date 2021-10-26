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

import pe.edu.upc.entities.Anuncio;
import pe.edu.upc.serviceinterface.IAnuncioService;
import pe.edu.upc.serviceinterface.IMypeService;
import pe.edu.upc.serviceinterface.ITipoTrabajoService;



@Controller
@RequestMapping("/anuncio")
public class AnuncioController {
	
	@Autowired
	private IAnuncioService aC;
	@Autowired
	private IMypeService mService;
	@Autowired
	private ITipoTrabajoService tService;
	
	@GetMapping("/new")
	public String newAnuncio(Model model) {
		model.addAttribute("anuncio", new Anuncio());
		model.addAttribute("listaTipoTrabajo", tService.list());
		model.addAttribute("listaMype", mService.list());
		return "anuncio/anuncio";
	}
	
	@GetMapping("/list")
	public String listAnuncio(Model model) {
		try {
			model.addAttribute("anuncio", new Anuncio());
			model.addAttribute("listaAnuncios", aC.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "anuncio/listAnuncio";
	}
	
	@PostMapping("/save")
	public String saveAnuncio(@Valid Anuncio anuncio, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "anuncio/anuncio";
		} else {
			int rpta = aC.insert(anuncio);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "anuncio/anuncio";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("anuncio", new Anuncio());
		return "redirect:/anuncio/list";
	}
}
