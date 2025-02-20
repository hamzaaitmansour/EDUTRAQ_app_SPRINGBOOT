package fullStack.template.service;

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

@Service
public class JustificationService {

    @Autowired
    private JustificationRepo justificationRepo;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private PresenceRepo presenceRepo;


    public void add(byte[] fileContent, String fileName, String contentType,String description,Long id_etudiant,Long id_presence) {


        // Création d'une nouvelle instance de JustificationAbsence
        try {
            Etudiant e =etudiantService.getEtudiant(id_etudiant);
            Presence p=presenceRepo.findById(id_presence).orElseThrow(()->new EntityNotFoundException("Presence not found"));

            JustificationAbsence justification = new JustificationAbsence();
            justification.setDescription(description);
            justification.setDocument(fileContent);
            justification.setFileName(fileName);
            justification.setContentType(contentType);
            justification.setEtudiant(e);
            justification.setPresence(p);

            // Sauvegarde dans la base de données
            justificationRepo.save(justification);

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
