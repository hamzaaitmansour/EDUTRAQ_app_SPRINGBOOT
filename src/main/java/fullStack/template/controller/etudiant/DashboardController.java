package fullStack.template.controller.etudiant;

import fullStack.template.dto.PresenceHistoireRequest;
import fullStack.template.dto.PresenceResponse;
import fullStack.template.dto.SeanceResponse;
import fullStack.template.entities.Notification;
import fullStack.template.entities.Presence;
import fullStack.template.entities.Seance;
import fullStack.template.models.Etudiant;
import fullStack.template.service.EtudiantService;
import fullStack.template.service.NotificationService;
import fullStack.template.service.PresenceServie;
import fullStack.template.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;

@RestController
@RequestMapping("/api/etudiant")
public class DashboardController {
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private SeanceService seanceService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PresenceServie presenceServie;
    // emploit de temps
    @GetMapping("/emploit/{id}")
    public ResponseEntity<List<SeanceResponse>> getEmploit(@PathVariable Long id)
    {
        System.out.println("\n\n\n\n\n"+id+"\n\n\n\n\n\n");
        Etudiant e=etudiantService.getEtudiantById(id);
        System.out.println("\n test test \n\n");
        List<SeanceResponse> list =seanceService.getAllByFiliere(e.getFiliere().getId());
        System.out.println("\n test 2   test 2\n\n");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/emails/vu/{id}")
    public ResponseEntity<Notification> setVu(@PathVariable Long id)
    {

        return new ResponseEntity<>(notificationService.setVu(id),HttpStatus.ACCEPTED);
    }
    // reçu notifications
        // last 4 notifiactions non lues
        @GetMapping("/dashboard/emails/{id}")
        public ResponseEntity<List<Notification>> getLastFourMesgs(@PathVariable Long id)
                {
                    return new ResponseEntity<>(etudiantService.getLastNotifications(id),HttpStatus.OK);
                }


        // reçu tous les notifications
        @GetMapping("/emails/{id}")
        public ResponseEntity<List<Notification>> getNots(@PathVariable Long id)
        {
            return new ResponseEntity<>(etudiantService.getEtudiant(id).getNotifications(),HttpStatus.OK);
        }

    // dashboard reçois tous les presences de cette semaine
    @GetMapping("/dashboard/presence/{email}")
    public ResponseEntity<List<PresenceResponse>>  getDashboardPresence(@PathVariable String email)
    {
        LocalDate date = LocalDate.now();

        Etudiant etudiant=etudiantService.getByEmail(email);
        System.out.println(etudiant.getFirstname()+etudiant.getLastname()+etudiant.getFiliere().getNom());

      return
        new ResponseEntity<>(presenceServie.getPres(etudiant,date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR),date.getYear()),HttpStatus.OK);

    }

    // Presence of any date
    @PostMapping("/presence/any")
     public ResponseEntity<List<PresenceResponse>> getHistoirePresence(@RequestBody PresenceHistoireRequest p)
    {
        LocalDate date = p.getDate();

        Etudiant etudiant=etudiantService.getByEmail(p.getEmail());
        System.out.println(etudiant.getFirstname()+etudiant.getLastname()+etudiant.getFiliere().getNom());

        return
                new ResponseEntity<>(presenceServie.getPres(etudiant,date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR),date.getYear()),HttpStatus.OK);

    }


    //

}
