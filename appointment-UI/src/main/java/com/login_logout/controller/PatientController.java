package com.login_logout.controller;


import java.security.Principal;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.login_logout.response.AppointmentResponse;
import com.login_logout.response.PatientResponse;
import com.login_logout.service.DoctorService;
import com.login_logout.service.PatientService;
import com.login_logout.service.UserService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	UserService userService;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	DoctorService doctorService;

	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"), true, 10));   
	}
	
	 
	@ModelAttribute
	private void patientDetails(Model m, Principal p) {
		String email = p.getName();
		PatientResponse patientRespose = patientService.getByEmail(email);

		m.addAttribute("patient", patientRespose);

	}
	
	@GetMapping("/")
	public String home() {
		return "patient_view/home";	}
	
	
	@GetMapping("/appointments/{pid}")
	public String listOfAppointments(@PathVariable long pid, Model model) {
		List<AppointmentResponse> appos =patientService.getAllAppointmentsByPid(pid);
		
	
		
		model.addAttribute("appos", appos);
		return "patient_view/appointments";
		
	}
	
	@GetMapping("/doctors")
	public String  getAllDoctors(Model model) {
		model.addAttribute("doctors", patientService.getAllDoctors());
		return "patient_view/AllDoctors";
	}
	
	@GetMapping("/profile/{id}")
	public String getById(@PathVariable long id, Model model) {
		model.addAttribute("patient", patientService.getById(id));
		return "patient_view/profile";
	}
	
	
	@PostMapping("/update/{id}")
	public String UpdatePatient(@ModelAttribute PatientResponse patient, @PathVariable long id,Model m, HttpSession session) {
		patient.setId(id);
//		System.out.println("Before Update");
//		System.out.println(patient.toString());
		PatientResponse patientResponse = patientService.updatePatient(patient, id);
//		System.out.println("After Update");
//		System.out.println(patientResponse.toString());
		m.addAttribute("patient", patientResponse);
		session.setAttribute("profile_updated", "Profile updated..");
		
		return "patient_view/home";
	}
	
	@GetMapping("/delet/{id}")
	public String DeletePatient(@PathVariable long id){
		if (patientService.DeletePatient(id)) {
			return "index";
		}
		return "patient_view/home";
		
	}
	
	@GetMapping("/book/{did}")
	public String GetAppointmentPage(@PathVariable long did, Model model) {
		model.addAttribute("doctor", patientService.getDoctorByDID(did));
		return "patient_view/bookAppointment";
	}
	
	@PostMapping("/book/{id}")
	public String CreateAppointment(@ModelAttribute("appointment") AppointmentResponse appointment, @PathVariable long id,HttpSession session) {
		appointment.setPid(id);
		appointment.setStatus("Pending");
		//System.out.println(appointment.toString());
		patientService.CreateAppointment(appointment);
		
				 session.setAttribute("booked","Appointment Booked Sucessfully." );
		return "redirect:/patient/appointments/"+appointment.getPid();
		
	}
	
	@GetMapping("/editAppo/{aid}")
	public String EditAppointment(@PathVariable long aid, Model model) {
		AppointmentResponse appointmentResponse = patientService.getAppointmentByAID(aid);
		model.addAttribute("appointment", appointmentResponse);
		//System.out.println(appointmentResponse.toString());
		
		return "patient_view/editAppointment";
	}
	
	@PostMapping("/updateApp/{id}/{did}")
	public String UpdateAppointment(@ModelAttribute("appointment") AppointmentResponse appointment, @PathVariable("id") long id,@PathVariable("did") long did,HttpSession session) {
		appointment.setPid(id);
		appointment.setDid(did);
		appointment.setStatus("Pending");
		//System.out.println(appointment.toString());
		//patientService.CreateAppointment(appointment);
		CreateAppointment(appointment, id, session);
		session.setAttribute("booked","Appointment Updated." );
		return "redirect:/patient/appointments/"+appointment.getPid();
		
	}
	
	@GetMapping("/deleteAppo/{aid}")
	public String DeleteAppointment(@PathVariable long aid, HttpSession session) {
		AppointmentResponse appointmentResponse = patientService.getAppointmentByAID(aid);
	patientService.DeleteAppointment(aid);
	session.setAttribute("deleted", "Appointment deleted");
		return  "redirect:/patient/appointments/"+appointmentResponse.getPid();
	}

}
