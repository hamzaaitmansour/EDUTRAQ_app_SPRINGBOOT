package fullStack.template.service;

import fullStack.template.models.UserApp;
import fullStack.template.models.UserPrincipal;
import fullStack.template.repository.UserAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAppService implements UserDetailsService {
    @Autowired
    private UserAppRepo userRepo;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(); // Injecté correctement

    public UserAppService() {}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserApp user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email);
        }
        return new UserPrincipal(user);
    }

    public String register(UserApp user) {
        user.setPassword(encoder.encode(user.getPassword())); // Hachage du mot de passe
        userRepo.save(user);
        return "Success";
    }

    public String verify(String email, String password) {
        UserDetails userDetails;
        try {
            userDetails = loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            return "Fail"; // L'utilisateur n'existe pas
        }

        // Vérifier si le mot de passe correspond
        if (encoder.matches(password, userDetails.getPassword())) {
            return jwtService.generateToken(userDetails); // Passer `userDetails` ici
        }
        return "Fail"; // Mauvais mot de passe
    }

}
