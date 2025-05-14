package domein;

//tag::codeExample[]
abstract class Dot { // <1>
    public int x = 1, y = 1;
    
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        alert();
    }
    
    public abstract void alert(); // <2>
}

abstract class ColoredDot extends Dot { // <3>
    public int color;
}

public class SimpleDot extends Dot { // <4>
	@Override
    public void alert() { }
}
//end::codeExample[]