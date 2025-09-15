package com.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.Doctor;
import com.doctor.response.AppointmentResponse;
import com.doctor.response.DoctorResponse;
import com.doctor.service.CommonService;
import com.doctor.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	CommonService commonService;

	@GetMapping("/all")
	public List<DoctorResponse> GetAllDoctors()
	{
		return doctorService.getAllDoctors();
		
	}
	
	@GetMapping("/getById/{id}")
	public DoctorResponse getById(@PathVariable long id) {
		return doctorService.getById(id);
	}
	
	@GetMapping("/getByEmail/{email}")
	public DoctorResponse getByEmail(@PathVariable String email) {
		return doctorService.getByEmail(email);
	}
	
	@PostMapping("/register")
	public DoctorResponse CreateDoctor(@RequestBody Doctor doctor) {
		
		return doctorService.CreateDoctor(doctor);
		
	}
	
	@PutMapping("/update/{id}")
	public Doctor UpdateDoctor(@RequestBody Doctor doctor, @PathVariable long id) {
		doctor.setDid(id);
		
		return doctorService.UpdateDoctor(doctor);
		
	}
	@DeleteMapping("/delete/{id}")
	public String DeleteDoctor(@PathVariable long id) {
		return doctorService.DeleteDoctor(id);
		
	}
	
	@GetMapping("/appos/{id}")
	public List<AppointmentResponse> GetApposById(@PathVariable long id) {
		return commonService.DoctorAppointment(id) ;
		
	}
	
	@GetMapping("/status/{aid}/{st}")
	public boolean ChangeStatus(@PathVariable long aid, @PathVariable int st) {
		commonService.ChangeStatus(aid, st);
		return true;
	}
	
	
}
