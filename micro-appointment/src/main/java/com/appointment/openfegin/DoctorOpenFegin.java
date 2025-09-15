package com.appointment.openfegin;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.appointment.response.DoctorResponse;





@FeignClient(value="api-gateway")
public interface DoctorOpenFegin{

	@GetMapping("micro-doctor/doctor/getById/{did}")
	public  DoctorResponse getById(@PathVariable long did);
	
	
	
}
