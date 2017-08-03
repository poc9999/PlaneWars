package com.langxikeji.PlaneWars;


public class Gift extends Flyer{

	public Gift(int x, int y) {
		super(x, y, 30, 30, ImageRead.gift);
		// TODO Auto-generated constructor stub
	}

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
