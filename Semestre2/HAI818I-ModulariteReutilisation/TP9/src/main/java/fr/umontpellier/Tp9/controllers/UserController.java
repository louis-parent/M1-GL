package fr.umontpellier.Tp9.controllers;

import fr.umontpellier.Tp9.models.User;
import fr.umontpellier.Tp9.models.VerificationToken;
import fr.umontpellier.Tp9.repositories.UserRepository;
import fr.umontpellier.Tp9.repositories.VerificationTokenRepository;
import fr.umontpellier.Tp9.util.OnCreateUserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;

@Controller
public class UserController
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/doRegister")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result)
    {
        // check for errors ...
        // verify that username does not exist:
        if(userRepository.existsUserByUsername(user.getUsername()))
        {
            return "register.jsp?user=true";
        }
        else
        {	// encrypt password:
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // save user object:
            userRepository.saveAndFlush(user);
            eventPublisher.publishEvent(new OnCreateUserEvent("/", user));

            // create/save an Authority obj ...
            return "login";
        }
    }

    @GetMapping({"/userConfirm"})
    public String confirmUser(@RequestParam("token") String token)
    {
        VerificationToken verifToken = verificationTokenRepository.getOne(token);

        if (verifToken != null)
        {
            Date date = Calendar.getInstance().getTime();
            if (date.before(verifToken.getExpiryDate()))
            {
                verificationTokenRepository.delete(verifToken);
                User user = userRepository.findByUsername(verifToken.getUsername());
                user.setEnabled(true);
                userRepository.saveAndFlush(user);
                return "login.jsp?confirm=true";
            }
            else {
                verificationTokenRepository.delete(verifToken);
                return "register.jsp?expired=true";
            }
        }
        else { return "register.jsp?confirm=false"; }
    }
}