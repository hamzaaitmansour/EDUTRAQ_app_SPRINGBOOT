package fullStack.template.controller.prof;

import fullStack.template.dto.PresenceHistoireRequest;
import fullStack.template.dto.desktop.PresenceDResponse;
import fullStack.template.dto.desktop.PresenceHistRequest;
import fullStack.template.service.PresenceServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prof/historique")
public class HistoriqueProfAbscenceController {


    @Autowired
    private PresenceServie presenceServie;

    // get Presences dune filiere et une matiere et une type
    @PostMapping("")
    public List<PresenceDResponse> getHistoriqeAbs(@RequestBody PresenceHistRequest ps)
    {
        return presenceServie.getHistorique(ps);
    }
}
