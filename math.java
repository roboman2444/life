import java.awt.image.BufferedImage;


public class math {
	BufferedImage a ;
	BufferedImage b ;
	main callback;
	public math(main callback){
		this.callback = callback;
		a = new BufferedImage(callback.xin,callback.yin,BufferedImage.TYPE_BYTE_BINARY);
		b = new BufferedImage(callback.xin,callback.yin,BufferedImage.TYPE_BYTE_BINARY);
	}
	public boolean checkcell(int x, int y){
		if(x<0)x=callback.xin-1;
		else if(x>=callback.xin)x=0;
		if(y<0)y=callback.yin-1;
		else if(y>=callback.yin)y=0;
		if(callback.tempbool){
			//System.out.println("a at"+ x +y);
			return(a.getRGB(x, y)==-16777216);
		}else{
			//System.out.println("b at"+ x +y);
			return(b.getRGB(x, y)==-16777216);
		}
	}
	public void calc(int s, int e){
		//System.out.println("calcin" + callback.tempbool);
		for(int y=s;y<e;y++){
			//callback.r.draw(); //uncomment this line to SEE the threads!
			for(int x=0;x<callback.xin;x++){
				int near=0;
				if(checkcell(x-1,y-1))near++;
				if(checkcell(x,y-1))near++;
				if(checkcell(x+1,y-1))near++;
				if(checkcell(x-1,y))near++;
				if(checkcell(x+1,y))near++;
				if(checkcell(x-1,y+1))near++;
				if(checkcell(x,y+1))near++;
				if(checkcell(x+1,y+1))near++;
				//rules implementation
				if(callback.tempbool){
					if(near<2 || near>3)b.setRGB(x, y,-1);
					else if(near==3)b.setRGB(x, y,-16777216);
					else if(near==2)b.setRGB(x, y,a.getRGB(x, y));
				}else{
					if(near<2 || near>3)a.setRGB(x, y,-1);
					else if(near==3)a.setRGB(x, y,-16777216);
					else if(near==2)a.setRGB(x, y,b.getRGB(x, y));
				}
			}
		}
	}
}
