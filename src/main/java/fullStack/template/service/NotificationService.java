package fullStack.template.service;

import fullStack.template.entities.Notification;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
@Autowired
    private NotificationRepo notificationRepo;
 public Notification addNotification(Notification n)
 {
     System.out.println(n.getMessage()+n.getId()+n.getUserApp().getLastname());
     return notificationRepo.save(n);
 }
 public Notification setVu(Long id)
 {

     Notification n = notificationRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Notification not found"));
     n.setVu(true);
     return notificationRepo.save(n);

 }

}
