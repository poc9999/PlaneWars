package com.langxikeji.PlaneWars;


/*
 * 英雄机子弹的类
 */
public class Herobullet extends Flyer {

	public Herobullet(int x, int y) {
		super(x, y, 40,40, ImageRead.herobullet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(long time) {
		// TODO Auto-generated method stub
		if(time%20==0){
			y-=10;
		}
	}

	@Override
	public boolean OutOfBounds() {
		// TODO Auto-generated method stub
		return y<-20;
	}

	

}
