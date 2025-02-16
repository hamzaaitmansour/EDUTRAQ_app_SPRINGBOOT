package fullStack.template.service;

import fullStack.template.entities.Filiere;
import fullStack.template.repository.FiliereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereService {

@Autowired
    private FiliereRepo filiereRepo;
    public Filiere addFiliere(Filiere filiere)
    {
        return filiereRepo.save(filiere);
    }

    public void deleteFiliere(Long id)
    {   filiereRepo.deleteById(id);

    }


    public List<Filiere> findAll()
    {
        return filiereRepo.findAll();
    }

}
