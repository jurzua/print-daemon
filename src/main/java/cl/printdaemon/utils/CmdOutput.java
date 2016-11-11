package cl.printdaemon.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * @author Ricardo Urzua
 *
 */
public class CmdOutput {
	
	public String standardOutput;
	public String errorOutput;
	public int exitValue;
	
	public CmdOutput(Process pb) throws IOException{
		this.standardOutput = loadStream(pb.getInputStream());
		this.errorOutput = loadStream(pb.getErrorStream());
		this.exitValue = pb.exitValue();
	}
	
	public String loadStream(InputStream in) throws IOException{
		 String line;
		 StringBuilder sb = new StringBuilder();
		 BufferedReader input = new BufferedReader(new InputStreamReader(in));
		 while ((line = input.readLine()) != null) {
		      sb.append(line + "\n");
		 }
		 input.close();
		 return sb.toString();
	}
	
}
