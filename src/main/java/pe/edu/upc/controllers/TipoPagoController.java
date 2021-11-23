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


import pe.edu.upc.entities.TipoPago;
import pe.edu.upc.serviceinterface.ITipoPagoService;


@Controller
@RequestMapping("/tipopago")
public class TipoPagoController {
	

		@Autowired
		private ITipoPagoService pS;

		@Secured({"ROLE_ADMIN"})
		@GetMapping("/new")
		public String newTipoT(Model model) {
			model.addAttribute("tipopago",new TipoPago());
			return "tipopago/tipopago";
		}
		@Secured({"ROLE_ADMIN"})
		@GetMapping("/list")
		public String listCategories(Model model) {
			try {
				model.addAttribute("tipopago", new TipoPago());
				model.addAttribute("listaTipoPago", pS.list());
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
			}
			return "tipopago/listTipoPago";
		}
		
		@Secured({"ROLE_ADMIN"})
		@PostMapping("/save")
		public String saveMarca(@ModelAttribute("tipopago") @Valid TipoPago tipo, BindingResult result, Model model, SessionStatus status)
				throws Exception {
			if (result.hasErrors()) {
				return "tipopago/tipopago";
			} else {
				int rpta = pS.insert(tipo);
				if (rpta > 0) {
					model.addAttribute("tipopago", tipo);
					model.addAttribute("mensaje", "Ya existe");
					return "tipopago/tipopago";
				} else {
					model.addAttribute("mensaje", "Se guardó correctamente");
					status.setComplete();
				}
			}
			model.addAttribute("tipopago", new TipoPago());
			return "redirect:/tipopago/list";
		}
		
		@Secured({"ROLE_ADMIN"})
		@RequestMapping("/update/{id}")
		public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

			TipoPago objPro = pS.listarId(id);
			if (objPro == null) {
				objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
				return "redirect:/tipopago/list";
			} else {
				model.addAttribute("tipopago", objPro);
				return "tipopago/tipopago";
			}
		}
		
		@Secured({"ROLE_ADMIN"})
		@RequestMapping("/delete")
		public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
			try {
				if (id != null && id > 0) {
					pS.delete(id);
					model.put("mensaje", "Se eliminó correctamente");

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				model.put("mensaje", "No se puede eliminar un tipo pago");
			}
			model.put("listTipoPagos", pS.list());
			return "redirect:/tipopago/list";
		}
		//aaaaa
}
