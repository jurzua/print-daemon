package cl.printdaemon.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Esta clase es la representacón de un trabajo de impresión, que es generado por la clase WmicPrintJobOutput.
 * 
 * Example output:
 * 
 * Node,Document,JobId,JobStatus,Owner
 * DESKTOP-EREDE67,Microsoft Word - Informe Trabajo de Titulo.docx,6,Trabajos en cola | Imprimiendo,Ricardo
 * 
 * @author Ricardo Urzua
 *
 */
public class PrintJob {

	private String node; 
	private String jobId; 
	private String document;
	private String jobStatus; 
	private String owner;
	private String printer;
	private String totalPages;
	
	/**
	 *  array.length=7	Processing= Node,Document,JobId,Name,TotalPages
		array.length=8	Processing= DESKTOP-EREDE67,Microsoft Word - Informe Trabajo de Titulo.docx,16,Trabajos en cola,Microsoft Print to PDF, 16,Ricardo,2

	 * @param line
	 */
	public PrintJob(String line){
		String[] array = line.split(",");
		System.out.println("array.length=" + array.length + "\tProcessing= " + line);
		
		this.node = array[0];
		this.document = array[1];
		this.jobId = array[2];
		this.printer = array[3];
		this.totalPages = array[5];
		//this.jobStatus = array[3];
		//this.owner = array[4];
		//this.printer = array[5];
		
	}
	
	public String toParams() throws UnsupportedEncodingException{
		return "printer=" + URLEncoder.encode(printer, "UTF-8") + 
				"&document=" + URLEncoder.encode(document, "UTF-8") + 
				"&jobId=" + jobId + 
				"&pagesNumber=" + totalPages;
	}
	
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString(){
		return "PrintJob [jobId="+ jobId +", document="+ document +", owner=" + owner + "]";
	}
}
