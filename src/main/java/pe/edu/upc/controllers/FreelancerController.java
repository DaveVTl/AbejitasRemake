package pe.edu.upc.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.serviceinterface.IFreelancerService;
import pe.edu.upc.serviceinterface.IUploadFileService;

@Controller
@RequestMapping("/freelancers")
public class FreelancerController {
	@Autowired
	private IFreelancerService fS;
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/new")
	public String newFreelancer(Model model) {
		model.addAttribute("freelancer", new Freelancers());
		return "freelancer/freelancer";/*vista --> formulario para regisrar categoria*/
	}
	
	@GetMapping("/list")
	public String listFreelancers(Model model) {
		try {
			model.addAttribute("freelancer", new Freelancers());
			model.addAttribute("listaFreelancers", fS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "freelancer/listFreelancers";
	}

	@PostMapping("/save")
	public String saveFreelancer(@Valid Freelancers freelancer, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "freelancer/freelancer";
		} else {
			
			if (!foto.isEmpty()) {

				if (freelancer.getIdFreelancers() > 0 && freelancer.getPhotoFreelancer() != null
						&& freelancer.getPhotoFreelancer().length() > 0) {

					uploadFileService.delete(freelancer.getPhotoFreelancer());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				freelancer.setPhotoFreelancer(uniqueFilename);
			}
			
			int rpta = fS.insert(freelancer);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "freelancer/freelancer";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
				model.addAttribute("freelancer", new Freelancers());
				return "redirect:/freelancers/list";
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
	
	
}
