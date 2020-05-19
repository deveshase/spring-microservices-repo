package com.deva.chicago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getCurrencyExchange(@PathVariable String from,
			@PathVariable String to) {
		
		CurrencyExchange ce = repository.findByFromAndTo(from, to);
		
		ce.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return ce;
	}
}
