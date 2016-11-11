package cl.printdaemon.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class WmicPrintJobOutput extends CmdOutput{
	
	public WmicPrintJobOutput(Process pb) throws IOException{
		super(pb);
		load();
	}
	
	private void load() throws IOException{
		String line=null;
		BufferedReader bufReader = new BufferedReader(new StringReader(this.standardOutput));
		System.out.println("Processing standardOutput ...");
		while( (line=bufReader.readLine()) != null ) {
			System.out.println(line);
		}
	}
}
