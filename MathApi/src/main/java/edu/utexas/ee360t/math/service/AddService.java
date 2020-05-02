package edu.utexas.ee360t.math.service;

import org.springframework.stereotype.Service;

@Service
public class AddService {
	
	public int add(byte x, byte y) {
		return x + y;
	}
	
	public int add(short x, short y) {
		return x + y;
	}
	
	public long add(int x, int y) {
		return x + y;
	}
	
	public long add(long x, long y) {
		return x + y;
	}
	
	public double add(float x, float y) {
		return x + y;
	}
	
	public double add(double x, double y) {
		return x + y;
	}

}
