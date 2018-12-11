package srh.Calendarapp;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import org.apache.log4j.Logger;
import java.util.Date;


public class CreateEvent {

	private static String eventName;
	private static String eventDate;
	private static String eventDescription;
	private String eventLocation;
	private int eventPriority;
	String choice;

	Logger logg = Logger.getLogger(CreateEvent.class.getName());

	Scanner input = new Scanner (System.in);
	Scanner scan = new Scanner (System.in);

	Date localDate = new Date ();

	public int create() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/calender","root","root");
			Statement st = con.createStatement();

			do {
				System.out.println("Enter name of Event ");
				setEventName(input.nextLine());  
				input.nextLine();

				System.out.println("Date and Time of Event || **** Enter in this Format:yyyy/mm/dd HH:mm ****");
				eventDate= input.nextLine();


				try {
					SimpleDateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd HH:mm");

					Date sdate;

					sdate = sdf.parse(eventDate);


					if (sdate.compareTo(localDate)>=0) {
						System.out.println("Date Accepted");

					}else {
						System.out.println("!!! Please enter a current or upcoming date !!!");
					}
				}
				catch (Exception e) {
					logg.error ("!!! Enter the correct date and time format !!!");
				}

				System.out.println("Do you want to make a change ? Enter Yes to make change|No to continue");

				choice = input.next();

			} while (choice.equalsIgnoreCase("yes") );  

			System.out.println("Description"); 
			eventDescription = scan.nextLine(); 

			System.out.println("Location of Event ");   
			eventLocation = scan.nextLine();
			input.nextLine();

			System.out.println("Enter event priority|| ***** Enter 1:High| 2:Medium | 3:Low ");    
			eventPriority = input.nextInt();

			if (!(eventPriority>=1 && eventPriority<=3)) { 

				logg.error("WRONG INFORMATION ENTERED || !!! CHOOSE BETWEEN NUMBER 1-3 !!!"); 
			} 

			String sql="insert into event(name,date,description,location,priority) values('" + eventName + "','" + eventDate + "','"+eventDescription+ "','"+eventLocation+"','"+eventPriority+"')";
			st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs=st.getGeneratedKeys();
			if(rs.next()) {
				return rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 0;
	}


	public static String getEventName() {
		return eventName;
	}

	public static void setEventName(String eventName) {
		CreateEvent.eventName = eventName;
	}

	public static String getEventDescription() {
		return eventDescription;
	}

	public static void setEventDescription(String eventDescription) {
		CreateEvent.eventDescription = eventDescription;
	}

	public static String getEventDate() {
		return eventDate;
	}
	public static void setEventDate(String eventDate) {
		CreateEvent.eventDate = eventDate;
	}
}
