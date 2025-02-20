package fullStack.template.controller.etudiant;

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
        public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback)
            {
               return new ResponseEntity<>(feedbackService.addFeedback(feedback), HttpStatus.CREATED);
            }



        @PostMapping("/justification")
        public ResponseEntity<?> addJust(@RequestBody JustificationRequest jr)
                throws IOException {
                byte[] fileContent =jr.getFile().getBytes();
                String fileName = jr.getFile().getOriginalFilename();
                String contentType = jr.getFile().getContentType();
                justificationService.add(fileContent,fileName,contentType,jr.getDescription(),jr.getEtudiant_id(),jr.getPresence_id());

              return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Justification enregistrée avec succès");
        }
}