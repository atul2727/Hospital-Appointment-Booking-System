package com.patient.openfegin;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.patient.response.AppointmentResponse;




@FeignClient(value="api-gateway")
public interface appointmentOpenFegin {

	@GetMapping("micro-appointment/appointment/getAppsByPid/{pid}")
	public List<AppointmentResponse> getAppsByPid(@PathVariable long pid);
	

	@PostMapping("micro-appointment/appointment/create")
	public AppointmentResponse CreateAppointment(@RequestBody AppointmentResponse appointment);
	
	@GetMapping("micro-appointment/appointment/getAppByID/{id}")
	public AppointmentResponse getAppById(@PathVariable long id);
	
	@DeleteMapping("micro-appointment/appointment/detete/{aid}")
	public boolean DeleteAppoointment(@PathVariable long aid);
}
