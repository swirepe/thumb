package entropy;

	import java.util.Iterator;

// this is still in development
public class ColumnIterator<T> implements Iterator<T>{
		
	protected T[][] matrix;
	protected int x = 0;
	protected int y = 0;
	
	protected int xStart;
	protected int length;
	protected int yMax;
	
	public ColumnIterator(T[][] matrix){
		this.matrix = matrix;
		this.yMax = matrix.length;

		this.xStart = 0;
	}
	
	public ColumnIterator(T[][] matrix, int x, int yStart, int length){
		this(matrix);
		this.x = xStart;
		this.y = yStart;
		this.yMax = yStart + length;
	}
	
	
	@Override
	public boolean hasNext() {
		return (this.y < this.length) && (this.y < this.yMax) && (y < this.matrix.length);
	}

	@Override
	public T next(){
		T retVal = this.matrix[y][x];
		this.y += 1;	    
	    return retVal;
	}

	@Override
	public void remove() {
		// do nothing
	}
		
} // end of class ColumnIterator


