package domein;

//tag::codeExample[]
class Point {
	private int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class ColoredPoint extends Point {
	private static final int WHITE = 0, BLACK = 1;
	private int color;

	public ColoredPoint(int x, int y) {
		this(x, y, WHITE); // <1>
	}

	public ColoredPoint(int x, int y, int color) {
		super(x, y); // <2>
		this.color = color;
	}
}
//end::codeExample[]