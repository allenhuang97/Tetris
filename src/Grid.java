import java.awt.Graphics;
import java.awt.Rectangle;

public class Grid {

	private Rectangle [][] gridRect = new Rectangle[15][10];

	Grid(){
		createGrid();
	}

	public void createGrid(){
		for(int x = 0; x < gridRect[0].length; x++){
			for(int y = 0; y < gridRect.length; y++){
				gridRect[y][x] = new Rectangle(x*40, y*40, 40, 40);
			}
		}
	}
	public void paint (Graphics g){
		for(int x = 0; x < gridRect[0].length; x++){
			for(int y = 0; y < gridRect.length; y++){
				g.drawRect(gridRect[y][x].x, gridRect[y][x].y, gridRect[y][x].width, gridRect[y][x].height);
			}
		}
	}
}
