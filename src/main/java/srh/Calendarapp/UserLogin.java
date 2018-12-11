package srh.Calendarapp;

import java.sql.*;
import java.util.Scanner;
import org.apache.log4j.Logger;


public class UserLogin  {

	private static  String username;
	private static  String password;
	static int number;
	static int account;

	static Scanner input = new Scanner (System.in);

	static Logger logg = Logger.getLogger(UserLogin.class.getName());

	public static void main( String[] args ) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/calender","root","root");
			Statement st = con.createStatement();

			System.out.println("*****CALENDER MANAGEMENT SYSTEM*****");

			System.out.println("Enter 1 Create Account\nEnter 2 log in");
			account= input.nextInt();

			if (account==1) {
				System.out.println("*****CALENDER MANAGEMENT SYSTEM*****");
				try {

					System.out.println("Please Enter Username");
					username = input.next();

					System.out.println("Please Enter Password");
					password = input.next();    

					System.out.println("Signed up successfully");
					System.out.println("---------------------");
					System.out.println("Please Re-Start the Application to Log in");
					String sql="insert into user(username,password) values('" + username + "','" + password + "')";
					st.executeUpdate(sql);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}        
	
			}else {
				try {
					System.out.println("*****CALENDER MANAGEMENT SYSTEM*****");

					System.out.println("Log in");
					System.out.println("Please Enter Username");
					username = input.next();

					System.out.println("Please Enter Password");
					password = input.next();    

					String stmt = "Select * from user Where username='" + username + "' and password='"+password+"'";
					Statement statement= con.createStatement();

					ResultSet rs = statement.executeQuery(stmt);
					if(rs.next())
					{
						System.out.println("Login Successfully");
						System.out.println("---------------------");
						System.out.println("Enter an option");
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
						Dashboard dor = new Dashboard();
						dor.dash();
						}

					}else {

						logg.error ("Incorrect Password!!");
					}

				}catch(Exception e){ System.out.println(e);}  
				 logg.error("Incorrect Credentials");
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
