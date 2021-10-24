package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Reviews;

public interface IReviewsService {
	public Integer insert(Reviews review);

	List<Reviews> list();
}
