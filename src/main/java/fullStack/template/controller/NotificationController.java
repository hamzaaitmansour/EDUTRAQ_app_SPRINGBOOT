package fullStack.template.controller;

import fullStack.template.dto.NotificationRequest;
import fullStack.template.entities.Notification;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.UserApp;
import fullStack.template.repository.UserAppRepo;
import fullStack.template.service.NotificationService;
import fullStack.template.service.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {



    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserAppRepo userAppRepo;


    @PostMapping("/")
    public ResponseEntity<Notification> add(@RequestBody NotificationRequest n)
    {
        System.out.println("\n \n \n Notifications Add ...\n "+n.getId()+"\n \n ");
        UserApp user= userAppRepo.findById(n.getId()).orElseThrow(()->new EntityNotFoundException("User Not Found"));

        Notification not =new Notification(null,n.getMessage(),false,"Schedule", LocalDate.now(),user);

        return new ResponseEntity<>(notificationService.addNotification(not), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public List<Notification> getNots(@PathVariable Long id)
    {
        UserApp userApp= userAppRepo.findById(id).orElseThrow(()->new RuntimeException(""));
        return userApp.getNotifications();
    }



}
