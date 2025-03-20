package fullStack.template.controller.etudiant;

import fullStack.template.dto.FeedbackRequest;
import fullStack.template.dto.JustificationRequest;
import fullStack.template.entities.Feedback;
import fullStack.template.entities.JustificationAbsence;
import fullStack.template.service.FeedbackService;
import fullStack.template.service.JustificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/etudiant/noter")
public class Noter {

    // ajouter un feedback
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private JustificationService justificationService;
        @PostMapping("/feedback")
        public ResponseEntity<Feedback> addFeedback(@RequestBody FeedbackRequest f)
            {
                System.out.println("\n\n\n "+f.toString()+"\n\n");
               return new ResponseEntity<>(feedbackService.addFeedback(f), HttpStatus.CREATED);
            }



        @PostMapping("/justification")
        public ResponseEntity<?> addJust(@RequestBody JustificationRequest jr)
        {
            System.out.println("\n\n\n Post a Justification "+"  \n"+jr.getPresence_id());
            System.out.println("\n\n"+jr.toString()+"\n\n");

                justificationService.add(jr);
                return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Justification enregistrée avec succès");
        }
}