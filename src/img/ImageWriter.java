package img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageWriter {

	
	public void write(String filename, BufferedImage bi){
	    File outputfile = new File(filename);
	    try {
			ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
			System.err.println("Error writing file: " + filename);
		}
	}
}
