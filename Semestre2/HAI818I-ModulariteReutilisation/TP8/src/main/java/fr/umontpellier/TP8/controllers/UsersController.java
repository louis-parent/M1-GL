package fr.umontpellier.TP8.controllers;

import fr.umontpellier.TP8.models.User;
import fr.umontpellier.TP8.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
public class UsersController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> list()
    {
        return userRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Long id)
    {
        if(userRepository.findById(id).isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else
        {
            return userRepository.getById(id);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user)
    {
        return  userRepository.saveAndFlush(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Long id , @RequestBody User user)
    {
        User existingUser = userRepository.getById(id);
        BeanUtils.copyProperties(user, existingUser, "user_id");
        return userRepository.saveAndFlush(existingUser);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable Long id)
    {
        userRepository.deleteById(id);
    }
}
