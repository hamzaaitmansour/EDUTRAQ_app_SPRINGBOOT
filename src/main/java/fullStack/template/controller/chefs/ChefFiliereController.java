package fullStack.template.controller.chefs;

import fullStack.template.dto.*;
import fullStack.template.entities.Salle;
import fullStack.template.entities.Seance;
import fullStack.template.service.ChefService;
import fullStack.template.service.SalleService;
import fullStack.template.service.SeanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/chef")
public class ChefFiliereController {


     @Autowired
     private SeanceService seanceService;

     @Autowired
     private SalleService salleService;
    @Autowired
    private ChefService chefService;


    // ajouter seance
      @PostMapping("/seance/{id}")
    public ResponseEntity<Seance> addSeance(@PathVariable Long id,@RequestBody SeanceRequest seance)
      {

          System.out.println(seance.getJour()+"\n\n");
          return new ResponseEntity<>(seanceService.addSeance(seance,id), HttpStatus.CREATED);
      }
    // delete seance
    @DeleteMapping("/seance/{id}")
    public ResponseEntity<?> deleteSeance(@PathVariable Long id)
    {
        seanceService.deleteSeance(id);
        return  ResponseEntity.noContent().build();
    }
    // update seance
    @PutMapping("/seance")
    public ResponseEntity<Seance> updateSeance( @RequestBody Seance seance)
    {
        return  new ResponseEntity<>( seanceService.updateSeance(seance),HttpStatus.ACCEPTED);
    }
    @GetMapping("/profile/{id}")
    public ResponseEntity<ChefResponseProfile> getChefProfile(@PathVariable Long id)
    {
        return new ResponseEntity<>(chefService.getProfile(id),HttpStatus.OK);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<?> updateChefProfile(@RequestBody ChefResponseProfile profile ,@PathVariable Long id)
    {    chefService.updateProfile(id,profile);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // get All seance of filiere
    @GetMapping("/seance/{id}")
    public ResponseEntity<List<SeanceResponse>> getAllSeanceByFiliere(@PathVariable Long id)
    {
        System.out.println("\n\n\n "+id+"\n\n");
        return new ResponseEntity<>(seanceService.getAllByFiliere(id),HttpStatus.OK);
    }
    @PostMapping("/seance/check")
    public ResponseEntity<List<Salle>> getSallesEmpty(@RequestBody CheckSalle checkSalle)
    {
        // lundi - 8:30-10:30
        // -> seance where | lfoq  -> salles , select all mn ghir had salles
        List<Seance> seances = seanceService.getSeances(checkSalle.getHeure(), checkSalle.getJour());
        List<Salle> sallesNonEmpty =new ArrayList<>();
        for( Seance a : seances)
        {
            sallesNonEmpty.add(a.getSalle());
        }
        List<Salle> sallesEmpty=salleService.getall();
        sallesEmpty.removeAll(sallesNonEmpty);
       return new ResponseEntity<>(sallesEmpty,HttpStatus.OK);
    }

}
