package mynotes.api.service;

import org.springframework.stereotype.Service;

import mynotes.api.entity.User;

@Service
public interface UserService {
	
	public User saveUser(User user);
	public User getUserByUserName(String username);
	
}
