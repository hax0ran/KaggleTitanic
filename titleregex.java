import java.util.*;
import java.io.*;

public class titleregex {

    public static void main(String[] args) throws IOException {
        //text file, should be opening in default text editor
		//titleread.txt and testtitles.txt are two altered versions of the original test.csv and train.csv files; they have had all their spaces replaced with underscores ("_")
		//and their empty values with "?". In addition, testtitles.txt has had a "0" placed in all entries in the "Survived" column, which is changed to all "?"'s in this
		//program.
		List<String> result1 = readFile("C:\\Users\\M\\Desktop\\titleread.txt");
		List<String> result2 = readFile("C:\\Users\\M\\Desktop\\testtitles.txt");
    }
	
	public static List<String> readFile(String filename)
	{
	  List<String> records = new ArrayList<String>();
	  try
	  {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		BufferedWriter writer;
		if(filename.equals("C:\\Users\\M\\Desktop\\titleread.txt"))
			writer = new BufferedWriter(new FileWriter("train.arff"));
		else
			writer = new BufferedWriter(new FileWriter("test.arff"));
		writer.write("%PassengerId,Survived,Pclass,Name,Sex,Age,SibSp,Parch,Fare,Embarked\n"+
		"@RELATION train\n\n"+
		"@ATTRIBUTE PassengerId	numeric\n"+
		"@ATTRIBUTE Survived		{0,1}\n"+
		"@ATTRIBUTE Pclass		{1,2,3}\n"+
		"@ATTRIBUTE Name			{Col,Don,Dona,Rev,Mme,Mr,Ms,Dr,Lady,Mrs,Master,the_Countess,Major,Miss,Jonkheer,Mlle,Sir,Capt}\n"+
		"@ATTRIBUTE Sex			{male,female}\n"+
		"@ATTRIBUTE Age			numeric\n"+
		"@ATTRIBUTE SibSp		numeric\n"+
		"@ATTRIBUTE Parch		numeric\n"+
		"@ATTRIBUTE Fare			numeric\n"+"@ATTRIBUTE Embarked		{S,C,Q}\n\n"+
		"@DATA\n");
		//Was used to onvert names into titles; now commented out since the titles have been extracted as seen in the Name attribute above
		//Set<String> titles = new HashSet<>();
		while ((line = reader.readLine()) != null)
		{
		  String name = line.substring(line.indexOf('\"')+1,line.lastIndexOf('\"'));
		  String title = name.substring(name.indexOf(',')+2,name.indexOf('.'));
		  //titles.add(title);
		  int index = line.lastIndexOf(",");
		  //cabins.add(line.substring(line.lastIndexOf(",",index-1)+1,index));
		  //System.out.println(line.substring(line.lastIndexOf(",",index-1)+1,index));
		  if(filename.equals("C:\\Users\\M\\Desktop\\testtitles.txt")){
			  //System.out.println(index);
			  //System.out.println(line.substring(line.lastIndexOf(",",index-1)+1,index));
			  index = line.indexOf(",");
			  line = line.substring(0,index)+",?"+line.substring(index+2,line.length());
		  }
		  line = line.substring(0,line.indexOf(name)-1)+title+line.substring(line.indexOf(name)+name.length()+1,line.length())+"\n";
		  writer.write(line);
		}
		 /*BufferedWriter w2;
		if(filename.equals("titleread.txt"))
			w2 = new BufferedWriter(new FileWriter("traincabins.txt"));
		else
			w2 = new BufferedWriter(new FileWriter("testcabins.txt"));
		for(String s: cabins)
				w2.write(s+",");
		//for(String s: titles)
			//System.out.println(s);
		w2.close();*/
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