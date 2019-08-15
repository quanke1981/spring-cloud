package com.example.myproject.repository;

import java.util.Optional;
import com.example.myproject.model.User;

public interface UserRepository extends BaseRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
}
