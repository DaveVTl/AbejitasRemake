package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Reviews;
import pe.edu.upc.repositories.IReviewsRepository;
import pe.edu.upc.serviceinterface.IReviewsService;

@Service
public class ReviewsServiceImplement implements  IReviewsService{
	@Autowired
	private IReviewsRepository rR;
	
	@Override
	public Integer insert(Reviews review) {
		int rpta=rR.FindReviewExists(review.getIdTrabajo());
		if (rpta==0) {
			rR.save(review);
		}
		return rpta;
	}

	@Override
	public List<Reviews> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}
	
	
}
