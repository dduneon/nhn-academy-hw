package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.ResidentDTO;
import com.nhnacademy.springjpa.entity.Resident;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResidentRepository extends ResidentRepositoryCustom, JpaRepository<Resident, Integer> {

}
