package com.langxikeji.PlaneWars;
/*
 * Ӣ�ۻ�����
 */
public class Hero extends Flyer{


	public Hero(int x, int y) {
		super(x, y, 40, 40, ImageRead.hero);
		// TODO Auto-generated constructor stub
	}
	public void MoveUp() { // �Է��ɻ����Ƴ��磬ÿ���������λ
		if (y >= 10)
			y -= 10;
	}

	public void MoveDown() {
		if (y <= 606) // ��Ϸ�����ȥ�ɻ���79����С�ƶ�����
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

	//�ɻ��ӵ������λ���ɷɻ�λ��ȷ��
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
