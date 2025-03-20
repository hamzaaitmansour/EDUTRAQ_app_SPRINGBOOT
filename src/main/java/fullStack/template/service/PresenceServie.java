package fullStack.template.service;

import fullStack.template.dto.PresenceRequest;
import fullStack.template.dto.PresenceResponse;
import fullStack.template.dto.StatResponse;
import fullStack.template.dto.desktop.PresenceDResponse;
import fullStack.template.dto.desktop.PresenceDeskRequest;
import fullStack.template.dto.desktop.PresenceHistRequest;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.Matiere;
import fullStack.template.entities.Presence;
import fullStack.template.entities.Seance;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Etudiant;
import fullStack.template.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;

@Service
public class PresenceServie {
     @Autowired
     private PresenceRepo presenceRepo;

     @Autowired
      SeanceRepo seanceRepo;

     @Autowired
      EtudiantRepo etudiantRepo;

     @Autowired
    FiliereRepo filiereRepo;
     @Autowired
    MatiereRepo matiereRepo;

     public Presence addP(PresenceRequest b)
     {

         Etudiant e = etudiantRepo.findById(b.getId_etudiant()).orElseThrow(()->new EntityNotFoundException("Etudiant not found"));

         Seance seance=seanceRepo.findById(b.getId_seance()).orElseThrow(()->new EntityNotFoundException("Seance not found"));
         LocalDate date=LocalDate.now();

         int week =date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
         Presence presence=new Presence();//null,b.getStatut(),b.getRemarque(),week,2025,seance,e,null,null,null);
          presence.setRemarque(b.getRemarque());
          presence.setStatut(b.getStatut());
          presence.setDate(date);
          presence.setWeek(week);
          presence.setYear(date.getYear());
          presence.setSeance(seance);
          presence.setEtudiant(e);

         return presenceRepo.save(presence);
     }
     public List<PresenceResponse> getPres(Etudiant etudiant,int week ,int year)
     {
         System.out.println("\n\n\nRecherche des etudiants par week :"+week+" et etudiant "+etudiant.getEmail()+" year "+year);
         List<Presence> list=  presenceRepo.getPresencesByWeekAndEtudiantAndYear(week,etudiant,year);
         List<PresenceResponse> listResponse=new ArrayList<>();
         for (Presence p:list)
         {
          PresenceResponse rs= new PresenceResponse(p.getId(),p.getStatut(),p.getSeance().getHeure(),p.getSeance().getJour(),p.getSeance().getType(),p.getSeance().getMatiere().getNom(),p.getSeance().getProfesseur().getFirstname(),LocalDate.of(2026,5,29));
           listResponse.add(rs);
         }

         return  listResponse;
     }


    public List<StatResponse> getStat(Long id) {
            Etudiant e = etudiantRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
            List<Seance> seances = seanceRepo.findSeancesByFiliere(e.getFiliere());
            List<StatResponse> statResponses = new ArrayList<>();

        for (Seance s : seances) {
            Double absencePercentage = presenceRepo.getAbsencePercentageBySeance(s.getId(),e.getId());

            if (absencePercentage != null) {
                StatResponse statResponse = new StatResponse();
                statResponse.setMatiere(s.getMatiere().getNom());
                statResponse.setStat(absencePercentage.intValue());
                statResponses.add(statResponse);
            }
        }

        return statResponses;
    }

    public void addAll(List<PresenceRequest> prs) {

         for (PresenceRequest p : prs)
         {
             addP(p);
         }

    }
    //

    public List<PresenceDResponse> getHistorique(PresenceHistRequest ps) {
        System.out.println("     "+ps.getFiliere_id());
        System.out.println("     "+ps.getMatiere_id());

        Filiere filiere=filiereRepo.findById(ps.getFiliere_id()).orElseThrow();
        Matiere matiere=matiereRepo.findById(ps.getMatiere_id()).orElseThrow();
        String type=ps.getType();
       // List<Presence> presences=presenceRepo.findAllByFiliereAndMatiereAndType(filiere,matiere,ps.getType());
        List<PresenceDResponse> res = new ArrayList<>();
        // Les etudiants de cette filiere
        List<Etudiant> etudiants=etudiantRepo.findEtudiantsByFiliere(filiere);

        for (Etudiant e : etudiants)
        {
            PresenceDResponse r=new PresenceDResponse();

            // Etudiant e = p.getEtudiant();
            r.setEtudiant_id(e.getId());
            r.setEtudiant_nom(e.getFirstname()+" "+e.getLastname());
            r.setEtudiant_cne(e.getCne());

            List<Presence> presences=new ArrayList<>();

                for (Presence presence : e.getPresences())

                       if (presence != null && presence.getSeance().getMatiere().getNom().equalsIgnoreCase(matiere.getNom()) && presence.getSeance().getType().equalsIgnoreCase(type))
                         presences.add(presence);

                r.setPresences(presences);

                res.add(r);

        }
        return res;
    }

    public void addAllPresences(Long id,List<PresenceDeskRequest> presences) {
          System.out.println(" Seance  id "+id);
         Seance seance=seanceRepo.findById(id).orElseThrow();

         List<Presence> pList=new ArrayList<>();
         for (PresenceDeskRequest pr : presences) {
             Presence p =new Presence();
             LocalDate date = LocalDate.now();
             p.setDate(date);
             p.setYear(date.getYear());
             p.setWeek(date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
             p.setEtudiant(etudiantRepo.findById(pr.getId()).orElseThrow());
             p.setSeance(seance);
             p.setStatut(pr.isPresent() ? "present" : "absent");
             pList.add(p);
         }
         presenceRepo.saveAll(pList);
    }
}
