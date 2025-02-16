package fullStack.template.service;

import fullStack.template.entities.Filiere;
import fullStack.template.entities.Matiere;
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
    public void deleteMatiere(String nom) throws Exception {
        Matiere m = matiereRepo.findMatiereByNom(nom);
        if (m == null) {
            throw new Exception("Matière avec le nom '" + nom + "' non trouvée");
        }
        matiereRepo.delete(m);

    }

    public List<Matiere> findAll()
    {
        return matiereRepo.findAll();
    }
}
