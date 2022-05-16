/* @ClassName: Processor.java
 * 
 * @Description: The user enters a date between 1990 and 2050 in the format of 'yyyy-MM-dd' to check whether it is valid.
 * If it is invalid, an error message is displayed;
 * Return '0-MM-dd' if the date is wrong;
 * If it is valid and the date is correct, the previous date is printed.
 * After analyzing, found that pure handwriting will produce a large number of nested if-else,
 * which is not beautiful and not conducive to maintenance and modification, 
 * so I chose the following packages to write the code.
 * 
 * @Version: V1.0
 * 
 * @Date: 5/14/2022
 * 
 * @Author: Bernstein
 */

package experiment2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Processor {
	// Increased maintainability by avoid constants
	private int floor = 1990;
	private int upper = 2050;
	private String today;
	private String pattern = "\\d{4}\\-\\d{1,2}\\-\\d{1,2}";
	
	public String judger(){
		// Firstly, check whether the basic format by Regular Expression.
		if(today.matches(pattern)) {
			String[] spilted = today.split("-");
			int year = Integer.parseInt(spilted[0]);
			// Secondly, check if it is within the range (If not so, we don't need to take up other space).
			if(floor <= year && year <= upper) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// Put variable declarations outside the try-catch block as possible.
				Date date;
				try {
					// If you want to validate dates loosely, please delete the following line of code.
					sdf.setLenient(false);
					// Thirdly, check whether the date format is normal by parse() surrounded by try-catch block. If not, return a string with the format required by the experiment.
					date = sdf.parse(today);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.DAY_OF_MONTH, -1);  
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					return format.format(calendar.getTime());
				}
				catch (ParseException e) {
					return "0-" + spilted[1] + "-" + spilted[2];
				}
			}
			else {
				return "0-" + spilted[1] + "-" + spilted[2];
			}
		}
		else {
			return "Wrong input pattern!";
		}
	}
	
	// getter/setter method
	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
		
	}
	
	public static void main(String[] args) throws ParseException {
		// Following while statement is just for test.
		while(true){
			Scanner sc = new Scanner(System.in);
			Processor processor= new Processor();
			processor.setToday(sc.nextLine());
			System.out.println(processor.judger());
		}
	}
}
