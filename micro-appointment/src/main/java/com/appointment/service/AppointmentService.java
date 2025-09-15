package com.appointment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.entity.Appointment;
import com.appointment.repo.AppointmentRepo;
import com.appointment.response.AppointmentResponse;
import com.appointment.response.DoctorResponse;
import com.appointment.response.PatientResponse;


@Service
public class AppointmentService {
	
	
	@Autowired
	AppointmentRepo appointmentRepo;
	
	@Autowired
	CommonService commonService;
	
	public List<AppointmentResponse> getDoctorApposByDid(long did) {
		
		List<Appointment> appointment = appointmentRepo.getByDid(did);
		List<AppointmentResponse> appointmentResponse= new ArrayList<AppointmentResponse>();	
		
		for(Appointment appo:appointment) {
			
			PatientResponse patientResponse =commonService.getPatientById(appo.getPid());
			
			AppointmentResponse appoRes = new AppointmentResponse(appo);
			appoRes.setPatientResponse(patientResponse);
			appointmentResponse.add(appoRes);
		}
		return  appointmentResponse;
		
	}

public List<AppointmentResponse> getPatientApposByPid(long pid) {
		
	List<Appointment> appointment = appointmentRepo.getByPid(pid);
	List<AppointmentResponse> appointmentResponse= new ArrayList<AppointmentResponse>();	
	
	for(Appointment appo:appointment) {
		
		AppointmentResponse appoRes = new AppointmentResponse(appo);
		DoctorResponse doctorResponse = commonService.getDoctorById(appo.getDid());
		appoRes.setDoctorResponse(doctorResponse);
		appointmentResponse.add(appoRes);
	}
	return  appointmentResponse;
		
	}

public AppointmentResponse getAppointmentById(long id) {
	 
			 Appointment appointment= appointmentRepo.findById(id).get();
			 DoctorResponse doctorResponse = commonService.getDoctorById(appointment.getDid());
			 AppointmentResponse appointmentResponse = new AppointmentResponse(appointment);
			 appointmentResponse.setDoctorResponse(doctorResponse);
			 return appointmentResponse;
}

public AppointmentResponse CreateAppointment(Appointment appointment) {
	appointment = appointmentRepo.save(appointment);
	return new AppointmentResponse(appointment);
}
	public boolean DeleteAppointment(long aid) {
		Appointment appo= appointmentRepo.findById(aid).get();
		 appointmentRepo.delete(appo);
		 if (appointmentRepo.existsById(aid))
			 return false;
		 return true;
	}

}
