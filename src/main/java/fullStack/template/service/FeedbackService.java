package fullStack.template.service;

import fullStack.template.dto.FeedbackRequest;
import fullStack.template.entities.Feedback;
import fullStack.template.entities.Presence;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.repository.FeedbackRepo;
import fullStack.template.repository.PresenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepo feedbackRepo;
    @Autowired
    private PresenceRepo presenceRepo;

    public Feedback addFeedback(FeedbackRequest f)
    {
        Presence p = presenceRepo.findById(f.getPresence_id()).orElseThrow(()->new EntityNotFoundException("Not found "));
        Feedback feedback=new Feedback();
        feedback.setPresence(p);
        feedback.setCommentaire(f.getMessage());
        return feedbackRepo.save(feedback);
    }
    public List<Feedback> getAllFeedbacks(Long id_presence)
    {
        return feedbackRepo.findFeedbacksByPresence(presenceRepo.findById(id_presence).orElseThrow(()->new EntityNotFoundException("Not found ")));
    }
}
