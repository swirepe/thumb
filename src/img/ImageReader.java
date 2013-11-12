package img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entropy.Point;

public class ImageReader {
	public ImageReader(){
		
	}
	
	public BufferedImage read(String filename){
		return read(new File(filename));
	}
	
	public BufferedImage read(File file){
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (IOException e) {
			System.err.println("Error reading file:" + file.getAbsolutePath());
			//e.printStackTrace();
		}
		return bi;
	}
	
	public BufferedImage crop(BufferedImage src, Point topLeft, int size){
		return src.getSubimage(topLeft.x, topLeft.y, size, size);
	}
	
}
