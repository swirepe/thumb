package entropy;

import java.awt.image.BufferedImage;

public class ImageMatrix extends Matrix<Integer>{

	protected BufferedImage hostImage;

	
	public ImageMatrix(BufferedImage bi){
		this.hostImage = bi;
		init();
	}
	
	public void init(){
		this.matrix = new Integer[hostImage.getHeight()][hostImage.getWidth()];
		
	    for(int y=0; y < hostImage.getHeight(); y++) {
	        for(int x=0; x < hostImage.getWidth(); x++){
	            matrix[y][x] = truncateRBG(hostImage.getRGB(x,y));
	        }
	    }   
	}
	
	public void init(BufferedImage bi){
		this.hostImage = bi;
		init();
	}
	
	/**
	 * 
	 * @param rbg
	 * @return an rbg value with the tens clipped off
	 */
	public int truncateRBG(int rbg){
		int red = (rbg >> 16) & 0x000000FF;
		int green = (rbg >>8 ) & 0x000000FF;
		int blue = (rbg) & 0x000000FF;
		
		red /= 10;
		green /= 10;
		blue /= 10;
		
		return toRBG(red, green, blue);
	}
	
	public int toRBG(int red, int green, int blue){
		return ((red << 16) | (green << 8) | blue);
	}
	
}
