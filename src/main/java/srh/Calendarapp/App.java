package srh.Calendarapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class App  {

	public static void main( String[] args ){
		try {
			ResultSet rs = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/calender","root","1234");
			Statement st = con.createStatement();

			rs = st.executeQuery("select Eventid,name,date,location,description,priority,shareid,recursiveid from event");  

			FastReportBuilder drb = new FastReportBuilder();
			DynamicReport dr = drb
					.addColumn("Event ID", "Eventid", String.class.getName(),30) 
					.addColumn("Event Name", "name", String.class.getName(),30)
					.addColumn("Event Date", "date", String.class.getName(),50)   
					.addColumn("Event Description", "description", String.class.getName(),50) 
					.addColumn("Event Location", "location", String.class.getName(),50) 
					.addColumn("Event priority", "priority", String.class.getName(),50) 
					.addColumn("Share ID", "shareid", String.class.getName(),50) 
					.addColumn("Recursive ID", "recursiveid", String.class.getName(),50) 					
					.setTitle("EVENTS DETAILS").setSubtitle("This report was generated at " + new Date())      
					.setPrintBackgroundOnOddRows(true).setUseFullPageWidth(true).build();
			JRResultSetDataSource resultsetdatasource = new JRResultSetDataSource(rs);
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), resultsetdatasource);
			JasperViewer.viewReport(jp); 
		}
		catch(Exception ex) {
			System.out.println(ex);

		}





	}
}
