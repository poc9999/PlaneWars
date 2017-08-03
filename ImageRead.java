package com.langxikeji.PlaneWars;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

/*
 * 所有资源加载类
 */
public class ImageRead {
	public static BufferedImage enemyA;// 敌机一
	public static BufferedImage enemyB;// 敌机二
	public static BufferedImage enemyC;// 敌机三
	public static BufferedImage hero;// 英雄机
	public static BufferedImage herobullet;// 英雄机子弹
	public static BufferedImage enemybullet;// 敌机子弹
	public static BufferedImage gift;// 礼物
	public static BufferedImage blast;// 爆炸
	public static BufferedImage map1;//地图
	public static BufferedImage b1;
	public static BufferedImage b2;
	public static BufferedImage b3;
	public static BufferedImage b4;
	public static BufferedImage icon;//图标

	/*
	 * 资源加载使用静态初始化块
	 * 因为只需要启动的时候使用一次
	 */
	static {
		try {
			enemyA = ImageIO.read(new FileInputStream("./素材/Boss/BOSS1.png"));
			enemyB = ImageIO.read(new FileInputStream("./素材/Boss/BOSS2.png"));
			enemyC = ImageIO.read(new FileInputStream("./素材/Boss/BOSS3.png"));
			hero = ImageIO.read(new FileInputStream("./素材/Hero1/hero.png"));
			herobullet = ImageIO.read(new FileInputStream("./素材/Bullet/双弹1.png"));
			enemybullet = ImageIO.read(new FileInputStream("./素材/Bullet/蓝弹1.png"));
			gift = ImageIO.read(new FileInputStream("./素材/Gift/gift.png"));
			blast = ImageIO.read(new FileInputStream("./素材/Blast/blast_2_2.png"));
			map1=ImageIO.read(new FileInputStream("./素材/Maps/map5.jpg"));
			icon=ImageIO.read(new FileInputStream("./素材/Icon/图标.jpg"));
			b1=ImageIO.read(new FileInputStream("./素材/Maps/map4.jpg"));
			b2=ImageIO.read(new FileInputStream("./素材/Maps/b2.jpg"));
			b3=ImageIO.read(new FileInputStream("./素材/Maps/b3.png"));
			b4=ImageIO.read(new FileInputStream("./素材/Maps/b4.png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
