package edu.utexas.ee360t.math.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.utexas.ee360t.math.service.SubtractService;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/math/subtract")
public class SubtractController {
	
	private SubtractService service;
	
	public SubtractController(SubtractService service) {
		this.service = service;
	}
	
	@GetMapping("/byte")
	public ResponseEntity<Integer> sub(@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) @RequestParam byte x,
									@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) @RequestParam byte y) {
		return ResponseEntity.ok(service.subtract(x, y));
	}
	
	@GetMapping("/short")
	public ResponseEntity<Integer> sub(@Min(Short.MIN_VALUE) @Max(Short.MAX_VALUE) @RequestParam short x,
									@Min(Short.MIN_VALUE) @Max(Short.MAX_VALUE) @RequestParam short y) {
		return ResponseEntity.ok(service.subtract(x, y));
	}
	
	@GetMapping("/int")
	public ResponseEntity<Long> subInts(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam int x,
										 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam int y) {
		return ResponseEntity.ok(service.subtract(x, y));
	}
	
	@GetMapping("/long")
	public ResponseEntity<Long> subLongs(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam long x,
										 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam long y) {
		return ResponseEntity.ok(service.subtract(x, y));
	}
	
	@GetMapping("/float")
	public ResponseEntity<Double> subFloat(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double x,
										 	 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double y) {
		return ResponseEntity.ok(service.subtract(x, y));
	}
	
	@GetMapping("/double")
	public ResponseEntity<Double> subDoubles(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double x,
										 	 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double y) {
		return ResponseEntity.ok(service.subtract(x, y));
	}
	
	

}
