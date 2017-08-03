package com.langxikeji.PlaneWars;

import java.util.Random;

public class Enemy2 extends Flyer {

	Random r = new Random();

	public Enemy2(int x, int y) {
		super(x, y, 35, 35, ImageRead.enemyB);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(long time) {
		// TODO Auto-generated method stub

		x -= r.nextInt(5);
		y += r.nextInt(5);
	}

	public Enemybullet enemyfire() {

		Enemybullet e = new Enemybullet(this.x + WIDTH / 4, this.y + HEIGHT);
		return e;
	}

	@Override
	public boolean OutOfBounds() {
		// TODO Auto-generated method stub
		boolean a = x < -20;
		boolean b = y > PlaneWars.HEIGHT;

		return a | b;
	}

}
