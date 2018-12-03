package proj.dbconnctor;
import java.io.*;
import java.util.*;

public class DBPropertyLoader{
private static final String filepath="C:\\Users\\purur\\workspace\\TestProj\\src\\db.properties";
	
	private static Properties prop;
	static
	{
		prop=new Properties();
		try{
			File f=new File(filepath);
			FileInputStream fis=new FileInputStream(f);
			prop.load(fis);
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static Properties getProperties()
	{
		return prop;
		
	}
	
}