package fullStack.template.service;

import fullStack.template.dto.PresenceRequest;
import fullStack.template.entities.Presence;
import fullStack.template.entities.Seance;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Etudiant;
import fullStack.template.repository.EtudiantRepo;
import fullStack.template.repository.PresenceRepo;
import fullStack.template.repository.SeanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;

@Service
public class PresenceServie {
     @Autowired
     private PresenceRepo presenceRepo;

     @Autowired
      SeanceRepo seanceRepo;

     @Autowired
      EtudiantRepo etudiantRepo;

     public Presence addP(PresenceRequest b)
     {
         System.out.println("\n\n\n hy 1\n\n"+b.getId_etudiant());
         Etudiant e = etudiantRepo.findById(b.getId_etudiant()).orElseThrow(()->new EntityNotFoundException("Etudiant not found"));
         System.out.println("\n\n\n hy 1\n\n");
         Seance seance=seanceRepo.findById(b.getId_seance()).orElseThrow(()->new EntityNotFoundException("Seance not found"));
         LocalDate daate=LocalDate.now();
         int week =daate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
         Presence presence=new Presence(null,b.getStatut(),b.getRemarque(),week,b.getDate().getYear(),seance,e,null,null,null);
         return presenceRepo.save(presence);
     }
     public List<Presence> getPres(Etudiant etudiant,int week ,int year)
     {
         return  presenceRepo.getPresencesByWeekAndEtudiantAndYear(week,etudiant,year);
     }


}
