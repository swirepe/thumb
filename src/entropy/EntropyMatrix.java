package entropy;

import java.awt.image.BufferedImage;

public class EntropyMatrix extends Matrix<Double>{

	protected ProbabilityMatrix hostMatrix;
	
	public EntropyMatrix(BufferedImage bi){
		this(new ProbabilityMatrix(bi));
		
	}
	
	public EntropyMatrix(ProbabilityMatrix hostMatrix){
		this.hostMatrix = hostMatrix;
	}
	
	/**
	 * just check region by region
	 * also, for some reason, least entropy works better than most
	 * @return the top-left corner of the region with the lowest entropy
	 */
	public Point mostInterestingRegion(int size){
		double leastEntropy = Double.MAX_VALUE;
		int leastX = 0;
		int leastY = 0;
		

		double currentEntropy;
		for(int x = 0; x < this.hostMatrix.lenX() - size; x++){
			for(int y = 0; y < this.hostMatrix.lenY() - size; y++){
				currentEntropy = entropyForRegion(x,y,size);
				if(currentEntropy < leastEntropy){
					leastEntropy = currentEntropy;
					leastX = x;
					leastY = y;
				}
			}
		}

		
		return new Point(leastX, leastY);
	}
	
	public double entropyForRegion(int x, int y, int size){
		double total = 0.0;
		for(double prob : this.hostMatrix.getRegion(x,y, size)){
			total += prob;
		}
		
		return total;
	}
	
	// if we were being really clever, we wouldn't have to sum the whole region each time
	// if we add on a column to the right, we subtract the column from the left
	// and kind of inchworm our way down
	// this is the naive way
	// this is still in development
	/*
	public Point mostInterestingFancy(int size){
		double[][] entropies = new double[this.hostMatrix.lenY()][this.hostMatrix.lenX()];
		
		// build up a table of the entropy for the columns
		for(int y = 0; y <this.hostMatrix.lenY(); y++){
			for(int x = 0; x < this.hostMatrix.lenX(); x++){
				entropies[y][x] = sum(this.hostMatrix.getColumn(x, y, size));
			}
		}
		
		// find the point at which it is the greatest
		// entropyblock: a sum of (size) number of entropy columns
		double entropyBlock = 0;
		for(int i = 0; i < size; i++){
			entropyBlock += entropies[i][0];
		}
		
		double greatestEntropy = entropyBlock * -1;
		int greatestX = 0;
		int greatestY = 0;
		
		for(int y = size; y < this.hostMatrix.lenY(); y++ ){
			for(int x = 0; x < this.hostMatrix.lenX(); x++){
				entropyBlock -= entropies[y - size][x];
				entropyBlock += entropies[y][x];
				if(entropyBlock * -1 > greatestEntropy){
					greatestEntropy = entropyBlock * -1;
					greatestX = x;
					greatestY = y;
				}
					
			}
			
		}
		
		
		return new Point(greatestX, greatestY);
	}
   //*/
	public double sum(Iterable<Double> c){
		double total = 0.0;
		for(double x: c){
			total += x;
		}
		return total;
	}
	
}
