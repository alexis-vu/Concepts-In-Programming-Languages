import java.io.IOException;

import javafx.application.Platform;

public class PrintJobThread extends Thread {
	UserThread user;
	String fileName;
	OS141gui gui;
	
	public PrintJobThread(UserThread user, String fileName, OS141gui gui) {
		this.user = user;
		this.fileName = fileName;
		this.gui = gui;
	}
	
	public void run() {
		// find the file to print
		FileInfo info = user.diskManager.directoryManager.lookup(this.fileName);
		
		if (info != null) {
			try {
				// get info of file to read
				int start = info.startingSector;
				int disk = info.diskNumber;
				int printer = user.printerManager.request();
				StringBuffer sb = new StringBuffer();
				
				if (this.gui != null) {
					Platform.runLater(new Runnable() {
						public void run() {
							switch(user.id) {
							case(1):
								gui.user1TA.appendText("\nUser 1 printing to Printer " + (printer + 1) + "...");
								break;
							case(2):
								gui.user2TA.appendText("\nUser 2 printing to Printer " + (printer + 1) + "...");
								break;
							case(3):
								gui.user3TA.appendText("\nUser 3 printing to Printer " + (printer + 1) + "...");
								break;
							default:
								break;
							}
						}
					});
				}
				
				// read each sector of the file
				for (int i = 0; i < info.fileLength; ++i) {
					user.copyBuffer(sb, user.diskManager.disks[disk].read(start + i, this.gui).toString());
					user.printerManager.printers[printer].print(sb, gui);
				}
				
				// release the printer
				user.printerManager.release(printer);
				
				if (this.gui != null) {
					Platform.runLater(new Runnable() {
						public void run() {
							switch(user.id) {
							case(1):
								gui.user1TA.appendText("\nDone printing on Printer " + (printer + 1) + "!");
								break;
							case(2):
								gui.user2TA.appendText("\nDone printing on Printer " + (printer + 1) + "!");
								break;
							case(3):
								gui.user3TA.appendText("\nDone printing on Printer " + (printer + 1) + "!");
								break;
							default:
								break;
						}
						}
					});
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
