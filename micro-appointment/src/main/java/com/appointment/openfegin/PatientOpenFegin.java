package com.appointment.openfegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.appointment.response.PatientResponse;


@FeignClient(value="micro-patient")
public interface PatientOpenFegin {

	@GetMapping("patient/getById/{pid}")
	public PatientResponse getById(@PathVariable long pid);
}
