package org.jsp.dao;
import java.util.Optional;

import org.jsp.dto.User;
import org.jsp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepo repo;
	public void saveUser(User user){
		repo.save(user);
		
	}
	public Optional<User> findUserbyEmail(String email) {
		return repo.findByEmail(email);
	}
}
