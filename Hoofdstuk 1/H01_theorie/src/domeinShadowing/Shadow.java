package domeinShadowing;

//tag::codeExample[]
public class Shadow {
	private int i = 5; // <1>

	public static void main(String[] args) {
		Shadow shadow = new Shadow();
		
		shadow.foo();
	}
	
	public void foo() {
		int i = 7; // <2>
		
		System.out.println("Local i = " + i); // <3>
		System.out.println("Class attribute i = " + this.i); // <4>	
	}
}
//end::codeExample[]