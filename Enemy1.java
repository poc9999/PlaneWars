package com.langxikeji.PlaneWars;

import java.util.Random;

public class Enemy1 extends Flyer {

	Random r = new Random();

	// 构造方法
	public Enemy1(int x, int y) {
		super(x, y, 40, 40, ImageRead.enemyA);
		// TODO Auto-generated constructor stub
	}

	/*
	 * A类敌机的移动方法，X,Y每次随机加上一个值
	 */
	@Override
	public void move(long time) {
		// TODO Auto-generated method stub
		x += r.nextInt(5);
		y += r.nextInt(5);
	}

	/*
	 * 
	 * 敌机子弹是随着敌机位置出现的，所以这里写一个敌机火力的方法
	 */
	public Enemybullet enemyfire() {

		Enemybullet e = new Enemybullet(this.x + WIDTH / 4, this.y + HEIGHT);
		return e;
	}

	// 判断x y方向上面是否越界
	@Override
	public boolean OutOfBounds() {
		// TODO Auto-generated method stub
		boolean a = x > PlaneWars.WIDTH;
		boolean b = y > PlaneWars.HEIGHT;

		return a | b;
	}

}
