package com.ines.restaurants.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ines.restaurants.entities.Role;
import com.ines.restaurants.entities.User;
import com.ines.restaurants.entities.VerificationToken;
import com.ines.restaurants.repos.RoleRepository;
import com.ines.restaurants.repos.UserRepository;
import com.ines.restaurants.repos.VerificationTokenRepository;
import com.ines.restaurants.util.EmailSender;
import com.ines.restaurants.exceptions.EmailAlreadyExistsException;
import com.ines.restaurants.exceptions.InvalidTokenException;
import com.ines.restaurants.exceptions.ExpiredTokenException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRep;

    @Autowired
    RoleRepository roleRep;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    VerificationTokenRepository verificationTokenRepo;

    @Autowired
    EmailSender emailSender;

    @Override
    public User registerUser(com.ines.restaurants.entities.RegistationRequest request) {
        Optional<User> optionaluser = userRep.findByEmail(request.getEmail());
        if (optionaluser.isPresent())
            throw new EmailAlreadyExistsException("email déjà existant!");

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        newUser.setEnabled(false);

        // Ajouter le rôle par défaut USER
        Role r = roleRep.findByRole("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(r);
        newUser.setRoles(roles);

        userRep.save(newUser);

        // Générer le code secret (6 chiffres)
        String code = this.generateCode();

        // Enregistrer le token
        VerificationToken token = new VerificationToken(code, newUser);
        verificationTokenRepo.save(token);

        // Envoyer l'email
        sendEmailUser(newUser, token.getToken());

        return newUser;
    }

    public void sendEmailUser(User u, String code) {
        String emailBody = "Bonjour <h1>" + u.getUsername() + "</h1>" +
                " Votre code de validation est <h1>" + code + "</h1>";
        emailSender.sendEmail(u.getEmail(), emailBody);
    }

    public String generateCode() {
        Random random = new Random();
        Integer code = 100000 + random.nextInt(900000);
        return code.toString();
    }

    @Override
    public User validateToken(String code) {
        VerificationToken token = verificationTokenRepo.findByToken(code);
        if (token == null) {
            throw new InvalidTokenException("Invalid Token");
        }

        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            verificationTokenRepo.delete(token);
            throw new ExpiredTokenException("expired Token");
        }
        user.setEnabled(true);
        userRep.save(user);
        return user;
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
        User usr = userRep.findByUsername(username);
        Role r = roleRep.findByRole(rolename);
        if (usr != null && r != null) {
            usr.getRoles().add(r);
        }
        return usr;
    }

    @Override
    public void deleteAllusers() {
        userRep.deleteAll();
    }

    @Override
    public void deleteAllRoles() {
        roleRep.deleteAll();
    }

    @Override
    public Role addRole(Role role) {
        return roleRep.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRep.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRep.findAll();
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRep.save(user);
    }
}