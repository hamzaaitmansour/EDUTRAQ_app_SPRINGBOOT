package fullStack.template.service;

import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Professeur;
import fullStack.template.models.UserApp;
import fullStack.template.repository.ProfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfService {
    @Autowired
    private ProfRepo profRepo;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public Professeur register(Professeur user) {
        user.setPassword(encoder.encode(user.getPassword())); // Hachage du mot de passe
       return profRepo.save(user);

    }
    public Professeur update(Professeur user) {
        user.setPassword(encoder.encode(user.getPassword())); // Hachage du mot de passe
        return profRepo.save(user);
    }
    public Professeur findById(Long id)
    {
        return profRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Prof not found"));
    }
    public Professeur findByEmail(String email)
    {
        return profRepo.findProfesseurByEmail(email);
    }

   public List<Professeur> findAll()
   {
       return profRepo.findAll();
   }

         public void deleteProf(Long id )
            {
                profRepo.deleteById(id);
            }  }