package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Integer>{

	@Query("select count (c.nameCategory) from Category c where c.nameCategory=:name")	
	public int FindCategoryExists(@Param("name") String name);
	
}
