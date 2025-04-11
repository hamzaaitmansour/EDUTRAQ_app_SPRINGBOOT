package fullStack.template.service;

import fullStack.template.dto.ChefFiliereRequest;
import fullStack.template.dto.ChefResponseProfile;
import fullStack.template.entities.Filiere;
import fullStack.template.exception.EntityAlreadyExistException;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Professeur;
import fullStack.template.repository.ProfRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ChefService {
    @Autowired
    ProfRepo   profRepo;
    @Autowired
    private ProfService profService;
    @Autowired
    private FiliereService filiereService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public Professeur addChef(ChefFiliereRequest request) {
        System.out.println("addChef");
        System.out.println("prof"+request.getId_prof()+"filiere"+request.getId_filiere());
        // Récupération du professeur
        Professeur p = profService.findById(request.getId_prof());
        System.out.println(p.getRole());

        // Vérifie si ce professeur est déjà chef
        if (p.getRole().equals("CHEF")) {
            System.out.println("Professeur déjà chef : " + p.getRole());
            throw new EntityAlreadyExistException("Ce professeur est déjà chef de filière.");
        }

        // Récupération de la filière
        Filiere f = filiereService.findById(request.getId_filiere());
        System.out.println("Filiere trouver : " + f.getNom());

        // Vérifie si la filière a déjà un chef
        if (f.getProfesseur() != null && "CHEF".equals(f.getProfesseur().getRole())) {
            throw new EntityAlreadyExistException("Cette filière a déjà un chef.");
        }

        p.setFiliere(f);
        p.setRole("CHEF");
        f.setProfesseur(p);
        filiereService.updateFiliere(f);
        return profRepo.save(p);
    }


    public ChefResponseProfile getProfile(Long id) {
       Professeur prof = profRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Professeur not found"));
        ChefResponseProfile profile = new ChefResponseProfile();
        profile.setId(prof.getId());
        profile.setCni(prof.getCni_prof());
        profile.setEmail(prof.getEmail());
        profile.setApogee(prof.getApogee());
        profile.setFirstname(prof.getFirstname());
        profile.setLastname(prof.getLastname());
        profile.setPassword("");
        profile.setFiliere(prof.getFiliere().getNom());
       return profile;
    }

    public void updateProfile(Long id,ChefResponseProfile profile) {
        Professeur professeur=profRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Professeur not found"));
       // professeur.setApogee(profile.getApogee());
        professeur.setFirstname(profile.getFirstname());
        professeur.setLastname(profile.getLastname());
        professeur.setPassword(encoder.encode(profile.getPassword()));
        professeur.setTelephone(profile.getTelephone());
        profRepo.save(professeur);




    }
}
