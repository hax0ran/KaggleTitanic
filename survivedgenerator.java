//import java.awt.Desktop;
import java.util.*;
import java.io.*;

public class survivedgenerator {

    public static void main(String[] args) throws IOException {
        //text file, should be opening in default text editor
        //File file = new File("C:\Users\M\Desktop\test.csv");
		List<String> result = readFile("C:\\Users\\M\\Desktop\\test.csv");
		for(String s: result)
			System.out.println(s);
    }
	
	public static List<String> readFile(String filename)
	{
	  List<String> records = new ArrayList<String>();
	  try
	  {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		BufferedWriter writer = new BufferedWriter(new FileWriter("stest.csv"));
		while ((line = reader.readLine()) != null)
		{
		  int index = line.indexOf(",");
		  writer.write(line.substring(0,index)+",0"+line.substring(index,line.length())+"\n");
		  if(line.substring(line.length()-1,line.length()).equals(","))
			  writer.write(line+"_");
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