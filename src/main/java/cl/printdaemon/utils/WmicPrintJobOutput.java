package cl.printdaemon.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class WmicPrintJobOutput extends CmdOutput{
	
	private List<PrintJob> printJobList = new ArrayList<PrintJob>();
	
	public WmicPrintJobOutput(Process pb) throws IOException{
		super(pb);
		load();
	}
	
	private void load() throws IOException{
		String line=null;
		BufferedReader bufReader = new BufferedReader(new StringReader(this.standardOutput));
		System.out.println(this.standardOutput);
		
		while( (line=bufReader.readLine()) != null ) {
			if(StringUtils.isNotEmpty(line) && !line.startsWith("Node,Document,JobId,JobStatus,Owner")){
				try {
					PrintJob job = new PrintJob(line);
					printJobList.add(job);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<PrintJob> getPrintJobList(){
		return printJobList;
	}
}
