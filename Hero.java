package com.langxikeji.PlaneWars;
/*
 * 英雄机的类
 */
public class Hero extends Flyer{


	public Hero(int x, int y) {
		super(x, y, 40, 40, ImageRead.hero);
		// TODO Auto-generated constructor stub
	}
	public void MoveUp() { // 以防飞机上移出界，每次移五个单位
		if (y >= 10)
			y -= 10;
	}

	public void MoveDown() {
		if (y <= 606) // 游戏界面减去飞机高79和最小移动步长
			y += 10;
	}

	public void MoveLeft() {
		if (x >= 10)
			x -= 10;
	}

	public void MoveRight() {
		if (x <= 431)
			x += 10;
	}

	//飞机子弹发射的位置由飞机位置确定
	public Herobullet Herofire(){
		Herobullet bullet=new Herobullet(this.x+WIDTH/4,this.y);
		return bullet;
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
