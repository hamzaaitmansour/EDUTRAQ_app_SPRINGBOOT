package fullStack.template.service;

import fullStack.template.entities.Salle;
import fullStack.template.repository.SalleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalleService {
    @Autowired
    private SalleRepo salleRepo;
    public Salle addSalle(Salle salle)
    {
        return salleRepo.save(salle);
    }
    public void deleteSalle(Long id)
    {
         salleRepo.deleteById(id);
    }
    public List<Salle> getall()
    {
        return salleRepo.findAll();
    }
}
