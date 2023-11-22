package Proyecto.GestorAlmuerzo.Repository;

import Proyecto.GestorAlmuerzo.model.Role;
import Proyecto.GestorAlmuerzo.model.Suscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuscriptionRepository extends JpaRepository<Suscription, String> {
}
