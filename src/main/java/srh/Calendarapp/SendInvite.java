package srh.Calendarapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.log4j.Logger;

public class SendInvite extends CreateEvent{
	Logger logg = Logger.getLogger(SendInvite.class.getName());


	private String sendEvent;
	private String senderEmail; 
	private String senderPassword; 
	private String receiverEmail; 

	private String emailSubject = CreateEvent.getEventName();
	private String emailBody   =  CreateEvent.getEventDescription();


	Scanner input = new Scanner(System.in);

	public void send(int eventId) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/calender","root","root");
			Statement st = con.createStatement();
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();  
			Statement st3 = con.createStatement();  


			System.out.println("Do you want to share this Event || Enter yes or no");
			sendEvent = input.next();

			if (sendEvent.equals("yes")) {

				System.out.println("Enter your Email");
				senderEmail =input.next();

				System.out.println("Enter your Email Password");
				senderPassword =input.next();   

				System.out.println("Enter Recipient Email");
				receiverEmail =input.next();   

				Properties props = System.getProperties();
				String host = "smtp.gmail.com";

				props.put("mail.smtp.starttls.enable", "true");

				props.put("mail.smtp.ssl.trust", host);   
				props.put("mail.smtp.user", senderEmail);
				props.put("mail.smtp.password", senderPassword);  
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.auth", "true");


				Session session = Session.getDefaultInstance(props);
				MimeMessage message = new MimeMessage(session);

				try {    	    	
					message.setFrom(new InternetAddress(senderEmail));
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(receiverEmail));

					message.setSubject(emailSubject);
					message.setText(emailBody);

					Transport transport = session.getTransport("smtp");

					transport.connect(host, senderEmail,senderPassword);     
					transport.sendMessage(message, message.getAllRecipients());
					transport.close();
					System.out.println("Email sent successfully");

				}
				catch (Exception e) {
					logg.error ("!!Email was not sent!!");
				}
			}else {
				System.out.println("Event is not shared");

			}
			String sql="insert into share(senderemail,receiveremail,eventid) values('" + senderEmail+ "','" + receiverEmail + "',"+ eventId +")";
			st.executeUpdate(sql);    

			String test1= "select max(shareid) from share;" ;
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


			String sql1 = "update  calender.event set event.shareid = "+k+" where event.eventid = "+j+" ";
			st3.executeUpdate(sql1);

		} catch (ClassNotFoundException e) {
		     logg.error ("Incorrect variables");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

