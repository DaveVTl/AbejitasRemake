package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Category;

public interface ICategoryService {
	public Integer insert(Category category);

	List<Category> list();
}
