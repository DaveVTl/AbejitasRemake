package pe.edu.upc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entities.Category;
import pe.edu.upc.serviceinterface.ICategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private ICategoryService cS;
	
	@GetMapping("/new")
	public String newCategory(Model model) {
		model.addAttribute("category", new Category());
		return "";/*vista --> formulario para regisrar categoria*/
	}
}
