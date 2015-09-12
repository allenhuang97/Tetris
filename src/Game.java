import java.applet.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;

public class Game extends Applet implements Runnable{
	private Thread th;

	private Image dbImage;
	private Graphics dbg;

	private Grid grid = new Grid();

	//start method, to start thread
	public void start (){
		th = new Thread(this);
		th.start();	
	}//end of start

	//stop method, to stop thread
	public void stop(){
		th.stop();
	}//end of stop method

	//destroy method, to destroy thread
	public void destroy(){
		th.stop();
	}//end of destroy method

	//update method to update backGround and screen
	public void update (Graphics g){
		if (dbImage == null){
			dbImage = createImage (this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics ();
		}

		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

		dbg.setColor (getForeground());
		paint (dbg);

		g.drawImage (dbImage, 0, 0, this);
	}//end of update method

	public void init() {

	}

	//run method, constantly runs to refresh paint
	public void run(){
		while(true){
			repaint();
		}
	}

	public void paint (Graphics g){
		//40 width. 40 height. 10 Blocks Wide, 15 Blocks High
		this.resize(400, 600);
		grid.paint(g);
	}
}
