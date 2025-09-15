package com.login_logout.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.login_logout.entity.UserDtls;
import com.login_logout.response.DoctorResponse;
import com.login_logout.response.PatientResponse;
import com.login_logout.service.DoctorService;
import com.login_logout.service.PatientService;
import com.login_logout.service.UserService;


@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	DoctorService doctorService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/registerDoctor")
	public String DoctorRegister() {
		return "doctorRegister";
	}

	
	@PostMapping("/createPatient")
	public String createuser(@ModelAttribute PatientResponse patient, HttpSession session) {

	
		UserDtls user = new UserDtls();
		user.setEmail(patient.getEmail());
		user.setPassword(patient.getPassword());
		user.setRole("ROLE_PATIENT");
		System.out.println(user.toString());
		

		boolean f = userService.checkEmail(user.getEmail());

		if (f) {
			session.setAttribute("msg", "Email Id alreday exists");
		}

		else {
			PatientResponse patientDtls=patientService.createUser(patient);
			UserDtls userDtls = userService.createUser(user);
			if (userDtls != null && patientDtls !=null) {
				session.setAttribute("msg", "Register Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}

		return "redirect:/register";
	}
	
	

	@PostMapping("/createDoctor")
	public String createDoctor(@ModelAttribute DoctorResponse doctor, HttpSession session) {


		UserDtls user = new UserDtls();
		user.setEmail(doctor.getEmail());
		user.setPassword(doctor.getPassword());
		user.setRole("ROLE_DOCTOR");
		

		boolean f = userService.checkEmail(user.getEmail());

		if (f) {
			session.setAttribute("msg", "Email Id alreday exists");
		}

		else {
			
			DoctorResponse doctordtls=doctorService.createUser(doctor);
		
			UserDtls userDtls = userService.createUser(user);
			if (userDtls != null && doctordtls !=null) {
				session.setAttribute("msg", "Doctor Register Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}

		return "redirect:/registerDoctor";
	}

}