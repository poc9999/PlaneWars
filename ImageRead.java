package com.langxikeji.PlaneWars;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

/*
 * ������Դ������
 */
public class ImageRead {
	public static BufferedImage enemyA;// �л�һ
	public static BufferedImage enemyB;// �л���
	public static BufferedImage enemyC;// �л���
	public static BufferedImage hero;// Ӣ�ۻ�
	public static BufferedImage herobullet;// Ӣ�ۻ��ӵ�
	public static BufferedImage enemybullet;// �л��ӵ�
	public static BufferedImage gift;// ����
	public static BufferedImage blast;// ��ը
	public static BufferedImage map1;//��ͼ
	public static BufferedImage b1;
	public static BufferedImage b2;
	public static BufferedImage b3;
	public static BufferedImage b4;
	public static BufferedImage icon;//ͼ��

	/*
	 * ��Դ����ʹ�þ�̬��ʼ����
	 * ��Ϊֻ��Ҫ������ʱ��ʹ��һ��
	 */
	static {
		try {
			enemyA = ImageIO.read(new FileInputStream("./�ز�/Boss/BOSS1.png"));
			enemyB = ImageIO.read(new FileInputStream("./�ز�/Boss/BOSS2.png"));
			enemyC = ImageIO.read(new FileInputStream("./�ز�/Boss/BOSS3.png"));
			hero = ImageIO.read(new FileInputStream("./�ز�/Hero1/hero.png"));
			herobullet = ImageIO.read(new FileInputStream("./�ز�/Bullet/˫��1.png"));
			enemybullet = ImageIO.read(new FileInputStream("./�ز�/Bullet/����1.png"));
			gift = ImageIO.read(new FileInputStream("./�ز�/Gift/gift.png"));
			blast = ImageIO.read(new FileInputStream("./�ز�/Blast/blast_2_2.png"));
			map1=ImageIO.read(new FileInputStream("./�ز�/Maps/map5.jpg"));
			icon=ImageIO.read(new FileInputStream("./�ز�/Icon/ͼ��.jpg"));
			b1=ImageIO.read(new FileInputStream("./�ز�/Maps/map4.jpg"));
			b2=ImageIO.read(new FileInputStream("./�ز�/Maps/b2.jpg"));
			b3=ImageIO.read(new FileInputStream("./�ز�/Maps/b3.png"));
			b4=ImageIO.read(new FileInputStream("./�ز�/Maps/b4.png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
