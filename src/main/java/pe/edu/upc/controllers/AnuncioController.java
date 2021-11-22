package pe.edu.upc.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.access.annotation.Secured;
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

import pe.edu.upc.entities.Anuncio;
import pe.edu.upc.serviceinterface.IAnuncioService;
import pe.edu.upc.serviceinterface.IMypeService;
import pe.edu.upc.serviceinterface.ITipoTrabajoService;



@Controller
@RequestMapping("/anuncio")
public class AnuncioController {
	@Autowired
	private IAnuncioService aR;
	@Autowired
	private IMypeService mS;
	@Autowired
	private ITipoTrabajoService tService;
	
	@GetMapping("/new")
	public String newAnuncio(Model model) {
		model.addAttribute("anuncio", new Anuncio());
		model.addAttribute("listaTipoTrabajos", tService.list());
		model.addAttribute("listaMype", mS.list());
		return "anuncio/anuncio";
	}
	
	
	@GetMapping("/list")
	public String listAnuncio(Model model) {
		try {
			model.addAttribute("anuncio", new Anuncio());
			model.addAttribute("listaAnuncio", aR.list());
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
			model.addAttribute("listaMype", mS.list());
			return "anuncio/anuncio";
		} else {
			int rpta = aR.insert(anuncio);
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

		Anuncio objPro = aR.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/anuncio/list";
		} else {
			model.addAttribute("listaAnuncio", aR.list());
			model.addAttribute("listaMype", mS.list());
			model.addAttribute("listaTipoTrabajos", tService.list());
			model.addAttribute("anuncio", objPro);
			return "anuncio/anuncio";
		}
	}
	
	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

		Anuncio anuncio= aR.listarId(id);

		if (anuncio == null) {
			flash.addFlashAttribute("error", "El trabajo no existe en la base de datos");
			return "anuncio/listAnuncio";
		}

		model.put("anuncio", anuncio);
		model.put("titulo", "Detalle de Anuncio: " + anuncio.getNameAnuncio());

		return "anuncio/ver";
	}
	
	@Secured({"ROLE_MYPE"})
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				aR.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un review");
		}
		model.put("listAnuncio", aR.list());

		return "/anuncio/listAnuncio";
	}
	
	@GetMapping("/listFind")
	public String listProductFind(Model model) {
		try {
			model.addAttribute("anuncio", new Anuncio());
			model.addAttribute("listAnuncios", aR.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/anuncio/find";
	}

	@RequestMapping("/find")
	public String find(Map<String, Object> model, @ModelAttribute Anuncio anuncio) throws ParseException {

		List<Anuncio> listAnuncios;

		anuncio.setNameAnuncio(anuncio.getNameAnuncio());
		listAnuncios = aR.fetchAnuncioByName(anuncio.getNameAnuncio());

		if (listAnuncios.isEmpty()) {
			listAnuncios = aR.fetchAnuncioByTrabajoName(anuncio.getNameAnuncio());
		}

		if (listAnuncios.isEmpty()) {
			listAnuncios = aR.findByNameAnuncioIgnoreCase(anuncio.getNameAnuncio());
		}

		if (listAnuncios.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listAnuncios", listAnuncios);
		return "anuncio/find";

	}
	
}
