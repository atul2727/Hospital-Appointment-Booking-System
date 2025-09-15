package com.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.openfegin.DoctorOpenFegin;
import com.appointment.openfegin.PatientOpenFegin;
import com.appointment.response.DoctorResponse;
import com.appointment.response.PatientResponse;

@Service
public class CommonService {

	@Autowired
	DoctorOpenFegin doctorFegin;
	
	@Autowired
	PatientOpenFegin patientFegin;
	
	public DoctorResponse getDoctorById(long did) {
		DoctorResponse doctorResponse =  doctorFegin.getById(did);
		return doctorResponse;
	}
	
	public PatientResponse getPatientById(long pid) {
		PatientResponse patientResponse = patientFegin.getById(pid);
		return patientResponse;
	}
	
}
