package put.poznan.spring_vue;

import org.springframework.data.repository.CrudRepository;
import put.poznan.spring_vue.entities.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {

}
