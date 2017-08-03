package com.langxikeji.PlaneWars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 * 
 * ��װ���з����������Ժͷ���
 * @author poc999
 *
 */
public abstract class Flyer {
	/*
	 * ������������
	 */
	 public int x;
	 public int y;
	/*
	 * �������Ĵ�С
	 */
	public int WIDTH;
	public int HEIGHT;
	/*
	 * ��������ͼƬ
	 */
	 BufferedImage image;
	//��ʼ������������
	 public Flyer(int x,int y,int WIDTH,int HEIGHT,BufferedImage image){
		 this.x=x;
		 this.y=y;
		 this.WIDTH=WIDTH;
		 this.HEIGHT=HEIGHT;
		 this.image=image;
	 }
	//ÿ������������Ҫ�Լ��ƶ���������Ҫ�����Լ�ȥʵ���ƶ��ķ���
	public abstract void move(long time);
	//�ж������������Ƿ���ײ
	public static boolean boom(Flyer f1,Flyer f2){  
        //step1: ����������ε����ĵ�  
        int f1x = f1.x + f1.WIDTH/2;  
        int f1y = f1.y + f1.HEIGHT/2;  
        int f2x = f2.x + f2.WIDTH/2;  
        int f2y = f2.y + f2.HEIGHT/2;  
        //step2: �����������ײ���  
        boolean H = Math.abs(f1x - f2x) < (f1.WIDTH + f2.WIDTH)/2;  
        boolean V = Math.abs(f1y -f2y) < (f1.HEIGHT + f2.HEIGHT)/2;  
        //step3: ������������ͬʱ��ײ  
        return H&V;  
    }
	//���Ƴ�ÿ��������
	public void draw(Graphics g)
	{
		g.drawImage(image, x, y, WIDTH, HEIGHT, null);
	}
	
	//Խ����
	public abstract boolean OutOfBounds();
}
