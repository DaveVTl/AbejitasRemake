package pe.edu.upc.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Reviews;
import pe.edu.upc.repositories.IReviewsRepository;
import pe.edu.upc.serviceinterface.IReviewsService;

@Service
public class ReviewsServiceImplement implements  IReviewsService{
	@Autowired
	private IReviewsRepository rR;
	
	@Override
	public Integer insert(Reviews review) {
		int rpta=rR.FindReviewExists(review.getDescripcionre());
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
	
	@Override
	@Transactional(readOnly = true)
	public Reviews listarId(int idreview) {
		Optional<Reviews> op = rR.findById(idreview);
		return op.isPresent() ? op.get() : new Reviews();
	}
	
	@Override
	@Transactional
	public void delete(int idreview) {
		rR.deleteById(idreview);
	}
}
