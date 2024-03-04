package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByIdAndPassword(String id, String password);
  Optional<User> findById(String id);
  boolean existsById(String id);
  void deleteById(String id);

  @Modifying
  @Query("update User u set u.latestLogin = current_timestamp where u.id = ?1")
  int updateLatestLoginById(String id);

  @Modifying
  @Query("update User u set u.point = ?2 where u.id = ?1")
  int updatePointById(String id, int point);

  @Modifying
  @Query("update User u set u.password = ?2 where u.id = ?1")
  int updatePasswordById(String id, String password);
}
