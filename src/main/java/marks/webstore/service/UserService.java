package marks.webstore.service;

import marks.webstore.domain.Role;
import marks.webstore.domain.User;
import marks.webstore.repos.UserRepo;
import marks.webstore.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    public User findUserByUsername(User user) {
        return userRepo.findByUsername(user.getUsername());
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                            "Dear, %s! \n" + "\n" +
                            "Welcome to WebStore. Please, visit the next link to confirm the registration: \n" +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.sendMessage(user.getEmail(), "Activation code", message);
        }

        return true;
    }

    /**
     * Function that checks if the email is already taken
     * @param user Our new user
     * @return true if email is free, false if the email is taken
     */
    public boolean checkForEmail(User user) {
        User userFromDb = userRepo.findByEmail(user.getEmail());

        return userFromDb == null;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActive(true);
        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }
}