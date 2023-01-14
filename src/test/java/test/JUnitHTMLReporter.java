package test;

import static org.junit.Assert.*;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class JUnitHTMLReporter {
	
	static File junit_report;
	static BufferedWriter jUnit_writer;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		String junit_reportfile = "C:\\Users\\CT181LL\\eclipse-workspace\\JUnitProject\\JUNITREPORT.html";
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = new Date();
		junit_report = new File(junit_reportfile);
		jUnit_writer = new BufferedWriter(new FileWriter(junit_report, true));
		jUnit_writer.write("<html><body>");
		jUnit_writer.write("<h1> Test Execution Summary " + df.format(date) + "</h1>");

	}
	@AfterClass
	public static void tearDownAfterClass() throws IOException
	{
		jUnit_writer.write("</body></html>");
		jUnit_writer.close();
		Desktop.getDesktop().browse(junit_report.toURI());
	}
	
	@Rule
	public TestRule watchman = new TestWatcher() {
		@Override
		public Statement apply(Statement base, Description description) {
			return super.apply(base, description);
			
			}
			
		@Override
		public void succeeded(Description description) {
				try
				{
					jUnit_writer.write(description.getDisplayName() + "Success");
					jUnit_writer.write("<br>");
				}
				catch(Exception e1)
				{
					System.out.println(e1.getMessage());
				}
				
			}
		
		@Override
		public void failed(Throwable e, Description description) {
			try
			{
				jUnit_writer.write(description.getDisplayName() + " " + e.getClass().getSimpleName());
				jUnit_writer.write("<br>");
			}
			catch(Exception e2)
			{
				System.out.println(e2.getMessage());
			}
		}
			
	};
	
	}
