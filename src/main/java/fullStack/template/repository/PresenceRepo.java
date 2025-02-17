package fullStack.template.repository;

import fullStack.template.entities.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenceRepo extends JpaRepository<Presence,Long> {

}
