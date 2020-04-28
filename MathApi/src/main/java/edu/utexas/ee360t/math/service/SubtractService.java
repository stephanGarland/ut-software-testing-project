package edu.utexas.ee360t.math.service;

import org.springframework.stereotype.Service;

@Service
public class SubtractService {
	
	public int subtract(byte x, byte y) {
		return x - y;
	}
	
	public int subtract(short x, short y) {
		return x - y;
	}
	
	public long subtract(int x, int y) {
		return x - y;
	}
	
	public long subtract(long x, long y) {
		return x - y;
	}
	
	public double subtract(float x, float y) {
		return x - y;
	}
	
	public double subtract(double x, double y) {
		return x - y;
	}

}
