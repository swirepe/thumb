package entropy;

import java.util.Iterator;


public class MatrixIterator<T> implements Iterator<T>{
	
	protected T[][] matrix;
	protected int x = 0;
	protected int y = 0;
	
	protected int xStart;
	protected int xMax;
	protected int yMax;
	
	public MatrixIterator(T[][] matrix){
		this.matrix = matrix;
		this.xMax = matrix[0].length;
		this.yMax = matrix.length;

		this.xStart = 0;
	}
	
	public MatrixIterator(T[][] matrix, int xStart, int yStart, int regionSize){
		this(matrix);
		this.x = xStart;
		this.y = yStart;
		this.xStart = xStart;
		this.xMax = xStart + regionSize;
		this.yMax = yStart + regionSize;
	}
	
	
	@Override
	public boolean hasNext() {
		return (this.x < this.xMax) && (this.y < this.yMax);
	}

	@Override
	public T next(){
		T retVal = this.matrix[y][x];
		this.x += 1;
	    if (this.x >= this.xMax){
	    	this.x = this.xStart;
	    	this.y += 1;
	    }
	    
	    return retVal;
	}

	@Override
	public void remove() {
		// do nothing
	}
} // end of class ImageIterator
