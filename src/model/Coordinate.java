package model;

public class Coordinate {

	public double x;
	public double y;


	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
		//3 is the number of decimal
		this.x = toPrecision(x,3);
		this.y = toPrecision(y, 3);

	}


	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}


	/**
	 * To convert number with only n decimal points
	 * @param x
	 * @param decimals
	 * @return
	 */
	public static double toPrecision(double x, int decimals) {
		int factor = (int) Math.pow(10, decimals);

		int buf= (int)(x*factor);
		x = (double)buf/factor;

		return x;
	}

}
