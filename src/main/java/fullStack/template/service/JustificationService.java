package fullStack.template.service;

import fullStack.template.dto.Image;
import fullStack.template.dto.JustificationRequest;
import fullStack.template.entities.JustificationAbsence;
import fullStack.template.entities.Presence;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Etudiant;
import fullStack.template.repository.JustificationRepo;
import fullStack.template.repository.PresenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
public class JustificationService {

    @Autowired
    private JustificationRepo justificationRepo;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private PresenceRepo presenceRepo;


    public void add(JustificationRequest js) {
            try {JustificationAbsence jAbsence=new JustificationAbsence();
                if(js.getFile()!=null){
                String profileData = js.getFile();
                if (profileData == null || profileData.isEmpty()) {
                    throw new IllegalArgumentException("Justification image data is missing");
                }

                // Split the data URL if present
                String[] parts = profileData.split(",");
                String base64Image = parts.length > 1 ? parts[1] : parts[0];

                // Decode the Base64 string
                byte[] imageBytes = Base64.getDecoder().decode(base64Image);
                    jAbsence.setDocument(imageBytes);
                }

                Etudiant e = etudiantService.getEtudiant(js.getEtudiant_id());
                Presence p = presenceRepo.findById(js.getPresence_id()).orElseThrow(()->new RuntimeException("Not found "));

                jAbsence.setPresence(p);
                jAbsence.setEtudiant(e);
                jAbsence.setDescription(js.getDescription());

                justificationRepo.save(jAbsence);

            } catch (IllegalArgumentException e) {
                // Handle decoding errors
                throw new RuntimeException("Invalid Base64 image data", e);
            }

        }

}
