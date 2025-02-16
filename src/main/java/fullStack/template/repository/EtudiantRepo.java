package fullStack.template.repository;

import fullStack.template.entities.Filiere;
import fullStack.template.models.Etudiant;
import fullStack.template.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepo extends JpaRepository<Etudiant,Long> {
    public Etudiant findByEmail(String email);
    public List<Etudiant> findEtudiantsByFiliere(Filiere filiere);
}
