package fullStack.template.repository;

import fullStack.template.entities.Feedback;
import fullStack.template.entities.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Long> {
    public List<Feedback> findFeedbacksByPresence(Presence presence);


}
