package cl.printdaemon;

import cl.printdaemon.utils.WmicPrintJobOutput;

public class MainClass {

	public static void main(String[] args) throws InterruptedException{
		
		String username = System.getProperty("user.name");
		
		System.out.println(username);
		
		try {
			java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
			System.out.println("Hostname of local machine: " + localMachine.getHostName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String[] cmd = {"wmic", "printjob", "get"};
		/*
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
		}*/
	}
}
