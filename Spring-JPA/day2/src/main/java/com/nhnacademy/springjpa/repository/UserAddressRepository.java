package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.UserAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
  void deleteById(Long id);
  List<UserAddress> findByUser_Id(String userId);
}
