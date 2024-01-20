package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends ResidentRepositoryCustom, JpaRepository<Resident, Integer> {

}
