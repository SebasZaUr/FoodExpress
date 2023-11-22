package Proyecto.GestorAlmuerzo.Repository;

import Proyecto.GestorAlmuerzo.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay,String> {
}
