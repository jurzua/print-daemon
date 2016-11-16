package cl.printdaemon.utils;

/**
 * Example output:
 * 
 * Node,Document,JobId,JobStatus,Owner
 * DESKTOP-EREDE67,Microsoft Word - Informe Trabajo de Titulo.docx,6,Trabajos en cola | Imprimiendo,Ricardo
 * 
 * @author jurzua
 *
 */
public class PrintJob {

	private String node; 
	private String jobId; 
	private String document;
	private String jobStatus; 
	private String owner;
	
	public PrintJob(String line){
		String[] array = line.split(",");
		for(String item : array){
			System.out.println(item);
		}
		
		this.node = array[0];
		this.jobId = array[1];
		this.document = array[2];
		this.jobStatus = array[3];
		this.owner = array[4];
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
}
