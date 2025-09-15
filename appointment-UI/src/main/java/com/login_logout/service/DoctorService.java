package com.login_logout.service;


import java.util.List;

import com.login_logout.response.AppointmentResponse;
import com.login_logout.response.DoctorResponse;


public interface DoctorService {

	public DoctorResponse createUser(DoctorResponse doctor);

	public boolean checkEmail(String email);
	public DoctorResponse getByEmail(String email);
	public List<AppointmentResponse> getAllAppointmentByDID(long did);
	
	public DoctorResponse getDoctorByDID(long did);
	public DoctorResponse UpdateDoctor(DoctorResponse doctor);
	public boolean UpdateAppoStatus(long aid,int st);
	
}
