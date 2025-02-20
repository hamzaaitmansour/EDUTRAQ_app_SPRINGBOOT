package fullStack.template.service;

import fullStack.template.entities.Filiere;
import fullStack.template.entities.Notification;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.Etudiant;
import fullStack.template.repository.EtudiantRepo;
import fullStack.template.repository.FiliereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepo etudiantRepo;
    @Autowired
    private FiliereRepo filiereRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public Etudiant addEtudiant(Etudiant etudiant)
    { // add to filiere 1 Info Test

        etudiant.setFiliere(filiereRepo.findById(1L).get());

  etudiant.setPassword(encoder.encode(etudiant.getPassword()));
        return etudiantRepo.save(etudiant);
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
    {
        return etudiantRepo.findAll();
    }
    public List<Etudiant> getAllEtudiantsByFiliere(Long id)
    {Filiere f= filiereRepo.findById(id).orElseThrow(()->new EntityNotFoundException("filiere not found"));
       return  etudiantRepo.findEtudiantsByFiliere(f);
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
