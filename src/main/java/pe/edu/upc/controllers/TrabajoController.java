package pe.edu.upc.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Trabajo;
import pe.edu.upc.serviceinterface.IAnuncioService;
import pe.edu.upc.serviceinterface.IFreelancerService;
import pe.edu.upc.serviceinterface.ITipoTrabajoService;
import pe.edu.upc.serviceinterface.ITrabajoService;
import pe.edu.upc.serviceinterface.IUploadFileService;

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
	@Autowired
	private IUploadFileService uploadFileService;

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
	public String insertTrabajo(@ModelAttribute @Valid Trabajo objTrabajo, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaAnuncio", aS.list());
			model.addAttribute("listaFreelancers", fS.list());
			model.addAttribute("listaTipoTrabajo", tpS.list());
			return "trabajo/trabajo";
		} else {
			if (!foto.isEmpty()) {

				if (objTrabajo.getIdTrabajo() > 0 && objTrabajo.getPhotoTrabajo() != null
						&& objTrabajo.getPhotoTrabajo().length() > 0) {

					uploadFileService.delete(objTrabajo.getPhotoTrabajo());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				objTrabajo.setPhotoTrabajo(uniqueFilename);
			}
			boolean flag = tS.insert(objTrabajo);
			if (flag) {
				return "redirect:/trabajos/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/trabajos/new";
			}
		}
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

	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

		Trabajo trabajo = tS.listarId(id);

		if (trabajo == null) {
			flash.addFlashAttribute("error", "El trabajo no existe en la BD");
			return "trabajo/listTrabajos";
		}

		model.put("trabajo", trabajo);
		model.put("titulo", "Detalle de trabajo: " + trabajo.getNameTrabajo());

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
