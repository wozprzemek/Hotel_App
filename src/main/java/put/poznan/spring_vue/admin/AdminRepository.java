package put.poznan.spring_vue.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import put.poznan.spring_vue.admin.Admin;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    List<Admin> findByLogin (String login);
}
