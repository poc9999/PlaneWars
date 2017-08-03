package com.langxikeji.PlaneWars;

/*
 *  英雄机子弹和敌机相撞的爆炸类
 */
public class Blast extends Flyer{

	public Blast(int x, int y) {
		super(x-20, y-20,50,50,ImageRead.blast );
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(long time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean OutOfBounds() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
