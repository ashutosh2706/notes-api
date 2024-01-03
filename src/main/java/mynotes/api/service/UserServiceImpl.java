package mynotes.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mynotes.api.entity.User;
import mynotes.api.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserByUserName(String username) {
		return userRepository.getUserByUserName(username);
	}
}
