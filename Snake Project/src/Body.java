import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Body {
	ArrayList<Point> body = new ArrayList<Point>();
	Point tail = new Point(0,0);

	public Body(Point head){
		body.add(new Point(head.x -50, head.y));
		tail.x = head.x -50;
		tail.y = head.y;
	}
	public void Move(Point head) {
		int Nextx = head.x;
		int Nexty = head.y;
		int Curx = 0;
		int Cury = 0;
		
		for(Point p: body) {
			Curx = p.x;
			Cury = p.y;
			p.x = Nextx;
			p.y = Nexty;
			Nextx = Curx;
			Nexty = Cury;
		}
	}
	
	public void Add(){
		body.add(new Point(-70, -70));
	}
		
}

