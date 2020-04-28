package edu.utexas.ee360t.math.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.utexas.ee360t.math.service.DivideService;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/math/divide")
public class DivideController {
	
	private DivideService service;
	
	public DivideController(DivideService service) {
		this.service = service;
	}
	
	@GetMapping("/byte")
	public ResponseEntity<Integer> divide(@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) @RequestParam byte x,
										 @Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) @RequestParam byte y) {
		return ResponseEntity.ok(service.divide(x, y));
	}
	
	@GetMapping("/short")
	public ResponseEntity<Integer> divide(@Min(Short.MIN_VALUE) @Max(Short.MAX_VALUE) @RequestParam short x,
										 @Min(Short.MIN_VALUE) @Max(Short.MAX_VALUE) @RequestParam short y) {
		return ResponseEntity.ok(service.divide(x, y));
	}
	
	@GetMapping("/int")
	public ResponseEntity<Long> divInts(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam int x,
										 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam int y) {
		return ResponseEntity.ok(service.divide(x, y));
	}
	
	@GetMapping("/long")
	public ResponseEntity<Long> divLongs(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam long x,
										 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam long y) {
		return ResponseEntity.ok(service.divide(x, y));
	}
	
	@GetMapping("/float")
	public ResponseEntity<Double> divideFloat(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double x,
										 	 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double y) {
		return ResponseEntity.ok(service.divide(x, y));
	}
	
	@GetMapping("/double")
	public ResponseEntity<Double> divideDoubles(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double x,
										 	 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double y) {
		return ResponseEntity.ok(service.divide(x, y));
	}
}