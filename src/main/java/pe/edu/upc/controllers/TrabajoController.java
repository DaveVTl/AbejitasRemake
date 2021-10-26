package pe.edu.upc.controllers;


import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.support.SessionStatus;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Trabajo;
import pe.edu.upc.serviceinterface.IAnuncioService;
import pe.edu.upc.serviceinterface.IFreelancerService;
import pe.edu.upc.serviceinterface.ITipoTrabajoService;
import pe.edu.upc.serviceinterface.ITrabajoService;

@Controller
@RequestMapping("/trabajos")
public class TrabajoController {
	@Autowired
	private ITrabajoService tS;
	@Autowired
	private IAnuncioService aS;
	@Autowired
	private IFreelancerService fS;
	@Autowired
	private ITipoTrabajoService tpS;


	@GetMapping("/new")
	public String newProduct(Model model) {
		model.addAttribute("trabajo", new Trabajo());
		model.addAttribute("listaAnuncio", aS.list());
		model.addAttribute("listaFreelancers", fS.list());
		model.addAttribute("listaTipoTrabajo", tpS.list());
		model.addAttribute("trabajo", new Trabajo());
		return "trabajo/trabajo";
	}

	@GetMapping("/list")
	public String listProducts(Model model) {
		try {
			model.addAttribute("trabajo", new Trabajo());
			model.addAttribute("listaTrabajos", tS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "trabajo/listTrabajos";
	}

	@RequestMapping("/save")
	public String insertTrabajo(@Valid Trabajo objTrabajo, BindingResult result, Model model, SessionStatus status)
			throws ParseException {
		if (result.hasErrors()) {
			model.addAttribute("listaAnuncio", aS.list());
			model.addAttribute("listaFreelancers", fS.list());
			model.addAttribute("listaTipoTrabajo", tpS.list());
			return "trabajo/trabajo";
		} else {
			
			boolean flag = tS.insert(objTrabajo);
			if (flag) {
				return "redirect:/trabajos/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/trabajos/new";
			}
		}
	}

	
	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

		Trabajo trabajo= tS.listarId(id);

		if (trabajo == null) {
			flash.addFlashAttribute("error", "El trabajo no existe en la base de datos");
			return "trabajo/listTrabajos";
		}

		model.put("trabajo", trabajo);
		model.put("titulo", "Detalle de Trabajo: " + trabajo.getNameTrabajo());

		return "trabajo/ver";
	}
	
	@RequestMapping("/list")
	public String listTrabajos(Map<String, Object> model) {
		model.put("listaTrabajos", tS.list());
		return "trabajo/listTrabajos";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Trabajo tra) {
		tS.listarId(tra.getIdTrabajo());
		return "trabajo/listTrabajos";

	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Trabajo objTrabajo = tS.listarId(id);
		if (objTrabajo == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/trabajos/list";
		} else {
			model.addAttribute("listaAnuncio", aS.list());
			model.addAttribute("listaFreelancers", fS.list());
			model.addAttribute("listaTipoTrabajo", tpS.list());
			model.addAttribute("trabajo", objTrabajo);
			return "trabajo/trabajo";
		}
	}
}