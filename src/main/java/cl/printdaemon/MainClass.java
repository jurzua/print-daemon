package cl.printdaemon;

import cl.printdaemon.utils.WmicPrintJobOutput;

public class MainClass {

	public static void main(String[] args){
		
		String[] cmd = {"wmic printjob get jobid, document, jobstatus"};
		
		try {
	    	
	    	Process pb = Runtime.getRuntime().exec(cmd);
		    pb.waitFor();
		    WmicPrintJobOutput output = new WmicPrintJobOutput(pb);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.exit(0);
	}
}
