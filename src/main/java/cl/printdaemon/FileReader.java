package cl.printdaemon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * wmic printjob get jobid, document, jobstatus, owner /format:csv 1> print_table_part.txt 
 * 
 * @author jurzua
 *
 */
public class FileReader {

	public static void main(String[] args){
		
		try {
			File file = new File("/home/jurzua/Projects/IANUS/workspace/print-daemon/data/test.txt");
			//FileInputStream fstream = new FileInputStream(file, "utf8");
			BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF8"));

			String strLine;

			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				if(StringUtils.isNotEmpty(strLine) && !strLine.startsWith("Node,Document,JobId,JobStatus,Owner")){
					processLine(strLine);
				}
			}

			//Close the input stream
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void processLine(String line){
		String[] array = line.split(",");
		System.out.println(array.length);
		
		for(String item : array){
			System.out.println(item);
		}
	}
}
