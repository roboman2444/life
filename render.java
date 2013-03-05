import java.awt.Graphics;
import java.awt.Toolkit;

public class render {
	main callback;
	public render(main callback){
		this.callback = callback;
	}
	public void draw(){
		Graphics g = null;
		try {
			g = callback.dbuf.getDrawGraphics();
			if(callback.tempbool)g.drawImage(callback.m.b,callback.startx,callback.starty,callback.rendx,callback.rendy,null);
			else g.drawImage(callback.m.a,callback.startx,callback.starty,callback.rendx,callback.rendy,null);
		}
		catch(Exception e){}
		finally {
			if (g != null) g.dispose();
		}
		callback.dbuf.show();
		Toolkit.getDefaultToolkit().sync();
	}

}
