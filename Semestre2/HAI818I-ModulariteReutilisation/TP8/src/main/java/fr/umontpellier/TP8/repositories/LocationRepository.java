package fr.umontpellier.TP8.repositories;

import fr.umontpellier.TP8.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long>
{
}
