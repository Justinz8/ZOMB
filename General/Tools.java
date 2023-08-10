package General;

public class Tools {
    public static int HEIGHT=900, WIDTH=900;
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
}
