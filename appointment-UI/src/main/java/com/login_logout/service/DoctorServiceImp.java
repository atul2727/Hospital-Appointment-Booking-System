package com.login_logout.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.login_logout.openfegin.DoctorOpenFegin;
import com.login_logout.repo.DoctorRepo;
import com.login_logout.response.AppointmentResponse;
import com.login_logout.response.DoctorResponse;

@Service
public class DoctorServiceImp implements DoctorService{

	@Autowired
	DoctorRepo doctorRepo;
	
	@Autowired
	DoctorOpenFegin doctorfegin;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	public DoctorResponse createUser(DoctorResponse doctor) {
		doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
		doctor.setRole("ROLE_DOCTOR");
		return doctorfegin.CreateDoctor(doctor);
	}

	@Override
	public boolean checkEmail(String email) {
		return doctorRepo.existsByEmail(email);
		}

	@Override
	public DoctorResponse getByEmail(String email) {
		
		return doctorfegin.getByEmail(email);
	}

	@Override
	public List<AppointmentResponse> getAllAppointmentByDID(long did) {
		
		return doctorfegin.GetApposById(did);
	}

	@Override
	public DoctorResponse getDoctorByDID(long did) {
		// TODO Auto-generated method stub
		return doctorfegin.getById(did);
	}

	@Override
	public DoctorResponse UpdateDoctor(DoctorResponse doctor) {
		// TODO Auto-generated method stub
		return doctorfegin.UpdateDoctor(doctor, doctor.getDid());
	}

	@Override
	public boolean UpdateAppoStatus(long aid, int st) {
		// TODO Auto-generated method stub
		doctorfegin.ChangeStatus(aid, st);
		return true;
	}

	

}
