package com.Sumerge.MovieApp.repository;
import com.Sumerge.MovieApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
}
