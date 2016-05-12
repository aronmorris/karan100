import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class ClosestPoints {

	public static void main(String[] args) {
		Tuple[] listOfPoints = {new Tuple(1,3),new Tuple(31,2), new Tuple(8, 5), new Tuple(8, 7),new Tuple(31,2), new Tuple(100, 2),new Tuple(6,3), new Tuple(2, 2)};
		HashMap<Double, ArrayList<Tuple>> ans = closestPair(sortX(listOfPoints), sortY(listOfPoints));
		System.out.printf(ans.toString());
	}
	
	/**
	 * Identifies closest points in O(nlogn) time, implements https://www.rosettacode.org/wiki/Closest-pair_problem algorithm
	 * 
	 * Implementation currently only sorts by Y, to fix in future
	 * @param xPoints sorted X ascending order
	 * @param yPoints sorted Y ascending order
	 * @return
	 */
	public static HashMap<Double, ArrayList<Tuple>> closestPair(Tuple[] xPoints, Tuple[] yPoints) {
		if (xPoints.length <= 3) {
			return bruteClosestPair(xPoints);
		}
		else {
			ArrayList<Tuple> xLeft = new ArrayList<Tuple>();
			ArrayList<Tuple> xRight = new ArrayList<Tuple>();
			
			Tuple xMid = xPoints[xPoints.length / 2];
			
			//populate arraylists
			for (int i = 0; i < xPoints.length; i++) {
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
			for (int i = 0; i < yPoints.length; i++) {
				if (yPoints[i].X() <= xMid.X()) {
					yLeft.add(yPoints[i]);
				}
				else if (yPoints[i].X() > xMid.X()) {
					yRight.add(yPoints[i]);
				}
			}
		
			HashMap<Double, ArrayList<Tuple>> left = new HashMap<Double, ArrayList<Tuple>>();
			HashMap<Double, ArrayList<Tuple>> right = new HashMap<Double, ArrayList<Tuple>>();
			Tuple[] leftX = new Tuple[xLeft.size()], 
					leftY = new Tuple[yLeft.size()],
					rightX = new Tuple[xRight.size()],
					rightY = new Tuple[yRight.size()];
			//left-right recursion implemented right down to the bottom of the nth layer of hell, key "AAAAAA"
			left = closestPair(xLeft.toArray(leftX), yLeft.toArray(leftY));
			right = closestPair(xRight.toArray(rightX), yRight.toArray(rightY));
			
			HashMap<Double, ArrayList<Tuple>> dpMin = new HashMap<Double, ArrayList<Tuple>>();
			
			dpMin = right; //min is legit just right array
			
			double dMin = (double)left.keySet().toArray()[0];
			//int nS = 0;
			
			if (dMin < (double)right.keySet().toArray()[0]) {
				dpMin = left;
			}
			
			ArrayList<Tuple> ySmall = new ArrayList<Tuple>();
			for (int i = 0; i < yPoints.length; i++) {
				if (Math.abs(xMid.X() - yPoints[0].X()) < dMin) {
					ySmall.add(yPoints[i]);
				}
			}
			
			HashMap<Double, ArrayList<Tuple>> closest = dpMin;
			//actual comparison sorting of the smallest elements
			for (int i = 1; i < ySmall.size() - 1; i++) {
				int k = i + 1;
				while (k < ySmall.size() && (ySmall.get(k).Y() - ySmall.get(i).Y()) < dMin) {
					if (Math.abs(ySmall.get(k).Y() - ySmall.get(i).Y()) < (double) dpMin.keySet().toArray()[0]) {
						ArrayList<Tuple> val = new ArrayList<Tuple>();
						val.add(ySmall.get(k));
						val.add(ySmall.get(i));
						HashMap<Double, ArrayList<Tuple>> temp = new HashMap<Double, ArrayList<Tuple>>();
						temp.put(Math.abs((double)ySmall.get(k).Y() - ySmall.get(i).Y()), val);
						closest = temp;
					}
					k += 1;
				}
			}
			return closest;
		}
	}
	
	//brute forcing it
	public static HashMap<Double, ArrayList<Tuple>> bruteClosestPair(Tuple[] points) {
		
		ArrayList<Tuple> minPts = new ArrayList<Tuple>();
		
		double minDistance = Double.MAX_VALUE;
		
		if (points.length < 2) { //only one element, it'd be the closest to itself
			for (int i = 0; i < points.length; i++) {
				minPts.add(points[i]);
			}
		}
		else {
			minDistance = Math.sqrt(Math.abs(  Math.pow((points[0].X() - points[1].X()), 2) + Math.pow((points[0].Y() - points[1].Y()), 2)  ));
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
		
		HashMap<Double, ArrayList<Tuple>> retM = new HashMap<Double, ArrayList<Tuple>>();
		retM.put(minDistance, minPts);
		
		return retM;
		
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
