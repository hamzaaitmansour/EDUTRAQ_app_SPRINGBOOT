package fullStack.template.service;

import fullStack.template.dto.Image;
import fullStack.template.dto.JustificationRequest;
import fullStack.template.dto.desktop.JustRequestD;
import fullStack.template.dto.desktop.JustResponseD;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.JustificationAbsence;
import fullStack.template.entities.Matiere;
import fullStack.template.entities.Presence;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Etudiant;
import fullStack.template.models.Professeur;
import fullStack.template.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class JustificationService {

    @Autowired
    private JustificationRepo justificationRepo;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private PresenceRepo presenceRepo;
    @Autowired
    private ProfRepo profRepo;
    @Autowired
    private MatiereRepo matiereRepo;
    @Autowired
    private FiliereRepo filiereRepo;


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
                jAbsence.setDate(LocalDate.now());

                justificationRepo.save(jAbsence);

            } catch (IllegalArgumentException e) {
                // Handle decoding errors
                throw new RuntimeException("Invalid Base64 image data", e);
            }

        }



        public List<JustResponseD> getAllForProfFiliereMatiere(JustRequestD k)
        {
            System.out.println("    id matiere : "+k.getId_matiere());
            Matiere m= matiereRepo.findById(k.getId_matiere()).orElseThrow();
            Filiere f = filiereRepo.findById(k.getId_filiere()).orElseThrow();
            Professeur p=profRepo.findById(k.getId_prof()).orElseThrow();



            List<JustificationAbsence> list =justificationRepo.findAllByProfesseurAndMatiereAndFiliere(p,m,f);

            List<JustResponseD> listResponse=new ArrayList<>();
            for(JustificationAbsence just:list)
            {
                JustResponseD d=new JustResponseD();
                d.setId(just.getId());
                d.setFiliere(just.getPresence().getSeance().getFiliere().getNom());
                d.setMatiere(just.getPresence().getSeance().getMatiere().getNom());
                d.setEtudiant(just.getEtudiant().getFirstname()+" "+just.getEtudiant().getLastname());
                d.setDescription(just.getDescription());
                d.setDocument(just.getDocument());
               if(just.getDate() != null)
                d.setDate(just.getDate().toString());
                listResponse.add(d);

            }
            return listResponse;
        }

    public List<JustResponseD> getLastJustificationFiliere(Long id ) {
        JustificationAbsence j = justificationRepo.findLatestByProfesseur(profRepo.findById(id).orElseThrow());
        if(j == null)
            return new ArrayList<JustResponseD>();
        Filiere f= j.getPresence().getSeance().getFiliere();
        List<JustificationAbsence> list =justificationRepo.findByFiliere(f);

        List<JustResponseD> listResponse=new ArrayList<>();
        for(JustificationAbsence just:list)
        {
            JustResponseD d=new JustResponseD();
            d.setId(just.getId());
            d.setFiliere(just.getPresence().getSeance().getFiliere().getNom());
            d.setMatiere(just.getPresence().getSeance().getMatiere().getNom());
            d.setEtudiant(just.getEtudiant().getFirstname()+" "+just.getEtudiant().getLastname());
            d.setDescription(just.getDescription());
            d.setDocument(just.getDocument());
            if(just.getDate() != null)
            d.setDate(just.getDate().toString());
            listResponse.add(d);

        }
        return listResponse;
    }
}
