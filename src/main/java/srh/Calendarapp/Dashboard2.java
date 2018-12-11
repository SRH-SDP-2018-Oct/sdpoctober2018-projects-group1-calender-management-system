package srh.Calendarapp;

import java.util.Scanner;
import java.util.Scanner;
public class Dashboard2 {
	int number;
	Scanner input = new Scanner (System.in);
	public void dash2() {
		
		System.out.println("Main Menu-----------");
		System.out.println("1:Create Event \n2:Estimate task \n3:Create Folder \n4:Delete Event \n5:Exit");
		number = input.nextInt();

		switch(number) {


		case 1: System.out.println("Create Event");
		System.out.println("--------------------"); 
		CreateEvent c = new CreateEvent();
		int eventId=c.create();


		Recursive r = new Recursive();
		r.add(eventId);        

		System.out.println ("Event Created on: "+ new  java.util.Date());

		
		SendInvite s = new SendInvite();
		s.send(eventId);

		Dashboard da = new Dashboard();
		da.dash();
		break;
		case 2: System.out.println("Estimate Task");
		System.out.println("--------------------"); 
		TaskInput t = new TaskInput();
		t.estimate();
		
		Dashboard ds = new Dashboard();
		ds.dash();
		break;
		case 3: System.out.println("Create Folder"); 
		System.out.println("--------------------"); 
		CreateFolder obj = new CreateFolder();
		obj.gotoFolder();
		
		Dashboard dh = new Dashboard();
		dh.dash();
		break;
		case 4: System.out.println("Delete Event");
		System.out.println("--------------------"); 
		DeleteEvent d = new DeleteEvent();
		d.delete();
		
		Dashboard dr = new Dashboard();
		dr.dash();
		break;
		
		case 5: System.out.println("Exited Successfully");
		System.exit(0);
		
		break;	 
		default:
	    System.out.println("Incorrect Option! Please select a valid number");
	    Dashboard dsh = new Dashboard();
		dsh.dash();
		}			
		
	}
	
}
