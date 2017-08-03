package com.langxikeji.PlaneWars;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlaneWars extends JPanel implements KeyListener {
	/**
	 * @author poc999
	 * @serial 2.0
	 *               �ʼ�: 1�����������ǣ�����������Ԫ�ص�ʱ��ͨ�������ǲ����޸�Ԫ�صġ� 
	 *                    2����Ϸ��ͣ��Ҫ�������󽹵㡣
	 *                    3��ÿһ���ж���ײ֮����Ҫ����ѭ���������ص���һ����ײ�ĵط��ж�
	 *                    4��������Ҫ�������� �ν���һ�λ���
	 *                    5��дһ���жϱ߽�ķ�������Ȼ��������ڴ�й©
	 */             
	private static final long serialVersionUID = 1L;

	//����ͼƬ�Ĵ�С
	public static final int WIDTH=500;
	public static final int HEIGHT=700;
	// ��ʼ״̬
	private int state = START;
	// ������Ϸ�ĸ���״̬��ֵ
	private static int START = 0;
	private static int RUNNING = 1;
	private static int PAUSE = 2;
	private static int GAME_OVER = 3;
	// ָ�������ĳ�ʼ״̬
	private boolean up, down, left, right, VK_Z, VK_X, VK_ENTER = false;
	// ʱ���ʼ��
	private int time = 0;
	// �����������ƶ���ʼֵ
	private int backgroundmove = 0;
	// ��¼Ӣ�ۻ��ķ���
	private int score = 0;
	// ������Ϸ�Ĺ����ĳ�ʼֵ
	private int stage = 0;
	// �����ĳ�ʼ����
	private int bomb = 10;
	// Ӣ�ۻ��ĳ�ʼѪ��
	private int life = 20;
	// ���ڲ�����������л��ĳ�ʼX����
	Random r = new Random();
	// �������֣���ը ���� �ӵ���Ч
	private AudioClip bgmusic;
	private AudioClip blastmusic;
	private AudioClip bombmusic;
	private AudioClip bulletmusic;
	// ���� ը�� ���� �ؿ���ǩ
	private JLabel scorelabel;
	private JLabel bomblabel;
	private JLabel lifelabel;
	private JLabel pointlabel;
	// ʹ�ü��ϱ������ж���
	private Hero hero;
	private Gift gift;
	
//	private Map<Object,ArrayList<Object>> map= new HashMap<>();
	
	
	private List<Blast> blast = new ArrayList<>();
	private List<Enemy1> enemy1 = new ArrayList<>();
	private List<Enemy2> enemy2 = new ArrayList<>();
	private List<Enemy3> enemy3 = new ArrayList<>();
	private List<Enemybullet> enemybullet = new ArrayList<>();
	private List<Herobullet> herobullet = new ArrayList<>();

	public PlaneWars() {
		URL u1 = PlaneWars.class.getResource("/bgmusic.wav");
		URL u2 = PlaneWars.class.getResource("/blastmusic.wav");
		URL u3 = PlaneWars.class.getResource("/Bomb.wav");
		URL u4 = PlaneWars.class.getResource("/bulletmusic.wav");

		bgmusic = Applet.newAudioClip(u1);
		blastmusic = Applet.newAudioClip(u2);
		bombmusic = Applet.newAudioClip(u3);
		bulletmusic = Applet.newAudioClip(u4);

		// ��ʼ�����
		board();
		// ʹ���߳�
		Thread game = new Thread() {

			public void run() {

				// �������ֵ�ѭ������
				bgmusic.loop();

				while (true) {
					/*
					 * Ӣ�ۻ����ƶ�,moving()������Ҫд�����档 ��Ϊ�տ�ʼ��Ҫ���Ƽ���enter����
					 * ʹ�õ��̣߳�ʹstate��ΪRUNNING
					 */
					moving();
					if (state == RUNNING && stage == 1) {
						// ���ƹؿ��ķ���
						if (score > 5000) {
							stage = 2;
							pointlabel.setText("�ڶ���");
						}
						// �л����ƶ�
						enemymoving(time);
						// Ӣ�ۻ��ӵ����ƶ�
						herobulletmoving(time);
						// �л��ӵ����ƶ�
						enemybulletmoving(time);
						// ���ɵл�
						enemy(time);
						// ���ɵл��ӵ�
						enemyfire(time);
						// Ӣ�ۻ��ӵ��͵л���ײ
						collision(time);
						// �л��ӵ���Ӣ�ۻ���ײ
						crash(time);
						// �����Ӣ�ۻ�����ײ
						giftcrash(time);
						//Խ����
						OutOfBounds();
						// ʱ��ĵ���
						time += 40;
						try {
							Thread.sleep(40);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					// �ڶ���(�����з���ִ������)
					if (state == RUNNING && stage == 2) {
						// �л����ƶ�
						enemymoving(time);
						enemymoving(time);
						// Ӣ�ۻ��ӵ����ƶ�
						herobulletmoving(time);
						herobulletmoving(time);
						// �л��ӵ����ƶ�
						enemybulletmoving(time);
						enemybulletmoving(time);
						// ���ɵл�
						enemy(time);
						enemy(time);
						// ���ɵл��ӵ�
						enemyfire(time);
						enemyfire(time);
						// Ӣ�ۻ��ӵ��͵л�
						collision(time);
						collision(time);
						// �л��ӵ���Ӣ�ۻ�
						crash(time);
						crash(time);
						// �����Ӣ�ۻ�����ײ
						giftcrash(time);
						giftcrash(time);
						//Խ����
						OutOfBounds();
						// ʱ��ĵ���
						time += 40;
						try {
							Thread.sleep(40);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					repaint();
				}
			}

			// �ɻ��ƶ��Ͳ���
			public void moving() {
				if (up) {
					hero.MoveUp();
				}
				if (down) {
					hero.MoveDown();
				}
				if (left) {
					hero.MoveLeft();
				}
				if (right) {
					hero.MoveRight();
				}
				if (VK_Z && bomb > 0) {
					// ���ű�ը��Ч
					bombmusic.play();
					bomb--;
					// ȫ����ը��Ч��
					int l1 = enemy1.size();
					int l2 = enemy2.size();
					int l3 = enemy3.size();
					for (int i = 0; i < l1; i++) {
						for (int j = 0; j < l2; j++) {
							for (int k = 0; k < l3; ) {
								Blast blast1 = new Blast(enemy1.get(i).x,
										enemy1.get(i).y);
								Blast blast2 = new Blast(enemy2.get(j).x,
										enemy2.get(j).y);
								Blast blast3 = new Blast(enemy3.get(k).x,
										enemy3.get(k).y);
								blast.add(blast1);
								blast.add(blast2);
								blast.add(blast3);
								l1--;
								l2--;
								l3--;
								break;
							}
						}
					}
					// ����ȫ���л��ķ���
					score += (1 * enemy1.size() + 2 * enemy2.size() + 3 * enemy3
							.size());
					// �л��������
					enemy1.clear();
					enemy2.clear();
					enemy3.clear();
					// �л��ӵ����
					enemybullet.clear();
					//ˢ�·�����ʾ
					scorelabel.setText("����:" + score);
					// ˢ����ʾը����
					bomblabel.setText("ը��" + bomb);
				}
				if (VK_X) {
					// ����Ӣ�ۻ��ӵ�����
					Herobullet herofire = hero.Herofire();
					// �����ӵ��������ٶ�
					if (time % 200 == 0) {
						bulletmusic.play();
						herobullet.add(herofire);
					}
				}
				if (VK_ENTER) {
					// �ж���Ϸ���Ƿ�Ϊ��ʼ״̬
					if (state == START && stage == 0) {
						state = RUNNING;
						stage = 1;
					}else if(state == GAME_OVER){
						state=RUNNING;
						Restart();
					}
				}

			}

			//���¿�ʼ��Ϸ
			public void Restart(){
				//���ж��󼯺ϳ�ʼ��
				herobullet.clear();
				enemy1.clear();
				enemy2.clear();
				enemy3.clear();
				enemybullet.clear();
				stage=1;
				state=1;
				score=0;
				life=20;
				bomb=10;
				//��ʼ�����
				board();
			}
			/*
			 * ������з������Ƿ�Խ��,�����ʹ��Խ�緽��,���������ڴ�й©
			 */
			public void OutOfBounds(){
				//�л�AԽ��
				for(int i=0,length=enemy1.size();i<length;i++){
					
					if(enemy1.get(i).OutOfBounds()){
						enemy1.remove(i);
						i--;
						length--;
					}
				}
				//�л�BԽ��
				for(int i=0,length=enemy2.size();i<length;i++){
					if(enemy2.get(i).OutOfBounds()){
						enemy2.remove(i);
						i--;
						length--;
					}
				}
				//�л�CԽ��
				for(int i=0,length=enemy3.size();i<length;i++){
					if(enemy3.get(i).OutOfBounds()){
						enemy3.remove(i);
						i--;
						length--;
					}
				}
				//Ӣ�ۻ��ӵ�Խ��
				for(int i=0,length=herobullet.size();i<length;i++){
					if(herobullet.get(i).OutOfBounds()){
						herobullet.remove(i);
						i--;
						length--;
					}
				}
				//�л��ӵ�Խ��
				for(int i=0,length=enemybullet.size();i<length;i++){
					if(enemybullet.get(i).OutOfBounds()){
						enemybullet.remove(i);
						i--;
						length--;
					}
				}
			}
			// �л���Ӣ���ӵ��ӵ���ײ�ķ���
			public void collision(long time) {
				// Ӣ�ۻ��ӵ����ϵĳ���
				int l1 = herobullet.size();
				// A�л����ϵĳ���
				int l2 = enemy1.size();
				// B�л����ϵĳ���
				int l3 = enemy2.size();
				// C�л����ϵĳ���
				int l4 = enemy3.size();
				// ����A�л����ӵ�����
				for (int i = 0; i < l1; i++) {
					for (int j = 0; j < l2; j++) {
						if (Flyer.boom(herobullet.get(i), enemy1.get(j))) {
							// һ����ײ�Ϳ�ʼ���ű�ը��Ч
							blastmusic.play();
							Herobullet hb = herobullet.get(i);
							Blast blast4 = new Blast(hb.x, hb.y);
							// ���뱬ը���󼯺�
							blast.add(blast4);
							// һ����ײ���Ƴ���ǰλ�õ�Ӣ�ۻ��ӵ��͵л�
							herobullet.remove(herobullet.get(i));
							enemy1.remove(enemy1.get(j));

							score += 20;
							scorelabel.setText("����:" + score);
							// ������ըͼЧ��
							// �л����ӵ����ϵĳ��ȷֱ��1
							l1--;
							l2--;
							// ������һ�ε�ǰλ�ü������
							i--;
							j--;
							// һ����ײ������ѭ��
							break;
						}
					}
				}
				// ����B�л����ӵ�����
				for (int i = 0; i < l1; i++) {
					for (int j = 0; j < l3; j++) {
						// �����ײ
						if (Flyer.boom(herobullet.get(i), enemy2.get(j))) {
							// һ����ײ�Ϳ�ʼ���ű�ը��Ч
							blastmusic.play();
							// ������ըͼЧ��
							Herobullet hb = herobullet.get(i);
							Blast blast1 = new Blast(hb.x, hb.y);
							// ���뱬ը���󼯺�
							blast.add(blast1);
							// һ����ײ���Ƴ���ǰλ�õ�Ӣ�ۻ��ӵ��͵л�
							herobullet.remove(herobullet.get(i));
							enemy2.remove(enemy2.get(j));

							score += 30;
							scorelabel.setText("����:" + score);
							// �л����ӵ����ϵĳ��ȷֱ��1
							l1--;
							l3--;
							// ������һ�ε�ǰλ�ü������
							i--;
							j--;
							// һ����ײ������ѭ��
							break;
						}
					}
				}
				// ����C�л����ӵ�����
				for (int i = 0; i < l1; i++) {
					for (int j = 0; j < l4; j++) {
						if (Flyer.boom(herobullet.get(i), enemy3.get(j))) {
							// һ����ײ�Ϳ�ʼ���ű�ը��Ч
							blastmusic.play();
							score += 50;
							scorelabel.setText("����:" + score);
							// ������ըͼЧ��
							Herobullet hb = herobullet.get(i);
							Blast blast1 = new Blast(hb.x, hb.y);
							// ���뱬ը���󼯺�
							blast.add(blast1);
							// һ����ײ���Ƴ���ǰλ�õ�Ӣ�ۻ��ӵ��͵л�
							herobullet.remove(herobullet.get(i));
							enemy3.remove(enemy3.get(j));
							// �л����ӵ����ϵĳ��ȷֱ��1
							l1--;
							l4--;
							// ������һ�ε�ǰλ�ü������
							i--;
							j--;
							// һ����ײ������ѭ��
							break;
						}
					}
				}
			}

			// �ж������Ӣ�ۻ���ײ�ķ���
			public void giftcrash(long time) {
				// �����ײ
				if (Flyer.boom(hero, gift)) {
					// �����ʼ��
					gift = new Gift(0, 0);
					// ���������ܼ����������ܼӵ���
					if (r.nextInt(2) == 1) {
						// �����������ܴ���5��
						if (bomb < 6) {
							bomb++;
							// ����С��0��
						} else {
							// ÿ�ε�����һ��
							bomb=5;
						}
					} else {

						// ����ֵ���ܴ���20��
						if (life < 21) {
							life+=2;
						} else {
							// ÿ������ֵ�ظ�2��
							life=20;
						}
					}
					lifelabel.setText("����:" + life);
					bomblabel.setText("����:" + bomb);
				}
			}

			// �л��ӵ���Ӣ�ۻ�����ײ����
			public void crash(long time) {
				int l3 = enemybullet.size();
				for (int i = 0; i < l3; i++) {
					if (Flyer.boom(enemybullet.get(i), hero)) {
						enemybullet.remove(enemybullet.get(i));
						life--;
						lifelabel.setText("����:" + life);
						// ����������ٵ�0,��Ϸ����
						if (life <= 0) {
							state = GAME_OVER;
						}
						l3--;
						i--;
						break;
					}
				}
			}

			// �л��ƶ�
			public void enemymoving(long time) {
				for (int i = 0; i < enemy1.size(); i++) {
					enemy1.get(i).move(time);
				}
				for (int i = 0; i < enemy2.size(); i++) {
					enemy2.get(i).move(time);
				}
				for (int i = 0; i < enemy3.size(); i++) {
					enemy3.get(i).move(time);
				}
				gift.move(time);
			}

			public void herobulletmoving(long time) {
				for (int i = 0; i < herobullet.size(); i++) {
					herobullet.get(i).move(time);
				}

			}

			public void enemybulletmoving(long time) {
				for (int i = 0; i < enemybullet.size(); i++) {
					enemybullet.get(i).move(time);
				}
			}

			public void enemyfire(long time) {
				// �л�A�����ӵ�
				if (time % 2000 == 0) {
					for (int i = 0; i < enemy1.size(); i++) {
						Enemybullet b = enemy1.get(i).enemyfire();
						enemybullet.add(b);
					}
				}
				// �л�B�����ӵ�
				if (time % 3000 == 0) {

					for (int i = 0; i < enemy2.size(); i++) {
						Enemybullet b = enemy2.get(i).enemyfire();
						enemybullet.add(b);
					}
				}
				// �л�C�����ӵ�
				if (time % 4000 == 0) {

					for (int i = 0; i < enemy3.size(); i++) {
						Enemybullet b = enemy3.get(i).enemyfire();
						enemybullet.add(b);
					}
				}
			}

			public void enemy(long time) {
				// ���ɵл�1��ʱ��
				if (time % 600 == 0) {
					Enemy1 e = new Enemy1(r.nextInt(450), 0);
					enemy1.add(e);
				}
				// ���ɵл�2��ʱ��
				if (time % 600 == 0) {
					Enemy2 e = new Enemy2(r.nextInt(450), 0);
					enemy2.add(e);
				}
				// ���������ʱ��
				if (score != 0 && score % 1000 == 0) {
					gift = new Gift(r.nextInt(450), 0);
				}
				// ���ɵл�3��ʱ��
				if (time % 1200 == 0) {
					Enemy3 e = new Enemy3(r.nextInt(450), 0);
					enemy3.add(e);
				}

			}

		};
		// �����߳�
		game.start();
	}

	

	// ������ը�����������
	public void board() {
		// �����������������
		this.removeAll();
		this.addKeyListener(this);
		// ��ʼ���������
		gift = new Gift(0, 0);
		// �ؿ���ǩ
		pointlabel = new JLabel("��һ��");
		pointlabel.setForeground(Color.green);
		Font font = new Font("����", Font.PLAIN, 20);
		pointlabel.setFont(font);
		// ������ǩ
		scorelabel = new JLabel("����:" + score);
		// ���ñ�����ɫ
		scorelabel.setForeground(Color.RED);
		// ը����ǩ
		bomblabel = new JLabel("����:" + bomb);
		bomblabel.setForeground(Color.RED);
		// ������ǩ
		lifelabel = new JLabel("����:" + life);
		lifelabel.setForeground(Color.RED);
		// ��Ϸ������ť
		final JButton gameon = new JButton("����");
		gameon.setForeground(Color.RED);
		// ���ð�ťΪ͸��
		gameon.setContentAreaFilled(false);
		// ��Ϸ��ͣ��ť
		JButton gamestop = new JButton("��ͣ");
		gamestop.setForeground(Color.RED);
		gamestop.setContentAreaFilled(false);
		// ������ť��Ӽ���
		gameon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (state == PAUSE) {
					state = RUNNING;
					/*
					 * ����ʹJPanel��Ϊ���㣡һ����Ҫ�������󽹵㡣��Ȼ��ͣһ�μ��̾�ֱ��ʧ����
					 */
					PlaneWars.this.requestFocusInWindow();
				}
			}
		});
		// ��ͣ��ť��Ӽ���
		gamestop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (state == RUNNING) {
					state = PAUSE;
				}
			}
		});
		// �½�һ����壬�Ѱ�ť�ͱ�ǩ�����뵽����м�
		JPanel jp = new JPanel();
		// ���ñ�����ɫΪ��ɫ
		jp.setBackground(Color.BLACK);
		// ʹ�ø������ֹ�����
		jp.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 10));
		jp.add(pointlabel);
		jp.add(bomblabel);
		jp.add(scorelabel);
		jp.add(lifelabel);
		jp.add(gameon);
		jp.add(gamestop);
		// JPanel ������������
		this.add(jp);
		// Ӣ�ۻ��ĳ�ʼλ��
		hero = new Hero(230, 600);
	}

	/*
	 * ���������Ļ���
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			BufferedImage image;
			if (backgroundmove > WIDTH) {
				backgroundmove = 0;
			}
			image = ImageIO.read(new FileInputStream("./�ز�/Maps/map3.jpg"));// ���ر���
			g.drawImage(image, 0, backgroundmove, WIDTH, HEIGHT, null);
			backgroundmove += 1;
			g.drawImage(image, 0, -HEIGHT + backgroundmove, WIDTH, HEIGHT, null); // ʹ�ñ����ǳ�����
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		// ���Ϊ��ʼ״̬
		if (state == START) {
			paintstart(g);
			// ���Ϊ��Ϸ����״̬
		} else if (state == RUNNING) {
			// ����Ӣ�ۻ�
			painthero(g);
			// ���Ƶл�
			paintenemy(g);
			// ����һ��л��ӵ�
			paintenemybullets(g);
			// ����Ӣ�ۻ��ӵ�
			paintherobullets(g);
			// ���Ʊ�ըЧ�����Ƴ���ըЧ��
			paintblast(g);
			// ��������
			paintgift(g);
		} else if (state == PAUSE) {
			g.setFont(new Font("����", Font.BOLD, 50));
			g.setColor(Color.RED);
			g.drawString("��Ϸ��ͣ!!!", 120, 330);
		}
		// ���Ϊ��Ϸ����״̬
		else if (state == GAME_OVER) {
			paintover(g);
		}

	}

	// �÷���Ϊ������Ϸ��������
	public void paintover(Graphics g) {
		g.drawImage(ImageRead.b1, 0, 0, 500, 800, null);
		g.drawImage(ImageRead.b2, 0, 0, 500, 150, null);
		Font font1 = new Font("����", Font.ITALIC, 60);
		Font font2 = new Font("����", Font.PLAIN, 30);
		g.setColor(Color.RED);
		g.setFont(font1);
		g.drawString("GAME OVER", 100, 300);
		g.setFont(font2);
		g.drawString("score:" + score + "---���Ѿ��ܰ���!", 80, 400);
		g.setFont(font2);
		g.drawString("�밴ENTER�����¿�ʼ��Ϸ", 100, 500);
	}

	// �÷���Ϊ���Ƴ�ʼ����
	public void paintstart(Graphics g) {
		g.drawImage(ImageRead.b1, 0, 0, 500, 800, null);
		g.drawImage(ImageRead.b2, 0, 0, 500, 150, null);
		g.drawImage(ImageRead.b3, 50, 200, null);
		g.drawImage(ImageRead.b4, 150, 200, 300, 400, null);
		Font font = new Font("����", Font.PLAIN, 20);
		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString("��ENTER����ʼ��Ϸ", 50, 500);
		g.drawString("X:�����ӵ�,Z:���Ƴ�������", 50, 550);
		g.drawString("�����:��,��,��,��", 50, 600);
		g.drawString("����:poc999", 50, 650);
	}

	// ��������
	public void paintgift(Graphics g) {
		// ��Ϊ����ĳ�ʼλ��Ϊ����Ϊ0��0,
		if (gift.x != 0 && gift.y != 0) {
			g.drawImage(ImageRead.gift, gift.x, gift.y, 20, 20, null);
		}
	}

	// ����Ӣ�ۻ�
	public void painthero(Graphics g) {

		g.drawImage(ImageRead.hero, hero.x, hero.y, 60, 60, null);
	}

	// ���Ʊ�ըͼ
	public void paintblast(Graphics g) {

		int l3 = blast.size();
		for (int i = 0; i < l3; ) {
			Blast b = blast.get(i);
			// ����֮������ɾ��
			b.draw(g);
			blast.remove(b);
			l3--;
			i--;
			break;
		}
	}

	// �������ел�
	public void paintenemy(Graphics g) {

		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 e = enemy1.get(i);
			e.draw(g);

		}

		for (int i = 0; i < enemy2.size(); i++) {
			Enemy2 e = enemy2.get(i);
			e.draw(g);

		}

		for (int i = 0; i < enemy3.size(); i++) {
			Enemy3 e = enemy3.get(i);
			e.draw(g);

		}
	}

	// ����A��л��ӵ�
	public void paintenemybullets(Graphics g) {

		for (int i = 0; i < enemybullet.size(); i++) {
			Enemybullet eb = enemybullet.get(i);
			eb.draw(g);

		}
	}

	// ����Ӣ�ۻ��ӵ�
	public void paintherobullets(Graphics g) {

		for (int i = 0; i < herobullet.size(); i++) {
			Herobullet hb = herobullet.get(i);
			hb.draw(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// ���̰���
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_Z:
			VK_Z = true;
			break;
		case KeyEvent.VK_X:
			VK_X = true;
			break;
		case KeyEvent.VK_ENTER:
			VK_ENTER = true;
			break;
		default:
			break;

		}
		repaint();
	}

	// �����ͷ�
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_Z:
			VK_Z = false;
			break;
		case KeyEvent.VK_X:
			VK_X = false;
			break;
		case KeyEvent.VK_ENTER:
			VK_ENTER = false;
			break;
		default:
			break;
		}
	}
}
