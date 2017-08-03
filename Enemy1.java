package com.langxikeji.PlaneWars;

import java.util.Random;

public class Enemy1 extends Flyer {

	Random r = new Random();

	// ���췽��
	public Enemy1(int x, int y) {
		super(x, y, 40, 40, ImageRead.enemyA);
		// TODO Auto-generated constructor stub
	}

	/*
	 * A��л����ƶ�������X,Yÿ���������һ��ֵ
	 */
	@Override
	public void move(long time) {
		// TODO Auto-generated method stub
		x += r.nextInt(5);
		y += r.nextInt(5);
	}

	/*
	 * 
	 * �л��ӵ������ŵл�λ�ó��ֵģ���������дһ���л������ķ���
	 */
	public Enemybullet enemyfire() {

		Enemybullet e = new Enemybullet(this.x + WIDTH / 4, this.y + HEIGHT);
		return e;
	}

	// �ж�x y���������Ƿ�Խ��
	@Override
	public boolean OutOfBounds() {
		// TODO Auto-generated method stub
		boolean a = x > PlaneWars.WIDTH;
		boolean b = y > PlaneWars.HEIGHT;

		return a | b;
	}

}
