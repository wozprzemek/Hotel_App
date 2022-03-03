package put.poznan.spring_vue.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import put.poznan.spring_vue.admin.Admin;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
