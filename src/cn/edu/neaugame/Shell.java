package cn.edu.neaugame;

import javax.security.auth.x500.X500Principal;

public class Shell extends GameObject{
	
	double degree;
	
	public Shell() {
		
		x = 200;
		y = 200;
		width = 10;
		height = 10;
		speed = 3;
		degree = Math.random()*Math.PI*2;
	}
	
	

}
