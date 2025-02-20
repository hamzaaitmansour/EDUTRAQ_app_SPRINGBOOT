package fullStack.template.repository;

import fullStack.template.models.ChefFiliere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefFiliereRepo extends JpaRepository<ChefFiliere,Long> {
    public ChefFiliere findChefFiliereByEmail(String email);

}
