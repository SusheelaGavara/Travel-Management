package proj.main.report;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import proj.dbconnctor.DBPropertyLoader;

public class Report {
	public Connection con;
	public Report()
	{
	Properties prop=null;
	prop= DBPropertyLoader.getProperties();
	try
	{
	Class.forName(prop.getProperty("driverClass"));
	con=DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"));
	}catch(Exception e)
	{
		System.out.println(e);
		
	}
	}
	  public void reportGen()
	  {
		  String sqlQuery="SELECT * FROM ticket WHERE   ticket_date >= '2017-05-01' AND ticket_date   <= '2017-06-01'";
		JasperReportBuilder report = DynamicReports.report();//a new report
		report
		  .columns(
		      Columns.column("TICKET NO.", "ticket_no", DataTypes.integerType()),
		      Columns.column("USER ID", "user_id", DataTypes.integerType()),
		      Columns.column("Package ID", "package_id", DataTypes.integerType()),
		      Columns.column("Date", "ticket_date", DataTypes.dateType()))
		  .title(//title of the report
		      Components.text("No. OF BOOKED TRips in a MONTH")
			  .setHorizontalAlignment(HorizontalAlignment.CENTER))
			  .pageFooter(Components.pageXofY())//show page number on the page footer
			  .setDataSource(sqlQuery, con);

		try {
	                //show the report
			report.show();

	                //export the report to a pdf file
			report.toPdf(new FileOutputStream("C:\\Users\\purur\\Documents\\report.pdf"));
		} catch (DRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	  }
	}