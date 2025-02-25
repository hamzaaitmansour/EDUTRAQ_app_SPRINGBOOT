package fullStack.template.service;

import fullStack.template.entities.Filiere;
import fullStack.template.entities.Matiere;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.repository.MatiereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereService {
    @Autowired
    private MatiereRepo matiereRepo;
    public Matiere addMatiere(Matiere matiere)
    {
        return matiereRepo.save(matiere);
    }
    public void deleteMatiere(Long id) throws Exception {

        matiereRepo.deleteById(id);

    }
    public Matiere updateMatiere(Matiere matiere)
    {    Matiere m = matiereRepo.findById(matiere.getId()).orElseThrow(()->new RuntimeException("not found"));
        m.setNom(matiere.getNom());



        return matiereRepo.save(m);
    }
    public List<Matiere> findAll()
    {
        return matiereRepo.findAll();
    }
}
