package cl.printdaemon;

import cl.printdaemon.utils.WmicPrintJobOutput;

public class MainClass {

	public static void main(String[] args) throws InterruptedException{
		
		String[] cmd = {"wmic", "printjob", "get"};
		
		while(true){
            Thread.sleep(500);
			try {
		    	System.out.println("*");
		    	Process pb = Runtime.getRuntime().exec(cmd);
			    pb.waitFor();
			    WmicPrintJobOutput output = new WmicPrintJobOutput(pb);
			    
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
