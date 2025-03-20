package fullStack.template.service;

import fullStack.template.dto.EtudiantResponsedesktop;
import fullStack.template.dto.Image;
import fullStack.template.dto.UpdateEtudiant;
import fullStack.template.dto.desktop.FingerPrint;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.Notification;
import fullStack.template.entities.Seance;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Etudiant;
import fullStack.template.models.Professeur;
import fullStack.template.repository.EtudiantRepo;
import fullStack.template.repository.FiliereRepo;
import fullStack.template.repository.ProfRepo;
import fullStack.template.repository.SeanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepo etudiantRepo;

    @Autowired
    ChefService chefService;
    @Autowired
    private SeanceRepo seanceRepo;
    @Autowired
    private FiliereRepo filiereRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private ProfRepo profRepo;

    public String addEtudiant(Image etudiant, Long id) {
        try {
            Professeur professeur = profRepo.findById(id).orElseThrow();
            Filiere filiere = professeur.getFiliere();
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
            e.setPassword(encoder.encode(etudiant.getPassword()));
            e.setProfile(imageBytes);
            e.setCni_etudiant(etudiant.getCni());
            e.setCne(etudiant.getCne());
            e.setFiliere(filiere);
            e.setTelephone(etudiant.getTelephone());
            e.setLastname(etudiant.getLastname());
            e.setFirstname(etudiant.getFirstname());
            //e.setFiliere(filiereRepo.findById(etudiant.getId_filiere())
                   // .orElseThrow(() -> new EntityNotFoundException("Filiere not found")));
            e.setRole("ETUDIANT");

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
    {
        Professeur professeur= profRepo.findById(id).orElseThrow();
        Filiere f=professeur.getFiliere();
        List<Etudiant> list=etudiantRepo.findEtudiantsByFiliere(f);
        List<Image> listImage =new ArrayList<>();
        for(Etudiant e : list)
        {


            Image response= new Image();

            response.setEmail(e.getEmail());

            response.setPassword(e.getPassword());

            response.setProfile("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(e.getProfile()));
            response.setCne(e.getCne());
            response.setId(e.getId());
            response.setTelephone(e.getTelephone());
            response.setLastname(e.getLastname());
            response.setFirstname(e.getFirstname());
            response.setFinger(e.getEmpreinte() != null);
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

    public List<EtudiantResponsedesktop> getByFliliereDesk(Long id)
    {
        //Filiere filiere= filiereRepo.findById(id).orElseThrow(()->new EntityNotFoundException("filiere non trouver "));
        Professeur professeur=profRepo.findById(id).get();
        Filiere filiere=professeur.getFiliere();
        List<EtudiantResponsedesktop> liste=new ArrayList<>();
        List<Etudiant> etudiants=etudiantRepo.findEtudiantsByFiliere(filiere);
        for (Etudiant e : etudiants)
        {
            EtudiantResponsedesktop r = new EtudiantResponsedesktop();
            r.setCne(e.getCne());
            r.setCni(e.getCni_etudiant());
            r.setNom(e.getFirstname());
            r.setPrenom(e.getLastname());
            r.setId(e.getId());
            liste.add(r);
        }
        return liste;
    }


    public List<Notification> getLastNotifications(Long id)
    {
         Etudiant e = etudiantRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Etudiant not found"));
        List<Notification> list = e.getNotifications();
        List<Notification> reversedList = new ArrayList<>(list);
        Collections.reverse(reversedList);

        return reversedList.subList(0, Math.min(4, reversedList.size()));
    }


    public Etudiant getByEmail(String email) {
        return etudiantRepo.findByEmail(email);
    }

    public List<EtudiantResponsedesktop> getAllByBySeance(Long id) {
        Seance seance=seanceRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Not Found"));
        List<Etudiant> etudiants= etudiantRepo.findEtudiantsByFiliere(seance.getFiliere());
        List<EtudiantResponsedesktop> etudDesktop=new ArrayList<>();
        for (Etudiant e : etudiants)
        {
            EtudiantResponsedesktop es=new EtudiantResponsedesktop();
            es.setCne(e.getCne());
            // es.setProfile(e.getProfile());
            es.setId(e.getId());
            es.setEmpreinte(e.getEmpreinte());
            es.setNom(e.getFirstname());
            es.setPrenom(e.getLastname());
            etudDesktop.add(es);
        }
        return etudDesktop;
        // Notifications
        // Justifications
        // Historique
        // Presence
        //
    }
        public List<Etudiant> getsByChef(Long id) {
        Professeur chef=profRepo.findById(id).orElseThrow();
        Filiere filiere = chef.getFiliere();
        List<Etudiant> etudiants= etudiantRepo.findEtudiantsByFiliere(filiere);
        List<Etudiant> e=new ArrayList<>();
        for (Etudiant et : etudiants)
        {
            if(et.getEmpreinte() == null)
                e.add(et);
        }
        return e;
    }

    public void saveFinger(FingerPrint fingerPrint) {
        Etudiant e = etudiantRepo.findById(fingerPrint.getId())
                .orElseThrow(() -> new EntityNotFoundException("Etudiant non trouvé"));
        // Décodage de la chaîne Base64 en byte[]
        byte[] empreinteBytes = Base64.getDecoder().decode(fingerPrint.getEmpreinte());
        e.setEmpreinte(empreinteBytes);
        etudiantRepo.save(e);
    }

    public void updateEtudiantFromChef(UpdateEtudiant etudiant,Long id) {
        Etudiant e=etudiantRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Etudiant not found"));
         if (e.getCne() != null)
           e.setCne(etudiant.getCne());
         if (e.getCni_etudiant() != null)
        e.setCni_etudiant(etudiant.getCni());
         if (e.getFirstname()  != null)
            e.setFirstname(etudiant.getFirstname());
         if (e.getLastname() != null)
        e.setLastname(etudiant.getLastname());
         if (e.getTelephone() != null)
        e.setTelephone(etudiant.getTelephone());

        etudiantRepo.save(e);
    }
}
