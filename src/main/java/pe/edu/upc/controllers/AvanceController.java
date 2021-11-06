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

import pe.edu.upc.entities.Avances;
import pe.edu.upc.serviceinterface.IAvanceService;
import pe.edu.upc.serviceinterface.IFreelancerService;
import pe.edu.upc.serviceinterface.ITrabajoService;
import pe.edu.upc.serviceinterface.IUploadFileService;



@Controller
@RequestMapping("/avances")
public class AvanceController {
	@Autowired
	private IAvanceService pService;
	@Autowired
	private ITrabajoService tService;
	@Autowired
	private IFreelancerService fService;
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/new")
	public String newAvance(Model model) {
		model.addAttribute("avance", new Avances());
		model.addAttribute("listaTrabajo", tService.list());
		model.addAttribute("listaFreelancer", fService.list());
		return "avances/avance";
		
	}

	@RequestMapping("/save")
	public String insertAvance(@ModelAttribute @Valid Avances objPro, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaTrabajo", tService.list());
			model.addAttribute("listaFreelancer", fService.list());
			return "avances/avance";
		} else {
			if (!foto.isEmpty()) {

				if (objPro.getIdAvance() > 0 && objPro.getFotoavance() != null && objPro.getFotoavance().length() > 0) {

					uploadFileService.delete(objPro.getFotoavance());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				objPro.setFotoavance(uniqueFilename);
			}
			boolean flag = pService.insert(objPro);
			if (flag) {
				return "redirect:/avances/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/avances/new";
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

		Avances avance = pService.listarId(id);

		if (avance == null) {
			flash.addFlashAttribute("error", "El avance no existe en la base de datos");
			return "avances/listAvances";
		}

		model.put("avance", avance);
		model.put("titulo", "Detalle de producto: " + avance.getNombre());

		return "avances/ver";
	}

	@GetMapping("/list")
	public String listAvances(Model model) {
		try {
			model.addAttribute("avance", new Avances());
			model.addAttribute("listaAvances", pService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "avances/listAvances";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Avances pro) {
		pService.listarId(pro.getIdAvance());
		return "avance/listAvances";

	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Avances objPro = pService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/avances/list";
		} else {
			model.addAttribute("listaTrabajo", tService.list());
			model.addAttribute("listaFreelancer", fService.list());
			model.addAttribute("avance", objPro);
			return "avances/avance";
		}
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un avance");
		}
		model.put("listAvances", pService.list());

//		return "redirect:/categories/list";
		return "/avances/listAvances";
	}

}
