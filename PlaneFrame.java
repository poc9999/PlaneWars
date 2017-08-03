package com.langxikeji.PlaneWars;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * @author poc999
 * 
 */
public class PlaneFrame extends JFrame {
	
	//������Ĵ�С
	public static final int FRAME_WIDTH=500;
	public static final int FRAME_HEIGHT=700;
	//�汾����
	private static final long serialVersionUID = 1L;
	// ����JPlane�Ķ���
	PlaneWars pw = new PlaneWars();

	// ����PlaneFrame�Ĺ���������ʼ������
	public PlaneFrame() {
		// ��JPanel�Ľ���״̬����Ϊtrue��
		pw.setFocusable(true);
		// ��JFrame�Ȼ�ý��㣬Ȼ��JPanel�ڻ�ý���
		pw.requestFocus();
		this.add(pw, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		// ���������ڵĶ���
		PlaneFrame pf = new PlaneFrame();
		// ���������������
		pf.setTitle("PlaneWars by2.0");
		//���ô����ö�
		pf.setAlwaysOnTop(true);
		// ����������Ĵ�С
		pf.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		// ���������������ʾ
		pf.setLocationRelativeTo(null);
		// ������Ϸ����ͼ��
		pf.setIconImage(ImageRead.icon);
		// ����������Ĺرպ��˳�
		pf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ��ʾ������
		pf.setVisible(true);
		// �����岻������
		pf.setResizable(false);
	}

}
