package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer>{
	
	@Query("select count (a.DNI) from Admin a where a.DNI=:name")	
	public int FindMypeExists(@Param("name") String name);
}
