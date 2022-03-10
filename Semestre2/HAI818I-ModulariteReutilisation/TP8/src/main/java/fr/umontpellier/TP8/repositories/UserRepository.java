package fr.umontpellier.TP8.repositories;

import fr.umontpellier.TP8.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
}
