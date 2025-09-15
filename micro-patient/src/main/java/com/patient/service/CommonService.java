package com.patient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.openfegin.DoctorOpenFegin;
import com.patient.openfegin.appointmentOpenFegin;
import com.patient.response.AppointmentResponse;
import com.patient.response.DoctorResponse;

@Service
public class CommonService {

	@Autowired
	appointmentOpenFegin appointmentFegin;
	
	@Autowired
	DoctorOpenFegin doctorFegin;
	
	
	public List<AppointmentResponse> PatientAppointment(long pid){
		List<AppointmentResponse> appointmentResponse= appointmentFegin.getAppsByPid(pid);
		
		return appointmentResponse;
		
	}
	
	public List<DoctorResponse> getAllDoctors(){
		List<DoctorResponse> doctors = doctorFegin.GetAllDoctors();
		return doctors;
	}
	
public DoctorResponse getDoctorByDID(long id) {
		return doctorFegin.getById(id);
	}

	public AppointmentResponse CreateAppointment(AppointmentResponse appointmentResponse) {
		return appointmentFegin.CreateAppointment(appointmentResponse);
	}
	
	public AppointmentResponse GetPaaointmentByAID(long aid) {
		return appointmentFegin.getAppById(aid);
	}
	
	public boolean DeteleAppointment(long aid) {
		return appointmentFegin.DeleteAppoointment(aid);
	}
	
	
}
