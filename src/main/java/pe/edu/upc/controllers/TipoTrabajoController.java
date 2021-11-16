package pe.edu.upc.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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


import pe.edu.upc.entities.TipoTrabajo;
import pe.edu.upc.serviceinterface.ITipoTrabajoService;


@Controller
@RequestMapping("/tipotrabajo")
public class TipoTrabajoController {
	

		@Autowired
		private ITipoTrabajoService cT;

		@Secured({"ROLE_ADMIN"})
		@GetMapping("/new")
		public String newTipoT(Model model) {
			model.addAttribute("tipotrabajo",new TipoTrabajo());
			return "tipotrabajo/tipotrabajo";
		}
		
		@GetMapping("/list")
		public String listTipoTrabajo(Model model) {
			try {
				model.addAttribute("tipotrabajo", new TipoTrabajo());
				model.addAttribute("listaTipoTrabajos", cT.list());
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
			}
			return "tipotrabajo/listTipoTra";
		}
		
		@Secured({"ROLE_ADMIN"})
		@PostMapping("/save")
		public String saveMarca(@ModelAttribute("tipotrabajo") @Valid TipoTrabajo tipo, BindingResult result, Model model, SessionStatus status)
				throws Exception {
			if (result.hasErrors()) {
				return "tipotrabajo/tipotrabajo";
			} else {
				int rpta = cT.insert(tipo);
				if (rpta > 0) {
					model.addAttribute("tipotrabajo", tipo);
					model.addAttribute("mensaje", "Ya existe");
					return "tipotrabajo/tipotrabajo";
				} else {
					model.addAttribute("mensaje", "Se guardó correctamente");
					status.setComplete();
				}
			}
			model.addAttribute("tipotrabajo", new TipoTrabajo());
			return "redirect:/tipotrabajo/list";
		}
		
		@Secured({"ROLE_ADMIN"})
		@RequestMapping("/update/{id}")
		public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

			TipoTrabajo objPro = cT.listarId(id);
			if (objPro == null) {
				objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
				return "redirect:/tipotrabajo/list";
			} else {
				model.addAttribute("tipotrabajo", objPro);
				return "tipotrabajo/tipotrabajo";
			}
		}
		
		@Secured({"ROLE_ADMIN"})
		@RequestMapping("/delete")
		public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
			try {
				if (id != null && id > 0) {
					cT.delete(id);
					model.put("mensaje", "Se eliminó correctamente");

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				model.put("mensaje", "No se puede eliminar un review");
			}
			model.put("listTipoTrabajo", cT.list());
			return "/tipotrabajo/listTipoTra";
		}
		
		@RequestMapping("/reportes")
		public String listReports()
		{
			return "reports/reportsv";
		}
		
		@RequestMapping("/reporte1")
		public String productosXimp(Map<String, Object> model) {
			model.put("listTtrabajoxOrd", cT.ttrabajoXord());
			return "reports/tipoxanuncio";
		}
}
//aaaa
