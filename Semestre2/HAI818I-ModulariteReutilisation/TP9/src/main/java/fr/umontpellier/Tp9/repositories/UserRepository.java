package fr.umontpellier.Tp9.repositories;

import fr.umontpellier.Tp9.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>
{
    public abstract boolean existsUserByUsername(String username);
    public abstract User findByUsername(String username);
}
