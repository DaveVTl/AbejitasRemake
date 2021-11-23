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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import pe.edu.upc.entities.Freelancers;
import pe.edu.upc.entities.Usuario;
import pe.edu.upc.serviceinterface.IFreelancerService;
import pe.edu.upc.serviceinterface.IUploadFileService;

@Controller
@RequestMapping("/freelancers")
public class FreelancerController {
	@Autowired
	private IFreelancerService fS;

	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/new")
	public String newCategory(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("newFreelancer", new Freelancers());
		return "registrofree";/* vista --> formulario para regisrar categoria */
	}

	@GetMapping("/list")
	public String listFreelancers(Model model) {
		try {
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("freelancer", new Freelancers());
			model.addAttribute("listaFreelancers", fS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "freelancer/listFreelancers";
	}

	@RequestMapping("/save")
	public String saveMarca(@ModelAttribute("newFreelancer") @Valid Freelancers newFreelancer, BindingResult result,
			Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "/registrofree";
		} else {
			if (!foto.isEmpty()) {

				if (newFreelancer.getIdFreelancers() > 0 && newFreelancer.getFotoFreelancers() != null
						&& newFreelancer.getFotoFreelancers().length() > 0) {

					uploadFileService.delete(newFreelancer.getFotoFreelancers());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				newFreelancer.setFotoFreelancers(uniqueFilename);
			}
			String bcryptPassword = passwordEncoder.encode(newFreelancer.getUsuario().getPassword());
			newFreelancer.getUsuario().setPassword(bcryptPassword);
			int rpta = fS.insert(newFreelancer);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/registrofree";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("newFreelancer", new Freelancers());
		return "redirect:/login";
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

	@GetMapping("/detalle/{id}")
	public String detailsCategory(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Freelancers> freelancer = fS.listarId(id);
			if (!freelancer.isPresent()) {
				model.addAttribute("info", "Freelancer no existe");
				return "redirect:/freelancers/list";
			} else {
				model.addAttribute("freelancer", freelancer.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/freelancer/update";
	}

	@GetMapping("/detail/{id}")
	public String detailsCustomer(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Freelancers> freelancer = fS.findById(id);

			if (!freelancer.isPresent()) {
				model.addAttribute("info", "Freelancer no existe");
				return "redirect:/freelancers/list";
			} else {
				model.addAttribute("freelancer", freelancer.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/freelancer/detail";
	}
	
	

	@GetMapping("/prere")
	public String prere(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("newFreelancer", new Freelancers());
		return "/preregistro";
	}
	
	@GetMapping("/listFind")
	public String listFreelancersFind(Model model) {
		try {
			model.addAttribute("freelancer", new Freelancers());
			model.addAttribute("listaFreelancers", fS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/freelancer/find";
	}

	@RequestMapping("/find")
	public String findByFreelancer(Map<String, Object> model,@ModelAttribute("freelancer") @Valid Freelancers freelancer)
			throws ParseException {

		List<Freelancers> listaFreelancers;
		freelancer.setNameFreelancers(freelancer.getNameFreelancers());
		listaFreelancers = fS.findByName(freelancer.getNameFreelancers());

		
		if (listaFreelancers.isEmpty()) {
			listaFreelancers = fS.findByNameFreelancersIgnoreCase(freelancer.getNameFreelancers());
		}
		if (listaFreelancers.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaFreelancers", listaFreelancers);
		return "freelancer/find";
//aaaaaa
	}
	
	@RequestMapping("/reportefree")
	public String FreelancerXimp(Map<String, Object> model) {
		model.put("listFreelancerxOrd", fS.freelancerXord());
		return "reports/freeprom";
	}

}
