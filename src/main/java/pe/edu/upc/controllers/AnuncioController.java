package pe.edu.upc.controllers;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

import pe.edu.upc.serviceinterface.IAnuncioService;
import pe.edu.upc.serviceinterface.IMypeService;
import pe.edu.upc.serviceinterface.ITipoTrabajoService;
import pe.edu.upc.serviceinterface.IUploadFileService;



@Controller
@RequestMapping("/anuncio")
public class AnuncioController {
	@Autowired
	private IAnuncioService aR;
	@Autowired
	private IMypeService mS;
	@Autowired
	private ITipoTrabajoService tService;
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping("/new")
	public String newAnuncio(Model model) {
		model.addAttribute("newAnuncio", new Anuncio());
		model.addAttribute("listaTipoTrabajo", tService.list());
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
	
	@GetMapping("/detalle/{id}")
	public String detailsMype(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Anuncio> anuncio = aR.listarId(id);
			if (!anuncio.isPresent()) {
				model.addAttribute("info", "Anuncio no existe");
				return "redirect:/anuncio/list";
			} else {
				model.addAttribute("anuncio", anuncio.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/anuncio/update";
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
	
	@GetMapping("/detail/{id}")
	public String detailsAnuncio(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Anuncio> anuncio = aR.findById(id);

			if (!anuncio.isPresent()) {
				model.addAttribute("info", "Anuncio no existe");
				return "redirect:/anuncio/list";
			} else {
				model.addAttribute("anuncio", anuncio.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/anuncio/detail";
	}
	
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
	
}
