package com.doctor.openfeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.doctor.response.AppointmentResponse;




@FeignClient(value="api-gateway")
public interface AppointmentOpenFegin {



	@GetMapping("micro-appointment/appointment/getAppsByDid/{did}")
	public List<AppointmentResponse> getAppsByDid(@PathVariable long did);

	@GetMapping("micro-appointment/appointment/status/{aid}/{st}")
	public boolean ChangeStatus(@PathVariable long aid, @PathVariable int st);
}
