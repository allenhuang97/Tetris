import java.applet.*;
import java.awt.*;

public class LoadingAnimation extends Applet implements Runnable{
	private Thread th;

	private Image dbImage;
	private Graphics dbg;

	private Rectangle []loadRect = new Rectangle[8];
	private int [][] rectProperties = new int [loadRect.length][2];
	private boolean start = true;
	private boolean end = false;
	private int startNum = 1;
	private int startCount = 0;
	private int loadTime = 0;

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

	public void init(){
		for(int i = 0; i < loadRect.length; i++){
			loadRect[i] = new Rectangle(0, 0, 40, 40);
			rectProperties[i][0] = 2;
			rectProperties[i][1] = 0;
		}
	}

	public void run(){
		while(end == false){
			if (start == true){
				if(startCount == 40){
					startNum++;
					startCount = 0;
					if(startNum == loadRect.length)
						start = false;
				}
				startCount++;
			}
			if((loadTime%75 == 0)&&(loadTime!=0)&&(loadTime/75 <= loadRect.length))
				rectProperties[(loadTime/75)-1][1] = 1;
			for(int i = 0; i < startNum; i ++){
				switch(rectProperties[i][0]){
				case 0:
					loadRect[i].setBounds(loadRect[i].x, loadRect[i].y-1,40,40);
					break;
				case 1:
					loadRect[i].setBounds(loadRect[i].x+1, loadRect[i].y,40,40);
					break;
				case 2:
					loadRect[i].setBounds(loadRect[i].x, loadRect[i].y+1,40,40);
					break;
				case 3:
					loadRect[i].setBounds(loadRect[i].x-1, loadRect[i].y,40,40);
					break;
				}
			}
			reDirect();
			if(loadTime/75 != loadRect.length)
				loadTime++;
			repaint();
			try {
				Thread.sleep(10);
			} 
			catch (InterruptedException e) {
			}
		}
	}

	public void reDirect(){
		for(int i = 0; i < startNum; i ++){
			switch(rectProperties[i][0]){
			case 0:
				if(loadRect[i].y == 0){
					rectProperties[i][0] = 3;
					if(loadTime/75 == loadRect.length)
						end = true;
				}
				break;
			case 1:
				if(loadRect[i].x == 80){
					rectProperties[i][0] = 0;
					if(loadTime/75 == loadRect.length)
						end = true;
				}
				break;
			case 2:
				if(loadRect[i].y == 80){
					rectProperties[i][0] = 1;
					if(loadTime/75 == loadRect.length)
						end = true;
				}
				break;
			case 3:
				if(loadRect[i].x == 0){
					rectProperties[i][0] = 2;
					if(loadTime/75 == loadRect.length)
						end = true;
				}
				break;
			}
		}
	}

	public void paint (Graphics g){
		this.resize(120, 120);
		for(int i = 0; i < loadRect.length; i++){
			if(rectProperties[i][1]==0)
				g.setColor(Color.RED);
			if(rectProperties[i][1]==1)
				g.setColor(Color.GREEN);
			g.fillRect(loadRect[i].x, loadRect[i].y, loadRect[i].width, loadRect[i].height);
			g.setColor(Color.BLACK);
			if(end == false)
				g.drawRect(loadRect[i].x, loadRect[i].y, loadRect[i].width, loadRect[i].height);
			else{ 
				g.drawRect(0, 0, 120, 120);
				g.drawRect(40, 40, 40, 40);
			}
		}
	}
}