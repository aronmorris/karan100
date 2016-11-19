import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;


public class RollAverageListener implements ActionListener {

	private final JTextField total;
	private final JTextField damageDice;
	private final JTextField[] mods;
	
	//regex pattern for various dice rolls (e.g. 1d6, 2d10+4, d20x4
	private String pattern = "(?<A>\\d*)d((?<B>\\d+)(?<math>(?<mult>[x\\/](?<C>\\d+))?(?<add>[+-](?<D>\\d+))?)?)?";
	
	private Pattern dicePattern;
	
	public RollAverageListener(JTextField totalField, JTextField damageDice, JTextField... modFields) {
		total = totalField;
		
		this.damageDice = damageDice;
		
		mods = modFields;
		
		dicePattern = Pattern.compile(pattern);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		int numberRolls = 1000; //rolls a die 1000 times
		
		int modSum = 0;
		
		for (JTextField txt : mods) {
			modSum += Integer.parseInt(txt.getText());
		}
		
		String rollTotal = Float.toString(averageDamage(numberRolls, modSum, damageDice));
		
		total.setText(rollTotal);
		
	}
	
	private float averageDamage(int numberOfRolls, int sumOfMods, JTextField monsterAttackDice) {
		
		//The lowest a d20 rolls is 1, highest is 20 (20+1 maxes the upper bound inclusive of 20)
		int dMin = 1, dMax = 21; 
		
		int averageDamage = 0;
		
		for (int i = 0; i < numberOfRolls; i++) {
			
			//gets a random int between 1 and 20, plus the average mod
			
			int dRoll = ThreadLocalRandom.current().nextInt(dMin, dMax);
			
			//A roll of 1 is always a miss regardless of modifiers
			if (dRoll == 1) {
				continue; 
			} 
			
			//greater than 20 from mods or a natural 20 score damage
			if (dRoll + sumOfMods >= 20 || dRoll == 20) {
				//adds to damage the damage roll from the monster's attack dice
				averageDamage += monsterDamageRoll(monsterAttackDice);
			}
			
		}
		
		//returns the rounded to 2 places average of the damage dealt
		return round(averageDamage / numberOfRolls);
		
	}
	
	private int monsterDamageRoll(JTextField monsterDice) {
		 Matcher m = dicePattern.matcher(monsterDice.getText());

		 int returnValue = 0;
	 
	    if (m.matches()) {
	        String groupA = m.group("A");
	        if (groupA == null || groupA == "") {
	            groupA = "1"; // default one roll
	        }

	        String groupB = m.group("B");
	        if (groupB == null) {
	            groupB = "6"; // default six-sided die
	        }

	        String groupC = m.group("C");
	        if (groupC == null) {
	            groupC = "1"; // default multiply or divide by 1
	        }

	        String groupD = m.group("D");
	        if (groupD == null) {
	            groupD = "0"; // default add or subtract 0
	        }

	        int rollCount = Integer.parseInt(groupA);
	        int dieFaceCount = Integer.parseInt(groupB);
	        int multiplierDivisor = Integer.parseInt(groupC);
	        int additional = Integer.parseInt(groupD);

	        String groupMath = m.group("math");
	        if (groupMath != null && groupMath.isEmpty()) {
	            groupMath = null;
	        }
	        String groupAdd = m.group("add");
	        String groupMult = m.group("mult");
	        
	        int multiplier; 
	        int divisor;
	        if (groupMult == null) {
	        	multiplier = 0;
	        	divisor = 0;
	        } else {
	        	multiplier = groupMult.contains("x") ? multiplierDivisor : 0;
	 	        divisor = groupMult.contains("/") ? multiplierDivisor : 0;
	        }
	        
	        returnValue = rollResult(rollCount, dieFaceCount, multiplier, divisor, additional);
	        
	    }
	    
	    return returnValue;
	    
	}
	
	private int rollResult(int rolls, int max, int multiplier, int divisor, int additional) {
		
		int result = 0;
		
		for (int i = 0; i < rolls; i++) {
			
			int temp = ThreadLocalRandom.current().nextInt(1, max + 1);
			
			if (multiplier > 0) {
				temp *= multiplier;
			} else if (divisor > 0) {
				temp /= divisor;
			}
			
			temp += additional;
			
			result += temp;
			
		}
		
		return result;
		
	}
	
	//rounds to 2 decimal places
	private float round(float input) {
		BigDecimal bd = new BigDecimal(Float.toString(input));
		
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return bd.floatValue();
	}
	
}
