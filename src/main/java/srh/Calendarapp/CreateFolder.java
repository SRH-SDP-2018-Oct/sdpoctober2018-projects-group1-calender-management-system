package srh.Calendarapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CreateFolder {

	java.sql.Statement stmt = null; 
	Connection conn;
	private static String folderName;
	private static String foName;
	private static String taskName;
	Scanner in = new Scanner(System.in);
	int eventNumber;
	public void gotoFolder(){

		try {    

			Class.forName("com.mysql.cj.jdbc.Driver");      
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/calender","root","root"); 
			stmt = conn.createStatement();
			System.out.println("Enter \n1.Create your own Folder\n2.To Exit");
			eventNumber = in.nextInt();
			if(eventNumber == 1) {
				System.out.print("Enter your folder name \n");
				foName = in.next();
				System.out.println("Folder name :"+ " "+foName); 
				System.out.print("Enter \n1.Add event to this folder\n2. To show Events");
				eventNumber = in.nextInt();
				if(eventNumber == 1) {
					System.out.print("Enter your Event name \n");    
					taskName = in.next();
					PreparedStatement stmt3=conn.prepareStatement("insert into folder_names(f_name,event_name) values(?,?)");      
					stmt3.setString(1,foName);
					stmt3.setString(2,taskName);
					stmt3.execute();
					System.out.println("--------------------");  
					System.out.println(taskName + " Event is added to the Folder: "+ foName); 
					System.out.println("--------------------");  
					System.out.println("Folder Successfully Created");  
					System.out.println("--------------------\n");
					System.out.println("Bye..");
					System.out.println("--------------------\n");

				}
				else if(eventNumber == 2){
					ResultSet rs = stmt.executeQuery("select * from folder_names");
					System.out.println("Folder name    --> Event name \n");
					while (rs.next()) {
						String folderName = rs.getString("f_name");
						String eventName = rs.getString("event_name");
						System.out.println(folderName + " --> " +eventName );
					}

					System.out.println("Bye..");
				}     
			}              
		}               
		catch(Exception e)    
		{System.out.println(e);
		}    
	}	}

