package com.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctor.openfeign.AppointmentOpenFegin;
import com.doctor.response.AppointmentResponse;


@Service
public class CommonService {

	@Autowired
	AppointmentOpenFegin appointmentFegin;
	
	public List<AppointmentResponse> DoctorAppointment(long did){
		List<AppointmentResponse> appointmentResponse= appointmentFegin.getAppsByDid(did);
		return appointmentResponse;
			}
	
public boolean ChangeStatus(long aid,int st) {
		appointmentFegin.ChangeStatus(aid, st);
		return true;
	}
}
