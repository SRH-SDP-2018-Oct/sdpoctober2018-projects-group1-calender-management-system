package srh.Calendarapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DeleteEvent{

	java.sql.Statement stmt = null; 
	Scanner keyboard=new Scanner(System.in);
	Connection conn;
	private String folderName;
	Scanner name=new Scanner(System.in);
	int eventNumber;

	public void delete(){

		try {    
			Class.forName("com.mysql.cj.jdbc.Driver");      
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/calender","root","root"); 
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from folder_names");
			System.out.println("Event name(s)\n ");

			while (rs.next()) {
				String eventName = rs.getString("event_name");
				System.out.println("* "+eventName );
			}
			System.out.println("Enter the event name to delete" );
			String delete_event = name.nextLine();  
			PreparedStatement stmt=conn.prepareStatement("DELETE FROM folder_names WHERE event_name=?");
			stmt.setString(1, delete_event);
			stmt.executeUpdate();
			ResultSet res = stmt.executeQuery("select * from folder_names");
			System.out.println("Event name(s)\n ");
			while (res.next()) {
				String eventName = res.getString("event_name");
				System.out.println("* "+eventName );
			}
			System.out.println("Successful Deleted\n");  

		}               
		catch(Exception e)    
		{System.out.println(e);
		}    
	}

}