package com.langxikeji.PlaneWars;

public class Enemybullet extends Flyer{

	public Enemybullet(int x, int y) {
		super(x, y, 20, 26, ImageRead.enemybullet);
		// TODO Auto-generated constructor stub
	}
	//敌机子弹的飞行方法
	@Override
	public void move(long time) {
		// TODO Auto-generated method stub
		if(time%20==0){
			y+=10;
		}
	}
	@Override
	public boolean OutOfBounds() {
		// TODO Auto-generated method stub
		return y>PlaneWars.HEIGHT;
	}
}
