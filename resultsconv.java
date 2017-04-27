import java.util.*;
import java.io.*;
public class resultsconv{
	public static void main(String[] args){
		readFile("C:\\Users\\M\\Desktop\\resultss.csv");
	}
	
	public static List<String> readFile(String filename)
	{
	  List<String> records = new ArrayList<String>();
	  try
	  {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		BufferedWriter writer= new BufferedWriter(new FileWriter("results.csv"));
		writer.write("PassengerId,Survived\n");
		while ((line = reader.readLine()) != null)
		{
		  writer.write((Integer.parseInt(line.substring(0,line.indexOf(",")))+891)+","+line.substring(line.indexOf(":",8)+1,line.indexOf(":",8)+2)+"\n");
		}
		reader.close();
		writer.close();
		return records;
	  }
	  catch (Exception e)
	  {
		System.err.format("Exception occurred trying to read '%s'.", filename);
		e.printStackTrace();
		return null;
	  }
	}
}
