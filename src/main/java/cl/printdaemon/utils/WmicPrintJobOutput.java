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
	
	/**
	 * array.length=7	Processing= Node,Document,JobId,JobStatus,Name,Owner,TotalPages
		array.length=8	Processing= DESKTOP-EREDE67,Microsoft Word - Informe Trabajo de Titulo.docx,16,Trabajos en cola,Microsoft Print to PDF, 16,Ricardo,2

	 * @throws IOException
	 */
	private void load() throws IOException{
		if(StringUtils.isEmpty(this.errorOutput)){
			String line=null;
			BufferedReader bufReader = new BufferedReader(new StringReader(this.standardOutput));
			
			while( (line=bufReader.readLine()) != null ) {
				if(StringUtils.isNotEmpty(line) && !line.startsWith("Node,Document,JobId,JobStatus,Name,Owner,TotalPages")){
					try {
						PrintJob job = new PrintJob(line);
						printJobList.add(job);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}	
		}else{
			System.out.println("Problem executing wmic: " + this.errorOutput);
		}
	}
	
	public List<PrintJob> getPrintJobList(){
		return printJobList;
	}
}
