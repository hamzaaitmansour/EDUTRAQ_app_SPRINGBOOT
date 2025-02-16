package fullStack.template.service;

import fullStack.template.dto.SeanceRequest;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.Salle;
import fullStack.template.entities.Seance;
import fullStack.template.exception.EntityAlreadyExistException;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeanceService {
    @Autowired
    private SeanceRepo seanceRepo;
    @Autowired
    private FiliereRepo filiererepo;
    @Autowired
    private MatiereRepo matiereRepo;
    @Autowired
    private UserAppRepo userAppRepo;
    @Autowired
    private SalleRepo salleRepo;

public Seance addSeance(SeanceRequest s)
{   Seance seance=new Seance();
    try{
        System.out.println("\n \n \n \n seance service working ... \n \n \n \n ");
       seance.setHeure(s.getHeure());
       seance.setJour(s.getJour());
       seance.setMatiere(matiereRepo.findMatiereByNom(s.getMatiere_nom()));
       seance.setFiliere(filiererepo.findById(s.getFiliere_id()).get());
       seance.setType(s.getType());
       seance.setUserApp(userAppRepo.findById(s.getProf_id()).get());
       seance.setSalle(salleRepo.findSalleByNom(s.getSalle_nom()));
        seanceRepo.save(seance);

    }catch(EntityAlreadyExistException e)
    {
      e.printStackTrace();
    }
    return seance;
}

public void deleteSeance(Long id)
{
    Seance seance=seanceRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("this is not foiund"));
    seanceRepo.delete(seance);
}

public List<Seance> getAllByFiliere(Long id)
{
    Filiere filiere= filiererepo.findById(id).orElseThrow(()-> new EntityNotFoundException("not found"));
    return  filiere.getSeances();
}
public Seance updateSeance(Seance seance)
{
    return seanceRepo.save(seance);
}


    public List<Seance> getSeances(String heure,String jour) {
    return seanceRepo.findSeancesByHeureAndJour(heure,jour);
    }
}
