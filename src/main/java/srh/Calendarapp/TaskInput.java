package srh.Calendarapp;

import java.util.Scanner;

public class TaskInput {
	private int total;
	private int occurance; 
	private float percentage;
	private String task;
	Scanner input = new Scanner(System.in);

	public void estimate() {

		System.out.println("Enter your task:");       
		String task= input.nextLine();


		System.out.println("Enter the max occurance per day");       
		total = input.nextInt();
		if(total<0)
		{
			System.out.println("invalid input");

		}

		System.out.println("Enter the number of completed task per day ");
		occurance = input.nextInt();
		if(occurance<=0)
		{
			System.out.println("invalid input");

		}

		percentage = (occurance * 100/ total);

		System.out.println("The percentage task output = " + percentage + " %");

	}
}
