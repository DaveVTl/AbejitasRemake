package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Category;
import pe.edu.upc.repositories.ICategoryRepository;
import pe.edu.upc.serviceinterface.ICategoryService;

@Service
public class CategoryServiceImplement implements ICategoryService{

	@Autowired
	private ICategoryRepository cR;
	
	@Override
	public Integer insert(Category category) {
		int rpta=cR.FindCategoryExists(category.getNameCategory());
		if (rpta==0) {
			cR.save(category);
		}
		return rpta;
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}
	

}
