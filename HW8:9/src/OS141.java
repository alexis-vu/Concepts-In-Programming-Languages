import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javafx.stage.Stage;

public class OS141 {
	protected int numUsers;
	protected int numDisks;
	protected int numPrinters;
	protected Disk disks[];
	protected Printer printers[];
	protected DiskManager diskManager;
	protected PrinterManager printerManager;
	protected Hashtable <String, File> userFiles;
	protected UserThread users[];
	
	public OS141(String[] args, OS141gui gui) throws IOException {
		// configure the arguments
		this.configure(args);
		this.configureFiles();
		
		// allocate memory
		this.disks = new Disk[this.numDisks];
		this.printers = new Printer[this.numPrinters];
		this.users = new UserThread[this.numUsers];
		
		// instantiate disks
		for (int i = 0; i < this.numDisks; ++i) {
			this.disks[i] = new Disk(i + 1);
		}
		
		// create disk manager
		this.diskManager = new DiskManager(this.disks);
		
		// instantiate printers
		for (int i = 0; i < this.numPrinters; ++i) {
			this.printers[i] = new Printer(i + 1);
		}
		
		// create printer manager
		this.printerManager = new PrinterManager(this.printers);
		
		// instantiate users and user files
		for (int i = 1; i <= numUsers; ++i) {
			this.users[i - 1] = new UserThread(i, this.userFiles.get("USER" + i), this.diskManager, this.printerManager, gui);
			this.users[i - 1].start();
		}
	}
	
	public void configureFiles() {
		String dir = System.getProperty("user.dir");
		File files = new File(dir + "/inputs");
		this.userFiles = new Hashtable<String, File>();
		
		for (File file : files.listFiles()) {
			userFiles.put(file.getName(), file);
		}
	}
	
	public void configure(String args[]) {
		this.numUsers = Math.abs(Integer.parseInt(args[0]));
		this.numDisks = Math.abs(Integer.parseInt(args[numUsers + 1]));
		this.numPrinters = Math.abs(Integer.parseInt(args[numUsers + 2]));
	}
}
