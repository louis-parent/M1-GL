package fr.umontpellier.Tp9.repositories;

import fr.umontpellier.Tp9.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String>
{
}
