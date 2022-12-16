import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import java.util.random.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Myframe extends JFrame implements KeyListener {
	public boolean start = true;
	public boolean running = false;
	public int offsetx = 25; //changes the location of the entire game screen
	public int offsety = 40;
	public Point head = new Point(500, 500);
	public Body body = new Body(head);
	public int ApplePosX = (int)(20 * Math.random()) * 50;
	public int ApplePosY = (int)(20 * Math.random()) * 50;
	public int dx = 0;
	public int dy = 0;
	public Myframe() {
		this.setVisible(true);
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(this);
		
		Timer t = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if (head.x == ApplePosX & head.y == ApplePosY) { //apple gets eaten
						ApplePosX = (int)(20 * Math.random()) * 50;
						ApplePosY = (int)(20 * Math.random()) * 50;
						body.Add();
						repaint();
				}
				body.Move(head);
				/*
				can combine these movements into two lines step 
				head.x += (50 * dx);
				head.y += (50 * dy);
				*/
				if (dx == 1) {
					head.x += 50;
				}
				if (dx == -1) {
					head.x -= 50;
				}
				if (dy == 1) {
					head.y += 50;
				}
				if (dy == -1) {
					head.y -= 50;
				}
				if (head.x == 1000 || head.x == -50 || head.y == 1000 || head.y == -50) {
					System.out.println("GAME OVER!");
					running = false;
					body = new Body(head);
				}
			for(Point p: body.body) {
					if (p.x == head.x & p.y == head.y) {
						running = false;
						head.x = 500;
						head.y = 500;
						body = new Body(head);
					}
				}
				if (running == false) {
					head.x = 500;
					head.y = 500;
					
				}
 				repaint();	
			}
			
		});
		t.start();
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(offsetx, offsety, 1000, 1000);
		
		g.fillRect(head.x + offsetx, head.y + offsety, 50, 50); //head
		for(Point p : body.body) {
			g.fillRect(p.x + offsetx, p.y + offsety, 50, 50);
		}
		g.setColor(Color.red);
		g.fillOval((int) ApplePosX + offsetx, (int) ApplePosY +offsety, 50, 50); //apple
		//for the drawString you just needed parens around the "" + score 
		// g.drawString(("" + score), x, y); 
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int Key = e.getKeyCode();
		if( Key == KeyEvent.VK_S & dy != -1){
			dy = 1;
			dx = 0;
		}
		if( Key == KeyEvent.VK_W & dy != 1){
			dy = -1;
			dx = 0;
		}
		if( Key == KeyEvent.VK_D & dx != -1){
			dx = 1;
			dy = 0;
		}
		if( Key == KeyEvent.VK_A & dx != 1){
			dx = -1;
			dy = 0;
		}
		running = true;
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
