package com.deva.chicago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsServiceController {

	@Autowired
	Limits limits;
		
	@GetMapping("/limits")
	public Limits getLimits() {
		
		return new Limits(limits.getMin(), limits.getMax());
	}
	
}
