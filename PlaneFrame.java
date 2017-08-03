package com.langxikeji.PlaneWars;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * @author poc999
 * 
 */
public class PlaneFrame extends JFrame {
	
	//主窗体的大小
	public static final int FRAME_WIDTH=500;
	public static final int FRAME_HEIGHT=700;
	//版本控制
	private static final long serialVersionUID = 1L;
	// 创建JPlane的对象
	PlaneWars pw = new PlaneWars();

	// 创建PlaneFrame的构造器，初始化对象
	public PlaneFrame() {
		// 将JPanel的焦点状态设置为true。
		pw.setFocusable(true);
		// 让JFrame先获得焦点，然后JPanel在获得焦点
		pw.requestFocus();
		this.add(pw, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		// 创建主窗口的对象
		PlaneFrame pf = new PlaneFrame();
		// 设置主窗体的名字
		pf.setTitle("PlaneWars by2.0");
		//设置窗体置顶
		pf.setAlwaysOnTop(true);
		// 设置主窗体的大小
		pf.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		// 设置主窗体居中显示
		pf.setLocationRelativeTo(null);
		// 设置游戏界面图标
		pf.setIconImage(ImageRead.icon);
		// 设置主窗体的关闭和退出
		pf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 显示主窗体
		pf.setVisible(true);
		// 主窗体不可拉伸
		pf.setResizable(false);
	}

}
