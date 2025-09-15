package com.login_logout.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.login_logout.response.AppointmentResponse;
import com.login_logout.response.DoctorResponse;

import com.login_logout.service.DoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@ModelAttribute
	private void doctorDetails(Model m, Principal p) {
		String email = p.getName();


		DoctorResponse doctorResponse= doctorService.getByEmail(email);		
		m.addAttribute("doctor", doctorResponse);

	}
	@GetMapping("/")
	public String home() {
		
		return "doctor_view/home";	}
	
	@GetMapping("/Apps/{did}")
	public String getAllAppoinmentByDID(@PathVariable long did, Model model){
		List<AppointmentResponse> appos = doctorService.getAllAppointmentByDID(did);
	
		model.addAttribute("appos", appos);

		return "doctor_view/appointments";
	}
	@GetMapping("/edit")
	public String EditProfile()
	{
		return "doctor_view/profile";
	}
	@PostMapping("/update/{did}")
	public String updateDoctor(@ModelAttribute DoctorResponse doctor,@PathVariable long did, Model model)
	{
		DoctorResponse docExist= doctorService.getDoctorByDID(did);
		doctor.setPassword(docExist.getPassword());
		DoctorResponse doctorRes= doctorService.UpdateDoctor(doctor);
		model.addAttribute("doctor", doctorRes);
		
		return "doctor_view/home";
	}
	
	@GetMapping("/status/{aid}/{st}")
	public String ChangeStatusAppo(@PathVariable long aid, @PathVariable int st) {
		doctorService.UpdateAppoStatus(aid, st);
		return "doctor_view/home";
	}

}
