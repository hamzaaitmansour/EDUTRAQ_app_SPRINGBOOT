package fullStack.template.repository;

import fullStack.template.entities.Notification;
import fullStack.template.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Long> {

}
