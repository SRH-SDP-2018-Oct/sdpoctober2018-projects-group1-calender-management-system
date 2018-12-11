package srh.Calendarapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Recursive {
	Logger logg = Logger.getLogger(Recursive.class.getName());

	Date localDate = new Date ();
	private static String eventOccurence;
	private static String newDate;
	private static String changeDate;
	Scanner input = new Scanner (System.in);

	public void add(int eventId) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/calender","root","root");
			Statement st = con.createStatement();
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();  
			Statement st3 = con.createStatement();  

			String name1 = CreateEvent.getEventName();	

			do {
				System.out.println("Is this Event recursive || Enter yes or no");
				eventOccurence = input.next();

				if (eventOccurence.equals("yes")) {
					System.out.println("Enter the next date.Enter in this Format:yyyy/mm/dd"); 
					newDate = input.next();

					try {
						SimpleDateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd");

						Date ndate;

						ndate = sdf.parse(newDate);


						if (ndate.compareTo(localDate)>=0) {
							System.out.println("Date Accepted");
							System.out.println(name1 + " event will re-occur on the entered date");

						}else {
							System.out.println("!!! Please enter a current or upcoming date !!!");
						}
					}
					catch (Exception e) {
						logg.error ("!!! Enter the correct date time format !!!");
					}


				}else {

					System.out.println("Event is not recursive");
				}     
				System.out.println("Do you want to change the date || Enter Yes or no");

				changeDate = input.next();
			} while (changeDate.equalsIgnoreCase("yes") );  

			String sql="insert into recur (eventoccurence,date2,eventid) values('" + eventOccurence+ "','" + newDate + "',"+ eventId +")";
			st.executeUpdate(sql);

			String test1= "select max(recursiveid) from recur;" ;
			String test2= "select max(eventid) from event;";
			int k = 0,j=0;
			ResultSet rs1 = st1.executeQuery(test1);
			while (rs1.next())
			{
				k = rs1.getInt(1);
			}

			ResultSet rs2 =  st2.executeQuery(test2);

			while (rs2.next())
			{
				j = rs2.getInt(1);
			}


			String sql1 = "update  calender.event  set event.recursiveid = "+k+" where event.eventid = "+j+" ";
			st3.executeUpdate(sql1);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

