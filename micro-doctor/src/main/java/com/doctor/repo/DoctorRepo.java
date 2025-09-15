package com.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doctor.entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

	
public	Doctor findByEmail(String email);



}
