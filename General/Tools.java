package General;

public class Tools {
    public static int HEIGHT=720, WIDTH=1080;
	public static double amountOfTicks = 30;
    public static boolean inbounds(double lower, double upper, double l, double r) {
		if(lower<=l&&r<=upper) {
			return true;
		}
		return false;
	}
	    public static boolean inbounds(double lower, double upper, double l) {
		if(lower<=l&&l<=upper) {
			return true;
		}
		return false;
	}
	public static double clamp(double lower, double upper, double n){
		if(n<lower) return lower;
		if(n>upper) return upper;
		return n;
	}
}
