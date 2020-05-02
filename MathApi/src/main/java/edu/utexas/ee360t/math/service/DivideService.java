package edu.utexas.ee360t.math.service;

import org.springframework.stereotype.Service;

@Service
public class DivideService {
	
	public int divide(byte x, byte y) {
		return x / y;
	}
	
	public int divide(short x, short y) {
		return x / y;
	}
	
	public long divide(int x, int y) {
		return x / y;
	}
	
	public long divide(long x, long y) {
		return x / y;
	}
	
	public double divide(float x, float y) {
		return x / y;
	}
	
	public double divide(double x, double y) {
		return x / y;
	}

}
