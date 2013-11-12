package entropy;

import java.util.Iterator;

public class Matrix<T> implements Iterable<T> {
	protected T[][] matrix;
	
	
	
	class Region implements Iterable<T>{

		T[][] matrix;
		int x;
		int y;
		int size;
		
		public Region(T[][] matrix, int x, int y, int size){
			this.matrix = matrix;
			this.x = x;
			this.y = y;
			this.size = size;
		}
		
		@Override
		public Iterator<T> iterator() {
			// TODO Auto-generated method stub
			return new MatrixIterator<T>(this.matrix, this.x, this.y, this.size);
		}
		
	}
	
	
	class Column implements Iterable<T>{
		
		T[][] matrix;
		int x;
		int y;
		int length;
		
		public Column(T[][] matrix, int x, int y, int length) {
			this.matrix = matrix;
			this.x = x;
			this.y = y;
			this.length = length;
		}

		@Override
		public Iterator<T> iterator() {
			// TODO Auto-generated method stub
			return new ColumnIterator<T>(this.matrix, this.x, this.y, this.length);
		}
		
	}
	
	
	
	@Override
	public Iterator<T> iterator() {
		return new MatrixIterator<T>(this.matrix);
	}

	public Iterable<T> getRegion(int x, int y, int size){
		return new Region(this.matrix, x, y, size);
	}
	
	
	public Iterable<T> getColumn(int x, int y, int length){
		return new Column(this.matrix, x, y, length);
	}
	
	public int lenY(){
		return this.matrix.length;
	}
	
	public int lenX(){
		return this.matrix[0].length;
	}
	
	public int numElements() {
		return lenX() * lenY();
	}
	
	public T get(int x, int y){
		return this.matrix[y][x];
	}
}
