package fullStack.template.controller.etudiant;

import fullStack.template.dto.PresenceResponse;
import fullStack.template.dto.SeanceResponse;
import fullStack.template.entities.Notification;
import fullStack.template.entities.Seance;
import fullStack.template.models.Etudiant;
import fullStack.template.service.EtudiantService;
import fullStack.template.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiant")
public class DashboardController {
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private SeanceService seanceService;
    // emploit de temps
    @GetMapping("/emploit/{id}")
    public ResponseEntity<List<SeanceResponse>> getEmploit(@PathVariable Long id)
    {
        System.out.println("\n\n\n\n\n"+id+"\n\n\n\n\n\n");
        Etudiant e=etudiantService.getEtudiantById(id);
        System.out.println("\n test test \n\n");
        List<SeanceResponse> list =seanceService.getAllByFiliere(e.getFiliere_etudiant().getId());
        System.out.println("\n test 2   test 2\n\n");
        return new ResponseEntity<>(list, HttpStatus.OK);
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
    public ResponseEntity<List<PresenceResponse>>  getDashboardPresence()
    {
return null;

    }

    // Profile j'ai email et je le donne le etudiant



    //

}
