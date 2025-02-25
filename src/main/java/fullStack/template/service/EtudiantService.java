package fullStack.template.service;

import fullStack.template.dto.Image;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.Notification;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Etudiant;
import fullStack.template.repository.EtudiantRepo;
import fullStack.template.repository.FiliereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepo etudiantRepo;
    @Autowired
    private FiliereRepo filiereRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public String addEtudiant(Image etudiant) {
        try {
            String profileData = etudiant.getProfile();
            if (profileData == null || profileData.isEmpty()) {
                throw new IllegalArgumentException("Profile image data is missing");
            }

            // Split the data URL if present
            String[] parts = profileData.split(",");
            String base64Image = parts.length > 1 ? parts[1] : parts[0];

            // Decode the Base64 string
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            // Create and populate Etudiant entity
            Etudiant e = new Etudiant();
            e.setEmail(etudiant.getEmail());
            e.setPassword(etudiant.getPassword());
            e.setProfile(imageBytes);
            e.setCni_etudiant(etudiant.getCni());
            e.setCne(etudiant.getCne());
            e.setTelephone(etudiant.getTelephone());
            e.setLastname(etudiant.getLastname());
            e.setFirstname(etudiant.getFirstname());
            e.setFiliere(filiereRepo.findById(etudiant.getId_filiere())
                    .orElseThrow(() -> new EntityNotFoundException("Filiere not found")));
            e.setRole("ROLE_ETUDIANT");

            // Save the entity
            etudiantRepo.save(e);

            return "Image uploaded and saved successfully.";
        } catch (IllegalArgumentException e) {
            // Handle decoding errors
            throw new RuntimeException("Invalid Base64 image data", e);
        }
    }
    public void deleteEtudiant(Long id)
    {
         etudiantRepo.deleteById(id);
    }
    public Etudiant updateEtudiant(Etudiant etudiant)
    {


        return  etudiantRepo.save(etudiant);
    }
    public List<Etudiant> getAllEtudiants()
    {  List<Etudiant> list=etudiantRepo.findAll();
       List<Image> listImage =new ArrayList<>();
       for(Etudiant e : list)
       {


           Image response= new Image();

           response.setEmail(e.getEmail());

           response.setPassword(e.getPassword());

           response.setProfile("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(e.getProfile()));
           response.setCne(e.getCne());
           listImage.add(response);

       }
        return etudiantRepo.findAll();
    }
    public List<Image> getAllEtudiantsByFiliere(Long id)
    {Filiere f= filiereRepo.findById(id).orElseThrow(()->new EntityNotFoundException("filiere not found"));

        List<Etudiant> list=etudiantRepo.findEtudiantsByFiliere(f);
        List<Image> listImage =new ArrayList<>();
        for(Etudiant e : list)
        {


            Image response= new Image();

            response.setEmail(e.getEmail());

            response.setPassword(e.getPassword());

            response.setProfile("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(e.getProfile()));
            response.setCne(e.getCne());
            listImage.add(response);

        }
        return listImage;
    }
    public Etudiant getEtudiant(String email)
    {
        return etudiantRepo.findByEmail(email);
    }
    public Etudiant getEtudiant(Long id)
    {
        return etudiantRepo.findById(id).orElseThrow(()->new EntityNotFoundException("etudiant non trouver "));
    }

    public Etudiant getEtudiantById(Long id) {
        System.out.println("Etudiant cherche ...\n\n\n");
        return etudiantRepo.findById(id).orElseThrow(()->new EntityNotFoundException("etudiant non trouver"));

    }


    public List<Notification> getLastNotifications(Long id)
    {
         Etudiant e = etudiantRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Etudiant not found"));
        List<Notification> list = e.getNotifications();
        List<Notification> reversedList = new ArrayList<>(list);
        Collections.reverse(reversedList);

        return reversedList.subList(0, Math.min(4, reversedList.size()));
    }

    public Etudiant getByEmpreinte(String empreinte) {
        return etudiantRepo.findByEmpreinte(empreinte);
    }
    public Etudiant getByEmail(String email) {
        return etudiantRepo.findByEmail(email);
    }
}
