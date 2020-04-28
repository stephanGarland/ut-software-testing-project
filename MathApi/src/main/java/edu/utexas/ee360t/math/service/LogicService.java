package edu.utexas.ee360t.math.service;

import org.springframework.stereotype.Service;

@Service
public class LogicService {
	
	public boolean and(boolean a, boolean b) {
		return a && b;
	}
	
	public boolean and(boolean a, boolean b, boolean c) {
		return a && b && c;
	}
	
	public boolean or(boolean a, boolean b) {
		return a || b;
	}

}
