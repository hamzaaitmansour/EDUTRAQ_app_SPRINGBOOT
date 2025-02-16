package fullStack.template.service;

import fullStack.template.entities.Filiere;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Etudiant;
import fullStack.template.repository.EtudiantRepo;
import fullStack.template.repository.FiliereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepo etudiantRepo;
    @Autowired
    private FiliereRepo filiereRepo;
    public Etudiant addEtudiant(Etudiant etudiant)
    {
        return etudiantRepo.save(etudiant);
    }
    public void deleteEtudiant(Long id)
    {
         etudiantRepo.deleteById(id);
    }
    public Etudiant updateEtudiant(Etudiant etudiant)
    {
        return  etudiantRepo.save(etudiant);
    }
    public List<Etudiant> getAllEtudiants()
    {
        return etudiantRepo.findAll();
    }
    public List<Etudiant> getAllEtudiantsByFiliere(Long id)
    {Filiere f= filiereRepo.findById(id).orElseThrow(()->new EntityNotFoundException("filiere not found"));
       return  etudiantRepo.findEtudiantsByFiliere(f);
    }
    public Etudiant getEtudiant(String email)
    {
        return etudiantRepo.findByEmail(email);
    }

}
