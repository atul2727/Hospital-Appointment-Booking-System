package com.appointment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.entity.Appointment;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long>{

	List<Appointment> getByDid(long did);

	List<Appointment> getByPid(long pid);

}
