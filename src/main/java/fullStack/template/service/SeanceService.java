package fullStack.template.service;

import fullStack.template.dto.SeanceRequest;
import fullStack.template.dto.SeanceResponse;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.Matiere;
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
import java.util.*;

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
   public SeanceResponse getNextresponseSeance(Long id)
   {
       Seance e = getNextSeance(id);
       return new SeanceResponse(e.getId(),e.getHeure(),e.getJour(),e.getType(),e.getMatiere().getNom(),e.getProfesseur().getFirstname()+e.getProfesseur().getLastname(),e.getSalle().getNom());

   }


    private Seance getNextSeance(Long id) {
        Etudiant e = etudiantRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
        List<Seance> seances = seanceRepo.findSeancesByFiliere(e.getFiliere());

        LocalDateTime now = LocalDateTime.now();
        int currentHour = now.getHour();
        int currentMinute = now.getMinute();
        String currentDay = now.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.FRANCE)
                .toLowerCase();

        // Liste ordonnée des jours de la semaine en français
        List<String> weekDays = Arrays.asList(
                "lundi", "mardi", "mercredi",
                "jeudi", "vendredi", "samedi", "dimanche"
        );

        // 1️⃣  Trier les séances par jour puis par heure (ordre alphabétique sur la chaîne "08:30-10:30")

        seances.sort(Comparator.comparing((Seance s) -> weekDays.indexOf(s.getJour().toLowerCase()))
                .thenComparing(Seance::getHeure));


        // 2️⃣  Chercher une séance aujourd'hui après l'heure actuelle
        Optional<Seance> nextSeanceToday = seances.stream()
                .filter(s -> s.getJour().equalsIgnoreCase(currentDay))
                .filter(s -> isFutureHour(s.getHeure(), currentHour, currentMinute))
                .findFirst();

        // On retourne directement l'entité existante
        return nextSeanceToday.orElseGet(() -> seances.stream()
                .filter(s -> isFutureDay(currentDay, s.getJour(), weekDays))
                .min(Comparator.comparing(s -> weekDays.indexOf(s.getJour().toLowerCase())))
                .orElse(null));

        // 3️⃣  Sinon, trouver la prochaine séance dans les jours à venir
    }

    /**
     * Vérifie si l’horaire de la séance est strictement après l’horaire actuel.
     *
     * @param heure        Chaîne de type "08:30-10:30"
     * @param currentHour  Heure courante (ex : 9)
     * @param currentMinute Minute courante (ex : 15)
     * @return true si l’horaire de début est après l’heure actuelle, false sinon
     */
    private boolean isFutureHour(String heure, int currentHour, int currentMinute) {
        // On récupère uniquement l’heure de début
        String[] timeRange = heure.split("-");
        String[] startHourMin = timeRange[0].split(":");
        int startHour = Integer.parseInt(startHourMin[0]);
        int startMinute = Integer.parseInt(startHourMin[1]);

        return (startHour > currentHour)
                || (startHour == currentHour && startMinute > currentMinute);
    }

    /**
     * Vérifie si le jour de la séance est après le jour actuel (gère le cas du dimanche).
     *
     * @param today      Jour actuel (ex : "dimanche")
     * @param seanceDay  Jour de la séance (ex : "lundi")
     * @param weekDays   Liste des jours ["lundi", ..., "dimanche"]
     * @return true si seanceDay est dans le futur par rapport à today
     */
    private boolean isFutureDay(String today, String seanceDay, List<String> weekDays) {
        int todayIndex = weekDays.indexOf(today.toLowerCase());
        int seanceIndex = weekDays.indexOf(seanceDay.toLowerCase());

        // Cas général : l’index du jour de la séance est plus grand que l’index du jour actuel
        // Cas du dimanche : si todayIndex == 6 (dimanche), on considère tout jour >= 0 comme futur
        return (seanceIndex > todayIndex)
                || (todayIndex == weekDays.size() - 1 && seanceIndex >= 0);
    }


    public List<Matiere> getMatieresByProf(Long id) {
        List<SeanceResponse> sr= getSeancesByProf(id);
        List<Matiere> ms = new ArrayList<>();
        for (SeanceResponse e : sr)
        {
            Matiere m =new Matiere();
            m.setNom(e.getMatiere().toUpperCase());

            if(!ms.contains(m))
                ms.add(m);
        }
        Long i=1L;
        for (Matiere m : ms){
            m.setId(i);
            i++;
        }

        return ms;
    }


}
