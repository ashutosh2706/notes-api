package mynotes.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mynotes.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User getUserByUserName(String userName);
}
