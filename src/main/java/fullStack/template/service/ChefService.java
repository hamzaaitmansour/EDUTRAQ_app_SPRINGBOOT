package fullStack.template.service;

import fullStack.template.dto.ChefFiliereRequest;
import fullStack.template.entities.Filiere;
import fullStack.template.models.ChefFiliere;
import fullStack.template.models.Professeur;
import fullStack.template.repository.ChefFiliereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ChefService {
    @Autowired
    private ProfService profService;
    @Autowired
    private FiliereService filiereService;
    @Autowired
    private ChefFiliereRepo chefFiliereRepo;
    public ChefFiliere addChef(ChefFiliereRequest request)
    {
        Professeur p= profService.findById(request.getId_prof());
        Filiere f =filiereService.findById(request.getId_filiere());
        ChefFiliere c = new ChefFiliere();
        c.setFiliere(f);
        c.setApogee(p.getApogee());
        c.setPassword(p.getPassword());
        c.setCni_prof(p.getCni_prof());
        c.setEmail(p.getEmail());
        c.setFirstname(p.getFirstname());
        c.setLastname(p.getLastname());

        c.setSeances(p.getSeances());
        c.setId(p.getId());
        c.setRole("ROLE_CHEF");
        c.setTelephone(p.getTelephone());
        c.setNotifications(p.getNotifications());
        return  chefFiliereRepo.save(c);
    }
    public List<ChefFiliere> getAll()
    {
        return  chefFiliereRepo.findAll();
    }
    public void delete(Long id)
    {
         chefFiliereRepo.deleteById(id);
    }
    public ChefFiliere update(ChefFiliere c)
    {
       return chefFiliereRepo.save(c);
    }

    public ChefFiliere findByEmail(String email)
    {
        return chefFiliereRepo.findChefFiliereByEmail(email);
    }
}
