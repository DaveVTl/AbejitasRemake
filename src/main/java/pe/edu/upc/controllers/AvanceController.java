package pe.edu.upc.controllers;

import java.net.MalformedURLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Avances;
import pe.edu.upc.serviceinterface.IAvanceService;
import pe.edu.upc.serviceinterface.IUploadFileService;

@Controller
@RequestMapping("/avances")
public class AvanceController {
	@Autowired
	private IAvanceService aS;
	
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/new")
	public String newTipoT(Model model) {
		model.addAttribute("avance", new Avances());
		return "avances/avance";
	}

	@GetMapping("/list")
	public String listCategories(Model model) {
		try {
			model.addAttribute("avance", new Avances());
			model.addAttribute("listaAvances", aS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "avances/listAvances";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Avances tipo, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "avances/avance";
		} else {
			int rpta = aS.insert(tipo);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "avances/avance";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("avance", new Avances());
		return "redirect:/avances/list";
	}

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
}
