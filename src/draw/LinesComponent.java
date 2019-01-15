package draw;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import draw.Line;

public class LinesComponent extends JComponent{

private int x1;
private int y1;
private int x2;
private int x3;
private int x4;
private int y2;
private int y3;
private int y4;

public int left = 0;
public int right = 0;
public Color colour = Color.BLACK;


private final LinkedList<Line> lines = new LinkedList<Line>();

public void addLine(int x1, int x2, int x3, int x4) {
    addLine(x1, x2, x3, x4, colour);
}

public void addLine(int x1, int x2, int x3, int x4, Color color) {
    lines.add(new Line(x1,x2,x3,x4, color));        
    repaint();
}

public void clearLines() {
    lines.clear();
    repaint();
}

public void addArrow() {
	
	left = 0;
	right = 0;
	
	colour = Color.BLACK;
	
	x1 = 30;
	x2 = 30;
	x3 = 20;
	x4 = 40;
	
	y1 = 10;
	y2 = 0;
	y3 = 0;
	y4 = 0;
	
	 addLine(x1, y1, x2, y2, Color.BLACK);
	 addLine(x1, y1, x3, y3, Color.BLACK);
	 addLine(x1, y1, x4, y4, Color.BLACK);
}



private void moveArrowX(int x) {
	x1 += x;
	x2 += x;
	x3 += x;
	x4 += x;
	
	 lines.set(0, new Line(x1, y1, x2, y2, Color.BLACK));
	 lines.set(1, new Line(x1, y1, x3, y3, Color.BLACK));
	 lines.set(2, new Line(x1, y1, x4, y4, Color.BLACK));
	 repaint();
}


private void moveArrowY(int y) {
	y1 += y;
	y2 += y;
	y3 += y;
	y4 += y;
	
	 lines.set(0, new Line(x1, y1, x2, y2, Color.BLACK));
	 lines.set(1, new Line(x1, y1, x3, y3, Color.BLACK));
	 lines.set(2, new Line(x1, y1, x4, y4, Color.BLACK));
	 repaint();
}

public void moveForward(int newValue, boolean pen) {
	
	if(right == 3){
		int x = x1;
		int y = y1;
		moveArrowY(newValue);
		if(pen == true){
			addLine(x, y, x1, y1, colour);
		}
		
	} else if ( left == 3) {
		int x = x1;
		int y = y1;
		moveArrowY(-newValue);
		if(pen == true){
			addLine(x, y, x1, y1, colour);
		}
		
	} else if(left == 0) {
		int x = x1;
		int y = y1;
		moveArrowX(newValue);
		if(pen == true){
			addLine(x, y, x1, y1, colour);
		}
	} else {
		int x = x1;
		int y = y1;
		moveArrowX(-newValue);
		if(pen == true){
			addLine(x, y, x1, y1, colour);
		}
		
	}
}

public void rotateLeft() {
	
	if(left == 0){
		x2 -= 10;
		x4 -= 20;
		
		y2 += 10;
		y3 += 20;
		
		right = 4;
	} else if (left == 1){
		x2 += 10;
		x3 += 20;
		
		y2 += 10;
		y4 += 20;
	}else if (left == 2){
		y2 -= 10;
		x2 += 10;
		x4 += 20;
		
		y3 -= 20;
	} else if (left == 3){
		x3 -= 20;
		
		x2 -= 10;
		y2 -= 10;
		
		y4 -= 20;
	}

	if (left < 3){
		left ++;
		if (right == 0){
			right = 4;
		} else {
			right --;
		}
		
	} else {
		left = 0;
		right = 0;
	}
	


	
	 lines.set(0, new Line(x1, y1, x2, y2, Color.BLACK));
	 lines.set(1, new Line(x1, y1, x3, y3, Color.BLACK));
	 lines.set(2, new Line(x1, y1, x4, y4, Color.BLACK));
	 
	 repaint();
}




public void rotateRight() {
	
	
	if(right == 0){
		x3 += 20;
		
		x2 += 10;
		y2 += 10;
		
		y4 += 20;
		
		left = 4;
	} else if (right == 1){
		y2 += 10;
		x2 -= 10;
		x4 -= 20;
		
		y3 += 20;
	}else if (right == 2){
		x2 -= 10;
		x3 -= 20;
		
		y2 -= 10;
		y4 -= 20;
	} else if (right == 3){
		x2 += 10;
		x4 += 20;
		
		y2 -= 10;
		y3 -= 20;
	}

	if (right < 3){
		right ++;
		if (left == 0){
			left = 4;
		} else {
			left --;
		}
	} else {
		right = 0;
		left = 0;
	}
	


	
	 lines.set(0, new Line(x1, y1, x2, y2, Color.BLACK));
	 lines.set(1, new Line(x1, y1, x3, y3, Color.BLACK));
	 lines.set(2, new Line(x1, y1, x4, y4, Color.BLACK));
	 
	 repaint();
}

public void setColour(Color colour){
	this.colour = colour;
}

public Color getColour(){
	return colour;
}

public int getCenterX() {
	return x1;
}

public int getCenterY() {
	return y1;
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Line line : lines) {
        g.setColor(line.color);
        g.drawLine(line.x1, line.y1, line.x2, line.y2);
    }
}


}