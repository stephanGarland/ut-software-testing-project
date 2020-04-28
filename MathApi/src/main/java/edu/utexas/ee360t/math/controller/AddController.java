package edu.utexas.ee360t.math.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.utexas.ee360t.math.service.AddService;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/math/add")
public class AddController {
	
	private AddService service;
	
	public AddController(AddService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/byte")
	public ResponseEntity<Integer> add(@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) @RequestParam byte x, 
									@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) @RequestParam byte y) {
		return ResponseEntity.ok(service.add(x, y));
	}
	
	@GetMapping(value = "/short")
	public ResponseEntity<Integer> add(@Min(Short.MIN_VALUE) @Max(Short.MAX_VALUE) @RequestParam short x, 
									@Min(Short.MIN_VALUE) @Max(Short.MAX_VALUE) @RequestParam short y) {
		return ResponseEntity.ok(service.add(x, y));
	}
	
	@GetMapping(value = "/int")
	public ResponseEntity<Long> addInts(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam int x, 
										 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam int y) {
		return ResponseEntity.ok(service.add(x, y));
	}
	
	@GetMapping(value = "/long")
	public ResponseEntity<Long> addLongs(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam long x, 
										 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam long y) {
		return ResponseEntity.ok(service.add(x, y));
	}
	
	@GetMapping(value = "/float")
	public ResponseEntity<Double> addFloat(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double x, 
										 	 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double y) {
		return ResponseEntity.ok(service.add(x, y));
	}
	
	@GetMapping(value = "/double")
	public ResponseEntity<Double> addDoubles(@Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double x, 
										 	 @Min(Integer.MIN_VALUE) @Max(Integer.MAX_VALUE) @RequestParam double y) {
		return ResponseEntity.ok(service.add(x, y));
	}
}