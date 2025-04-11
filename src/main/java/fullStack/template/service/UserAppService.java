package fullStack.template.service;

import fullStack.template.dto.CordinateurResponseProfile;
import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.UserApp;
import fullStack.template.models.UserPrincipal;
import fullStack.template.repository.UserAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserAppService implements UserDetailsService {
    @Autowired
    private UserAppRepo userRepo;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(); // Injecté correctement
    @Autowired
    private UserAppRepo userAppRepo;

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
            return "Email";
        }

        // Vérifier si le mot de passe correspond
        if (encoder.matches(password, userDetails.getPassword())) {
            return jwtService.generateToken(userDetails); // Passer `userDetails` ici
        }
        return "Fail"; // Mauvais mot de passe
    }


    public CordinateurResponseProfile getProfile(Long id) {
        CordinateurResponseProfile profile=new CordinateurResponseProfile();

        UserApp userApp=userRepo.findById(id).orElseThrow(()->new EntityNotFoundException("User Not Found"));
        profile.setFirstname(userApp.getFirstname());
        profile.setLastname(userApp.getLastname());
        profile.setEmail(userApp.getEmail());
        profile.setTelephone(userApp.getTelephone());
        return  profile;

    }

    public void updateProfile(Long id, CordinateurResponseProfile profile) {
        UserApp userApp=userRepo.findById(id).orElseThrow(()->new EntityNotFoundException("User Not Found"));
        userApp.setFirstname(profile.getFirstname());
        userApp.setLastname(profile.getLastname());
        if(profile.getPassword() != null)
           userApp.setPassword(encoder.encode(profile.getPassword()));
        System.out.println("\n\n\n Telephone est "+userApp.getTelephone());
        userApp.setTelephone(profile.getTelephone());
        userRepo.save(userApp);
    }
    @Autowired
    private EmailService emailService;
    public void forgotPassword(String email) {
        System.out.println(email);
        UserApp user =userAppRepo.findByEmail(email);
        if(user == null) {
            throw  new EntityNotFoundException("User not found");
        } String password = genererMotDePasseAlphanumerique();
        user.setPassword(encoder.encode(password));
        String sujet = "Réinitialisation de votre mot de passe - RoomifyEnsa";
        String message = """
Bonjour,

Vous avez demandé à réinitialiser votre mot de passe RoomifyEnsa.

Voici votre nouveau mot de passe : %s

Par mesure de sécurité, nous vous recommandons de le modifier après votre prochaine connexion.

Cordialement,
L'équipe RoomifyEnsa
""".formatted(password);

        emailService.envoyerEmail(email, sujet, message);
        userAppRepo.save(user);

    }
    public String genererMotDePasseAlphanumerique() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder motDePasse = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            motDePasse.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }

        return motDePasse.toString();
    }

}
