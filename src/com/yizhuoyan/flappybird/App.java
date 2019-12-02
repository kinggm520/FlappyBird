package com.yizhuoyan.flappybird;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.yizhuoyan.flappybird.model.Game;
import com.yizhuoyan.flappybird.model.GameConfig;
import com.yizhuoyan.flappybird.util.ResourceUtil;

public class App {
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					gameRun();	
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void gameRun(){
		GameConfig cfg=new GameConfig();
		final Game game=new Game(cfg);
		JFrame w=new JFrame();
		w.setContentPane(game.getView());
		w.pack();
		w.setIconImage((Image) ResourceUtil.get(cfg.gameAppIcon));
		w.setTitle(cfg.gameName);
		w.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		w.setResizable(false);
		w.setLocationRelativeTo(null);
		w.setVisible(true);
		w.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				game.stop();
			}
		});
		w.addMouseListener(new MouseAdapter() {     //鼠标点击控制
			@Override
			public void mousePressed(MouseEvent e) {
				game.currentScene.handleEvent(e);
			}
		});

		w.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {     //键盘空格键点击控制
				int KeyCode = e.getKeyCode();
				if (KeyCode == KeyEvent.VK_SPACE) {  //VK_SPACE值为32

					try {
						game.currentScene.handleEvent(e);

					} catch (Exception e2) {
						e2.printStackTrace();

					}


				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		game.run();
	}
}
