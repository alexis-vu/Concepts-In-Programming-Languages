public class FileInfo {
	protected int diskNumber;
	protected int fileLength;
	protected int startingSector;
	
	public FileInfo(int diskNumber, int fileLength, int startingSector) {
		this.diskNumber = diskNumber;
		this.fileLength = fileLength;
		this.startingSector = startingSector;
	}
}
