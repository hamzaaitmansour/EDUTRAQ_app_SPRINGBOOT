package fullStack.template.service;

import fullStack.template.dto.FiliereSimple;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.Matiere;
import fullStack.template.entities.Seance;
import fullStack.template.exception.EntityAlreadyExistException;
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
        System.out.println("add filiere "+filiere.getNom());
        Filiere f =filiereRepo.findFiliereByNom(filiere.getNom());
        if (f != null)
        {
            throw new EntityAlreadyExistException("cette Filiere : "+filiere.getNom()+" est deja existe");
         }

        return filiereRepo.save(filiere);
    }

    public void deleteFiliere(Long id)
    {   filiereRepo.deleteById(id);

    }
    public Filiere updateFiliere(Filiere filiere)
    {
        Filiere f = filiereRepo.findById(filiere.getId()).orElseThrow(()-> new EntityNotFoundException("La Filiere est deja existe"));
            f.setNom(filiere.getNom());
        f.setEffectif(filiere.getEffectif());
        return filiereRepo.save(filiere);
    }

    public List<Filiere> findAll()
    {
        return filiereRepo.findAll();
    }

    public Filiere findById(Long idFiliere) {

        return filiereRepo.findById(idFiliere).orElseThrow( ()->new EntityNotFoundException("Not found"));
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

    public List<FiliereSimple> getSimples() {

        List<Filiere> fls=filiereRepo.findAll();
        List<FiliereSimple> ls=new ArrayList<>();
        for (Filiere f : fls)
        {  if (!(f.getProfesseur() != null && "CHEF".equals(f.getProfesseur().getRole())) ){

            FiliereSimple fs=new FiliereSimple();
            fs.setNom(f.getNom());
            fs.setId(f.getId());
            ls.add(fs);
        }}
        return  ls;
    }
}
