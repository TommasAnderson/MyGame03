package cn.edu.neaugame;

import java.awt.Color;
import java.awt.Graphics;

public class Shell extends GameObject {

	double degree;

	public Shell() {

		x = 200;
		y = 200;
		width = 10;
		height = 10;
		speed = 5;
		degree = Math.random() * Math.PI * 2;

	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);

		g.fillOval((int) x, (int) y, width, height);

		// 炮弹沿着任意角度去飞
		x += speed * Math.cos(degree);
		y += speed * Math.sin(degree);

		if (x < Constant.GameFrame_SIDE || x > Constant.GameFrame_WIDTH-width-Constant.GameFrame_SIDE) {

			degree = Math.PI-degree;
			
		}

		if (y<Constant.GameFrame_TOP||y>Constant.GameFrame_HEIGHT-height-Constant.GameFrame_SIDE){
			degree = -degree;
		}

		g.setColor(c);

	}

}
