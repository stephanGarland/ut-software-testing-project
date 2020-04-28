package edu.utexas.ee360t.math.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.utexas.ee360t.math.service.LogicService;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/logic")
public class LogicController {
	
	private LogicService service;
	
	public LogicController(LogicService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/and")
	public ResponseEntity<Boolean> and(@RequestParam boolean x, @RequestParam boolean y) {
		return ResponseEntity.ok(service.and(x, y));
	}
	
	@GetMapping(value = "/andMore")
	public ResponseEntity<Boolean> and(@RequestParam boolean x, @RequestParam boolean y, @RequestParam boolean z) {
		return ResponseEntity.ok(service.and(x, y, z));
	}
	
	@GetMapping(value = "/or")
	public ResponseEntity<Boolean> or(@RequestParam boolean x, @RequestParam boolean y) {
		return ResponseEntity.ok(service.or(x, y));
	}
}
