package fullStack.template.service;

import fullStack.template.dto.ChefResponse;
import fullStack.template.dto.ProfRequestCordinateur;
import fullStack.template.dto.ProfSimple;
import fullStack.template.dto.desktop.ProfileChef;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.Seance;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Professeur;
import fullStack.template.models.UserApp;
import fullStack.template.repository.FiliereRepo;
import fullStack.template.repository.ProfRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfService {
    @Autowired
    private ProfRepo profRepo;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
    @Autowired
    private FiliereRepo filiereRepo;
    @PersistenceContext
    private EntityManager entityManager;

    public Professeur register(Professeur user) {
        user.setRole("PROF");
        user.setPassword(encoder.encode(user.getApogee())); // Hachage du mot de passe
        return profRepo.save(user);

    }
    public Professeur update(ProfRequestCordinateur user) {
        Professeur prof =profRepo.findById(user.getId()).orElseThrow(()->new EntityNotFoundException("not found"));
        prof.setApogee(user.getApogee());
        prof.setEmail(user.getEmail());

        return profRepo.save(prof);
    }
    public Professeur findById(Long id)
    {
        return profRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Prof not found"));
    }
    public Professeur findByEmail(String email)
    {
        return profRepo.findProfesseurByEmail(email);
    }

   public List<ProfRequestCordinateur> findAll()
   {   List<ProfRequestCordinateur> pf=new ArrayList<>();
       List<Professeur> p= profRepo.findAll();
       for(Professeur prof:p)
       {
           ProfRequestCordinateur pc=new ProfRequestCordinateur();
           pc.setId(prof.getId());
           pc.setApogee(prof.getApogee());
           pc.setEmail(prof.getEmail());
           pc.setNom(prof.getLastname()+" "+prof.getFirstname());
            pf.add(pc);
       }
       return pf;
   }
    @Transactional
    public void deleteProf(Long id) {
        Professeur professeur = profRepo.findById(id).get();
        profRepo.delete(professeur);
    }


    public List<ProfSimple> getSimpleProfs() {
        List<Professeur> p= profRepo.findAll();
        List<ProfSimple> pf=new ArrayList<>();
        for (Professeur prof : p) {
            ProfSimple ps=new ProfSimple();
            ps.setId(prof.getId());
            ps.setNom(prof.getLastname()+" "+prof.getFirstname());
            pf.add(ps);
        }
        return pf;
    }

    public List<ChefResponse> getAllChefs() {
        List<Professeur> professeurs=profRepo.findAll();
        List<Professeur> chefsss=new ArrayList<>();
        for (Professeur prof : professeurs) {
            if(prof.getRole().equals("CHEF"))
                chefsss.add(prof);
            System.out.println(prof.getRole());
        }
        System.out.println(" List de chefs "+professeurs.size());
        List<ChefResponse> chefs=new ArrayList<>();
        for (Professeur prof : chefsss) {
            ChefResponse chef=new ChefResponse();
            chef.setFiliere(prof.getFiliere().getNom());
            chef.setId(prof.getId());
            chef.setProf(prof.getLastname()+" "+prof.getFirstname());
            chefs.add(chef);
        }
        return chefs;
    }

    public void deleteChef(Long id) {
        Professeur p = findById(id);

        // On change le rôle du professeur
        p.setRole("ROLE_PROF");

        Filiere f = p.getFiliere();

        if (f != null) { // Vérification pour éviter une NullPointerException
            p.setFiliere(null);
            profRepo.save(p); // 🔥 Sauvegarder le professeur en premier pour casser la relation

            f.setProfesseur(null);
            filiereRepo.save(f); // Ensuite, sauvegarder la filière
        } else {
            profRepo.save(p); // Si pas de filière, on sauve juste le prof
        }
    }

    public void updateProfileDesktop(ProfileChef profile) {
        Professeur professeur= profRepo.findById(profile.getId()).orElseThrow(()->new EntityNotFoundException("not found"));
        professeur.setAccount_complete(true);
        professeur.setTelephone(profile.getTelephone());
        professeur.setCni_prof(profile.getCni());
        professeur.setLastname(profile.getLastname());
        professeur.setFirstname(profile.getFirstname());
        if(profile.getPassword() != null){
            if(profile.getPassword().length()>2)
              professeur.setPassword(encoder.encode(profile.getPassword()));


        }

        System.out.println(professeur.getLastname());
        System.out.println(professeur.getFirstname());
        profRepo.save(professeur);
    }
}