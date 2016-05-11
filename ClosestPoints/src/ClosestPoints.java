import java.util.ArrayList;
import java.util.Arrays;


public class ClosestPoints {

	public static void main(String[] args) {
		Tuple[] listOfPoints = {new Tuple(1,3),new Tuple(3,4),new Tuple(2,2),new Tuple(5,3)};
		ArrayList<Tuple> ans = closestPair(sortX(listOfPoints), sortY(listOfPoints));
		System.out.printf(ans.toString());
	}
	
	/**
	 * Identifies closest points in O(nlogn) time
	 * @param xPoints sorted X ascending order
	 * @param yPoints sorted Y ascending order
	 * @return
	 */
	public static ArrayList<Tuple> closestPair(Tuple[] xPoints, Tuple[] yPoints) {
		if (xPoints.length <= 3) {
			return bruteClosestPair(xPoints);
		}
		else {
			ArrayList<Tuple> xLeft = new ArrayList<Tuple>();
			ArrayList<Tuple> xRight = new ArrayList<Tuple>();
			
			Tuple xMid = xPoints[xPoints.length / 2];
			
			//populate arraylists
			for (int i = 0; i <= xPoints.length; i++) {
				if (i < xPoints.length / 2) {
					xLeft.add(xPoints[i]);
				}
				else if (i >= (xPoints.length / 2) + 1) {
					xRight.add(xPoints[i]);
				}
			}
			//repeat with Y lists and xMid value
			ArrayList<Tuple> yLeft = new ArrayList<Tuple>();
			ArrayList<Tuple> yRight = new ArrayList<Tuple>();
			
			//populate arraylists
			for (int i = 0; i <= yPoints.length; i++) {
				if (yPoints[i].X() <= xMid.X()) {
					yLeft.add(yPoints[i]);
				}
				else if (yPoints[i].X() > xMid.X()) {
					yRight.add(yPoints[i]);
				}
			}
		
			
			
		}
		return null;
	}
	
	//brute forcing it
	public static ArrayList<Tuple> bruteClosestPair(Tuple[] points) {
		
		ArrayList<Tuple> minPts = new ArrayList<Tuple>();
		
		if (points.length < 2) { //only one element, it'd be the closest to itself
			for (int i = 0; i < points.length; i++) {
				minPts.add(points[i]);
			}
		}
		else {
			double minDistance = Math.sqrt(Math.abs(  Math.pow((points[1].X() - points[2].X()), 2) + Math.pow((points[1].Y() - points[2].Y()), 2)  ));
			minPts.add(points[0]);
			minPts.add(points[1]);
			
			for (int i = 0; i < points.length; i++) {
				for (int j = i + 1; j < points.length; j++) {
					double testMin = Math.sqrt(Math.abs(  Math.pow((points[i].X() - points[j].X()), 2) + Math.pow((points[i].Y() - points[j].Y()), 2)  ));
					if (minDistance > testMin) {
						minDistance = testMin;
						minPts.set(0, points[i]);
						minPts.set(1, points[j]);
					}
				}
			}
		}
		return minPts;
		
	}
	//sorts in ascending order of X
	//yeah it's O(n^2), it's a crude thing for an short project
	public static Tuple[] sortX(Tuple[] array) {
		
		Tuple temp;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i].X() < array[j].X()) {
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
	
	public static Tuple[] sortY(Tuple[] array) {
		
		Tuple temp;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i].Y() < array[j].Y()) {
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}

}
