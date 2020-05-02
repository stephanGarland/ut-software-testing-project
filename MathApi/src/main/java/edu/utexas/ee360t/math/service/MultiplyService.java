package edu.utexas.ee360t.math.service;

import org.springframework.stereotype.Service;

@Service
public class MultiplyService {
	
	public long multiply(byte x, byte y) {
		return x * y;
	}
	
	public int multiply(short x, short y) {
		return x * y;
	}
	
	public long multiply(int x, int y) {
		return x * y;
	}
	
	public long multiply(long x, long y) {
		return x * y;
	}
	
	public double multiply(float x, float y) {
		return x * y;
	}
	
	public double multiply(double x, double y) {
		return x * y;
	}
}
