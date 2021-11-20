package pe.edu.upc.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Avances;
import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.entities.Mype;
import pe.edu.upc.entities.Trabajo;
import pe.edu.upc.serviceinterface.IMypeService;
import pe.edu.upc.serviceinterface.IUploadFileService;

@Controller
@RequestMapping("/mype")
public class MypeController {
	@Autowired
	private IMypeService mC;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping("/new")
	public String newMype(Model model) {
		model.addAttribute("newMype", new Mype());
		return "mype/mype";
	}
	
	@GetMapping("/list")
	public String listMypes(Model model) {
		try {
			model.addAttribute("mype", new Mype());
			model.addAttribute("listaMype", mC.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "mype/listMype";
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
	
	@RequestMapping("/save")
	public String saveMype(@ModelAttribute("newMype") @Valid Mype newMype, BindingResult result,
			Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "mype/mype";
		} else {
			if (!foto.isEmpty()) {

				if (newMype.getIdMype() > 0 && newMype.getLogoMype() != null
						&& newMype.getLogoMype().length() > 0) {

					uploadFileService.delete(newMype.getLogoMype());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				newMype.setLogoMype(uniqueFilename);
			}

			int rpta = mC.insert(newMype);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "mype/mype";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("mype", new Mype());
		return "redirect:/mype/list";
	}
	
	@GetMapping("/detalle/{id}")
	public String detailsMype(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Mype> mype = mC.listarId(id);
			if (!mype.isPresent()) {
				model.addAttribute("info", "Mype no existe");
				return "redirect:/mype/list";
			} else {
				model.addAttribute("mype", mype.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/mype/update";
	}

	@GetMapping("/detail/{id}")
	public String detailsCustomer(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Mype> mype = mC.findById(id);

			if (!mype.isPresent()) {
				model.addAttribute("info", "Mype no existe");
				return "redirect:/mype/list";
			} else {
				model.addAttribute("mype", mype.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/mype/detail";
	}

	@GetMapping("/listFind")
	public String listFreelancersFind(Model model) {
		try {
			model.addAttribute("mype", new Freelancers());
			model.addAttribute("listaMype", mC.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/mype/find";
	}

	@RequestMapping("/find")
	public String findByMype(Map<String, Object> model,@ModelAttribute("mype") @Valid Mype mype)
			throws ParseException {

		List<Mype> listaMype;
		mype.setNameEmpresaMype(mype.getNameEmpresaMype());
		listaMype = mC.findByName(mype.getNameEmpresaMype());

		if (listaMype.isEmpty()) {
			listaMype = mC.findByNameEmpresaMypeIgnoreCase(mype.getNameEmpresaMype());
		}
		if (listaMype.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaMype", listaMype);
		return "mype/find";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				mC.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un review");
		}
		model.put("listMype", mC.list());
		return "/mype/mype";
	}
}
