package pe.edu.upc.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Anuncio;
import pe.edu.upc.entities.Reviews;
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
		model.addAttribute("listaTipoTrabajos", tService.list());
		model.addAttribute("listaMypes", mService.list());
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
			model.addAttribute("listaTipoTrabajos", tService.list());
			model.addAttribute("listaMypes", mService.list());
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
	
	@Secured({"ROLE_MYPE"})
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Anuncio objPro = aC.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/anuncio/list";
		} else {
			model.addAttribute("listaAnuncio", aC.list());
			model.addAttribute("listaMypes", aC.list());
			model.addAttribute("listaTipoTrabajos", aC.list());
			model.addAttribute("anuncio", objPro);
			return "anuncio/anuncio";
		}
	}
	
	@Secured({"ROLE_MYPE"})
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				aC.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un review");
		}
		model.put("listAnuncio", aC.list());

		return "/anuncio/listAnuncio";
	}
	
}
