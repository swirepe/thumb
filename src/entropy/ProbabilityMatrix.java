package entropy;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ProbabilityMatrix extends Matrix<Double>{

	protected ImageMatrix hostMatrix;

	public ProbabilityMatrix(BufferedImage bi){
		this(new ImageMatrix(bi));
	}
	
	public ProbabilityMatrix(ImageMatrix hostMatrix){
		this.hostMatrix = hostMatrix;
		init();
	}
	
	public void init(){
		HashMap<Integer, Double> freqs = getFrequencies(this.hostMatrix);
		fillProbabilityMatrix(freqs);
	}
	
	
	protected void fillProbabilityMatrix(HashMap<Integer, Double> freqs){
		double total = (double)this.hostMatrix.numElements();
		
		int X = this.hostMatrix.lenX();
		int Y = this.hostMatrix.lenY();
		this.matrix = new Double[Y][X];
		
		double pvalue;
		
		for(int y = 0; y < Y; y++){
			for(int x = 0; x < X; x++){
			

				// now find the p(x)log2(p(x))
				pvalue = freqs.get(this.hostMatrix.get(x,y)) / total;  // p(x)
				pvalue = pvalue * (Math.log( 1.0 / pvalue) / Math.log(2));  // p(x)log2(p(x))

				this.matrix[y][x] = new Double(pvalue);
			}
		}
	}
	
	
	public HashMap<Integer, Double> getFrequencies(){
		return getFrequencies(this.hostMatrix);
	}
	
	
	public HashMap<Integer, Double> getFrequencies(ImageMatrix imgMatrix){
		HashMap<Integer, Double> freqs = new HashMap<Integer, Double>();
		
		for(int pixelValue: imgMatrix){
			_incCount(freqs, pixelValue);
		}
			
		return freqs;
	}
	
	public void init(ImageMatrix hostMatrix){
		this.hostMatrix = hostMatrix;
		init();
	}
	
	
	private void _incCount(HashMap<Integer, Double> h, Integer key){
		if(h.containsKey(key)){
			h.put(key, h.get(key) + 1.0);
		}else{
			h.put(key, 1.0);
		}
	}
}
