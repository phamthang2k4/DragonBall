package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

import Audio.AudioPlayer;
import Main.GamePanel;

public class Winner extends GameState {
	
	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = {
		"Press enter to turn back",
		"Quit"
	};
	
	
	
	private Font font;
	
	private AudioPlayer bgMusic;
	
	public Winner(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		try {
			
			bg = new Background("/Backgrounds/victory_bg.png", 1);
			bg.setVector(0.2, 0);
			
		
				 
			
			font = new Font("Arial", Font.PLAIN, 12);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		init();
		
	}
	
	public void init() {
		bgMusic = new AudioPlayer("/Music/you_win.mp3");
		bgMusic.play();
	}
	
	public void update() {
		bg.update();
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		
		
		
		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(new Color(23, 121, 27));
			}
			else {
				g.setColor(new Color(198, 29, 29));
			}
//			g.drawString(options[i], x-160, 140 + i * 15);
			
			 // Lấy FontMetrics từ Graphics
            FontMetrics metrics = g.getFontMetrics(font);

            // Lấy kích thước của mỗi lựa chọn menu
            int optionWidth = metrics.stringWidth(options[i]);
            int optionHeight = metrics.getHeight();
            int x = (GamePanel.WIDTH * GamePanel.SCALE - optionWidth) / 2; // Căn lề 2 bên

            g.drawString(options[i], x - 160, 140 + i * (optionHeight + 5));
            // Sử dụng optionHeight để căn chỉnh văn bản
		}
		
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL1STATE);
			bgMusic.stop();
		}
		if(currentChoice == 1) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
	
}
