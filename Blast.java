package com.langxikeji.PlaneWars;

/*
 *  Ӣ�ۻ��ӵ��͵л���ײ�ı�ը��
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
