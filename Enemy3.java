package com.langxikeji.PlaneWars;

import java.util.Random;

public class Enemy3 extends Flyer {

	Random r = new Random();

	public Enemy3(int x, int y) {
		super(x, y, 30, 30, ImageRead.enemyC);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(long time) {
		// TODO Auto-generated method stub
		y += r.nextInt(5);
	}

	public Enemybullet enemyfire() {

		Enemybullet e = new Enemybullet(this.x + WIDTH / 4, this.y + HEIGHT);
		return e;
	}

	@Override
	public boolean OutOfBounds() {
		// TODO Auto-generated method stub

		return y > PlaneWars.HEIGHT;

	}

}
