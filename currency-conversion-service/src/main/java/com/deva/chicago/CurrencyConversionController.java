package com.deva.chicago;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getCurrencyConvert(@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		Map<String, String>  uriVaribales = new HashMap<>();
		uriVaribales.put("from", from);
		uriVaribales.put("to", to);
		
		ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
						CurrencyConversion.class,
						uriVaribales );
		
		CurrencyConversion res = response.getBody();
		
		return new CurrencyConversion(res.getId(),from,to,res.getConversionMultiple(),
				quantity, quantity.multiply(res.getConversionMultiple()), res.getPort() );
	}
}
