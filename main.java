import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

public class main extends JFrame implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
	public BufferStrategy dbuf;
	public render r;
	public math m;
	public int zendx;
	public int zendy;
	public int zstartx=0;
	public int zstarty=0;
	public int rendx = 0;
	public int rendy = 0;
	public int starty;
	public int startx;
	public int maxthreads = 8;
	public int xin = 512;
	public int yin = 512;
	public boolean tempbool = false;
	public boolean menuup=false;
	public static void main(String[] args){
		new main();

	}
	main(){
		super ("main");
		this.setSize(xin,yin);
		this.setVisible(true);
		this.createBufferStrategy(2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("yo, type your x");						//get the height and width
		System.out.println("yo, type your y");
		dbuf = this.getBufferStrategy();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		r = new render(this);
		m= new math(this);
		for(int y=0;y<yin;y++)for(int x=0;x<xin;x++)if(new Random().nextBoolean())m.b.setRGB(x, y,-1);
		tempbool=true;
		menu.callback = this;
		rendx=xin;
		rendy=yin;
		while (true){
			if(menuup)menu.menu();
			else{
				tempbool=!tempbool;
				Thread[] tarray = new Thread[maxthreads];
				for(int i=0;i<maxthreads;i++){
					Thread t = new thread(i);
					t.start();
					tarray[i]=t;
				}
				for (int i = 0; i < maxthreads; i++) {
					if (!tarray[i].isAlive())
						continue;
					else{
						try {
							tarray[i].join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
				r.draw();
		}
	}
	public class thread extends Thread{
		int threadnum;
		public thread (int threadnum){
			this.threadnum = threadnum;
		}

		@Override
		public void run(){
			m.calc(yin/maxthreads*threadnum,yin/maxthreads*(threadnum+1));
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(!menuup)menuup=true;
		else{
			switch(arg0.getKeyCode()){
			case KeyEvent.VK_L: menu.load(); break;
			case KeyEvent.VK_S: menu.save(); break;
			case KeyEvent.VK_G: menu.go(); break;
			case KeyEvent.VK_R: menu.random(); break;
			case KeyEvent.VK_T: menu.step(); break;
			case KeyEvent.VK_D: menu.delay(); break;
			case KeyEvent.VK_X: System.exit(0); break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
	@Override
	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(rendx==xin&&rendy==yin&&startx==0&&starty==0){
			zstartx = e.getX();
			zstarty=e.getY();
		}
		else{
		}
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(rendx==xin&&rendy==yin&&startx==0&&starty==0){
			zendx = e.getX();
			zendy=e.getY();
		}
		else{
		}
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		//if (menuup){
		//	m.b.setRGB(e.getX(), e.getY(),-1);
		//	m.a.setRGB(e.getX(), e.getY(),-1);
		//}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
