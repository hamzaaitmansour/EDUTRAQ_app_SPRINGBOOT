package fullStack.template.service;

import fullStack.template.dto.ProfRequestCordinateur;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Professeur;
import fullStack.template.models.UserApp;
import fullStack.template.repository.ProfRepo;
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

    public Professeur register(Professeur user) {
        user.setRole("ROLE_PROF");
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

         public void deleteProf(Long id )
            {
                profRepo.deleteById(id);
            }  }