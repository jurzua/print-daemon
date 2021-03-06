package cl.printdaemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import cl.printdaemon.utils.PrintJob;
import cl.printdaemon.utils.WmicPrintJobOutput;

public class MainClass {

	public static String PRINT_MANAGER_LOCATION = "http://localhost:8080/print-management/printServlet?";
	
	public static void main(String[] args) throws InterruptedException{
		//wmic printjob get jobid, document, jobstatus, owner, name, totalpages
		//Node,Document,JobId,Name,TotalPages
		String[] cmd = {"wmic", "printjob", "get", "jobid,", "document,", "name,", "totalpages", "/format:csv"};
		
		while(true){
            Thread.sleep(800);
			try {
		    	System.out.println("Searching for printJob ...");
		    	//aquí ejecuto el proceso
		    	Process pb = Runtime.getRuntime().exec(cmd);
			    //aquí espero que el proceso este listo
		    	pb.waitFor();
		    	//cuando el proceso este listo, genero una lista de PrintJob desde la salida 
		    	//estandar del programa.
			    WmicPrintJobOutput output = new WmicPrintJobOutput(pb);
			    if(!output.getPrintJobList().isEmpty()){
			    	System.out.println(output.getPrintJobList().size() + " printJobs found");
			    	processPrintJobs(output.getPrintJobList());
			    }
			    //System.out.println();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * http://localhost:8080/print-management/printServlet?userName=jurzua&computer=PC01&printer=Lexon_01&document=tesis.docx
	 * 
	 * @param list
	 * @throws UnsupportedEncodingException 
	 */
	public static void processPrintJobs(List<PrintJob> list) throws UnsupportedEncodingException{
		String userName = System.getProperty("user.name");
		String machine = null;
		try {
			InetAddress localMachine = java.net.InetAddress.getLocalHost();
			machine = localMachine.getHostName();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String params01 = "userName=" + userName + "&computer=" + URLEncoder.encode(machine, "UTF-8");
		
		
		for(PrintJob job : list){
			try {
				//params01 = URLEncoder.encode(params01, "UTF-8");
				String urlString = PRINT_MANAGER_LOCATION + params01 + "&" + job.toParams();
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				
				int responseCode = conn.getResponseCode();
				System.out.println("\tSending 'GET' request to URL : " + urlString);
				System.out.println("\tResponse Code : " + responseCode);
				System.out.println("\tResponse text : " + getRequestResponse(conn));
				System.out.println();
				
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
	public static String getRequestResponse(HttpURLConnection conn) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}