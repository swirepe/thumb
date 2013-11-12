package main;

import img.ImageReader;
import img.ImageWriter;

import java.awt.image.BufferedImage;
import java.io.File;

import entropy.EntropyMatrix;
import entropy.Point;

public class Worker implements Runnable{

	private String filename;
	private int size;
	
	public Worker(String filename, int size) {
		this.filename = filename;
		this.size = size;
	}

	public void work(String filename, int size){
		long startTime = System.currentTimeMillis();
		System.err.println(filename + "\tStarting.");
		File infile = new File(filename);
		
		BufferedImage bi = new ImageReader().read(infile);
		
		EntropyMatrix entropyMatrix = new EntropyMatrix(bi);
		
		Point mostInterestingPoint = entropyMatrix.mostInterestingRegion(size);
		System.err.println(filename + "\tMost Interesting Point: " + mostInterestingPoint.x + " " + mostInterestingPoint.y);
		BufferedImage cropped = new ImageReader().crop(bi, mostInterestingPoint, size);
		
		String parent = infile.getParent();
		if(parent == null){
			parent = "." ;
		}

		parent +=  File.separator;
		String newFileName = parent + "thumb-" + infile.getName() ;
		System.err.println(filename + "\tWriting to: " + newFileName);
		new ImageWriter().write(newFileName, cropped);
		
		long endTime = System.currentTimeMillis();
		System.err.println(filename + "\tDone in " + ((endTime - startTime ) / 1000 ) + " seconds.");
	}

	@Override
	public void run() {
		work(this.filename, this.size);
		
	}
	
}
