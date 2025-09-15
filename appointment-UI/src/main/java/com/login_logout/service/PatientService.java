package com.login_logout.service;


import java.util.List;

import com.login_logout.response.AppointmentResponse;
import com.login_logout.response.DoctorResponse;
import com.login_logout.response.PatientResponse;


public interface PatientService {

	public PatientResponse createUser(PatientResponse patient);

	public boolean checkEmail(String email);
	public PatientResponse getByEmail(String email);
	public java.util.List<AppointmentResponse> getAllAppointmentsByPid(long pid);
	public List<DoctorResponse> getAllDoctors();
	public PatientResponse getById(long id);
	public PatientResponse updatePatient(PatientResponse patient,long id);
	public DoctorResponse getDoctorByDID(long did);
	public AppointmentResponse CreateAppointment(AppointmentResponse appointmentResponse);
	public AppointmentResponse getAppointmentByAID(long aid);
	public boolean DeleteAppointment(long aid);
	public boolean DeletePatient(long did);

	
}
