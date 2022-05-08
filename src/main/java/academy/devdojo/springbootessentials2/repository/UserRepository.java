package academy.devdojo.springbootessentials2.repository;

import academy.devdojo.springbootessentials2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername (String username);

}
