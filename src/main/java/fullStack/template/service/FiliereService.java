package fullStack.template.service;

import fullStack.template.entities.Filiere;
import fullStack.template.entities.Matiere;
import fullStack.template.entities.Seance;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Professeur;
import fullStack.template.repository.FiliereRepo;
import fullStack.template.repository.SeanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FiliereService {

@Autowired
    private FiliereRepo filiereRepo;

@Autowired
private SeanceRepo seanceRepo;
    public Filiere addFiliere(Filiere filiere)
    {
        return filiereRepo.save(filiere);
    }

    public void deleteFiliere(Long id)
    {   filiereRepo.deleteById(id);

    }
    public Filiere updateFiliere(Filiere filiere)
    {
        Filiere f = filiereRepo.findById(filiere.getId()).get();
        f.setNom(filiere.getNom());
        f.setEffectif(filiere.getEffectif());
        return filiereRepo.save(filiere);
    }

    public List<Filiere> findAll()
    {
        return filiereRepo.findAll();
    }

    public Filiere findById(Long idFiliere) {

        return filiereRepo.findById(idFiliere).orElseThrow(()->new EntityNotFoundException("Not found"));
    }

    public List<Filiere> getFilieresByProf(Professeur prof) {
        List<Seance> seances= seanceRepo.findAllByProfesseur(prof);
        List<Filiere> fls=new ArrayList<>();
        for (Seance s : seances)
        {
            if(!fls.contains(s.getFiliere()))
                fls.add(s.getFiliere());
        }
        return fls;
    }
}
