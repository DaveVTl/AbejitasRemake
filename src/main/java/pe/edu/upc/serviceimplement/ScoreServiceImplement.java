package pe.edu.upc.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Score;
import pe.edu.upc.repositories.IScoreRepository;
import pe.edu.upc.serviceinterface.IScoreService;

@Service
public class ScoreServiceImplement implements  IScoreService{
	@Autowired
	private IScoreRepository sR;
	
	@Override
	public List<Score> list() {
		// TODO Auto-generated method stub
		return sR.findAll();
	}
}
