package fullStack.template.service;

import fullStack.template.dto.SeanceRequest;
import fullStack.template.dto.SeanceResponse;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.Salle;
import fullStack.template.entities.Seance;
import fullStack.template.exception.EntityAlreadyExistException;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Etudiant;
import fullStack.template.models.Professeur;
import fullStack.template.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SeanceService {
    @Autowired
    private SeanceRepo seanceRepo;
    @Autowired
    private FiliereRepo filiererepo;
    @Autowired
    private MatiereRepo matiereRepo;
    @Autowired
    private ProfRepo profRepo;
    @Autowired
    private SalleRepo salleRepo;
    @Autowired
    private  EtudiantRepo etudiantRepo;

public Seance addSeance(SeanceRequest s)
{   Seance seance=new Seance();
    try{
        System.out.println("\n \n \n \n seance service working ... \n \n \n \n ");
       seance.setHeure(s.getHeure());
       seance.setJour(s.getJour());
       seance.setMatiere(matiereRepo.findById(s.getId_matiere()).get());
       seance.setFiliere(filiererepo.findById(s.getId_filiere()).get());
       seance.setType(s.getType());
       seance.setProfesseur(profRepo.findById(s.getId_user()).get());
       seance.setSalle(salleRepo.findById(s.getId_salle()).get());
        seanceRepo.save(seance);

    }catch(EntityAlreadyExistException e)
    {
      e.printStackTrace();
    }
    return seance;
}

public void deleteSeance(Long id)
{
    Seance seance=seanceRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("this is not found"));
    seanceRepo.delete(seance);
}

public List<SeanceResponse> getAllByFiliere(Long id)
{
    System.out.println("\n\n\n\n get all by filieres \n\n\n\n\n");
    Filiere filiere= filiererepo.findById(id).orElseThrow(()-> new EntityNotFoundException("not found"));
    List<Seance> s=  filiere.getSeances();
    List<SeanceResponse> sr=new ArrayList<>();
    for(Seance e : s)
    {
        SeanceResponse r=new SeanceResponse(e.getId(),e.getHeure(),e.getJour(),e.getType(),e.getMatiere().getNom(),e.getProfesseur().getFirstname()+e.getProfesseur().getLastname(),e.getSalle().getNom());
        sr.add(r);
    }
    return sr;
}
    public Seance updateSeance(Seance seance)
{
    return seanceRepo.save(seance);
}


    public List<Seance> getSeances(String heure,String jour) {
    return seanceRepo.findSeancesByHeureAndJour(heure,jour);
    }

    public List<SeanceResponse> getSeancesByProf(Long id )
    {
        Professeur prof=profRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Nor found"));
        List<Seance> seances=seanceRepo.findAllByProfesseur(prof);
        List<SeanceResponse> sr=new ArrayList<>();
        for(Seance e : seances)
        {
            SeanceResponse r=new SeanceResponse(e.getId(),e.getHeure(),e.getJour(),e.getType(),e.getMatiere().getNom(),e.getProfesseur().getFirstname()+e.getProfesseur().getLastname(),e.getSalle().getNom());
            sr.add(r);
        }
        return sr;

    }
    public SeanceResponse getNextSeance(Long id) {
        Etudiant e =etudiantRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Not found"));
        List<Seance> seances =  seanceRepo.findSeancesByFiliere(e.getFiliere());
        LocalDateTime dateTime = LocalDateTime.now();
        int heure = dateTime.getHour();
        String jour = dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRANCE).toLowerCase();

        for (Seance s : seances)
        {   if(s.getJour().toLowerCase().equals(jour))
            {
                switch (heure)
                {
                   // case (heure>8 && heure<10)

                }
            }

        }
return  null;

    }
}
