import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.application.Platform;

public class UserThread extends Thread{
	protected int id;
	protected File userFile;
	protected StringBuffer buffer;
	protected DiskManager diskManager;
	protected PrinterManager printerManager;
	protected OS141gui gui;


	public UserThread(int id, File userFile, DiskManager diskManager, PrinterManager printerManager, OS141gui gui) {
		this.id = id;
		this.userFile = userFile;
		this.buffer = new StringBuffer();
		this.diskManager = diskManager;
		this.printerManager = printerManager;
		this.gui = gui;
	}

	/**
	 * Copies a buffer's content to another buffer.
	 * @param to - string buffer to overwrite
	 * @param from - string buffer to copy from
	 */ 
	public void copyBuffer(StringBuffer to, String from) {
		to.ensureCapacity(from.length());
		to.replace(0, to.capacity(), from);
	}

	public void processCommands() throws InterruptedException, IOException {
		RandomAccessFile file = new RandomAccessFile(this.userFile, "r");
		String commands[];

		while(file.getFilePointer() != file.length()) {
			// copy the buffer to check command
			copyBuffer(this.buffer, file.readLine());

			// split the commands
			commands = this.buffer.toString().split(" ");

			// execute command based on line
			if (commands[0].equalsIgnoreCase(".save")) {
				saveFile(commands[1], file);
			}
			else if (commands[0].equalsIgnoreCase(".print")) {
				printFile(commands[1]);
			}
		}
		file.close();
	}

	public void saveFile(String name, RandomAccessFile inputFile) throws InterruptedException, IOException {
		int diskNumber = this.diskManager.request();
		int offset = this.diskManager.getNextFreeSector(diskNumber);
		int numLines = 0;

		if (this.gui != null) {
			Platform.runLater(new Runnable() {
				public void run() {
					switch(id) {
					case(1):
						gui.user1TA.appendText("\nUser 1 saving to disk " + diskNumber + " ...");
					break;
					case(2):
						gui.user2TA.appendText("\nUser 2 saving to disk " + diskNumber + " ...");
					break;
					case(3):
						gui.user3TA.appendText("\nUser 3 saving to disk " + diskNumber + " ...");
					break;
					default:
						break;
					}	
				}
			});
		}

		// while our buffer doesn't equal end we write to the disk, keep this order
		while (!this.buffer.toString().equalsIgnoreCase(".end")) {
			this.diskManager.disks[diskNumber].write(offset + numLines, this.buffer, this.gui);
			++numLines;
			copyBuffer(this.buffer, inputFile.readLine());
		}

		// copy in .end
		this.diskManager.disks[diskNumber].write(offset + numLines, this.buffer, this.gui);
		++numLines;

		// enter the file's info in for mapping later
		this.diskManager.directoryManager.enter(name, new FileInfo(diskNumber, numLines, offset));

		// set the next free sector in the disk
		this.diskManager.setNextFreeSector(diskNumber, offset + numLines);

		// release the disk for another user to use
		this.diskManager.release(diskNumber);

		if (this.gui != null) {
			Platform.runLater(new Runnable() {
				public void run() {
					switch(id) {
					case(1):
						gui.user1TA.appendText("\nDone saving to disk " + diskNumber + "!");
						break;
					case(2):
						gui.user2TA.appendText("\nDone saving to disk " + diskNumber + "!");
						break;
					case(3):
						gui.user3TA.appendText("\nDone saving to disk " + diskNumber + "!");
						break;
					default:
						break;
					}
				}
			});
		}
	}

	public void printFile(String fileName) throws InterruptedException, IOException {
		PrintJobThread job = new PrintJobThread(this, fileName, this.gui);
		job.start();
	}

	public void run() {
		try {
			if (this.gui != null) {
				Platform.runLater(new Runnable() {
					public void run() {
						switch(id) {
						case(1):
							gui.user1TA.appendText("User " + id + " started..");
						break;
						case(2):
							gui.user2TA.appendText("User " + id + " started...");
						break;
						case(3):
							gui.user3TA.appendText("User " + id + " started...");
						break;
						default:
							break;
						}
					}
				});
			}
			this.processCommands();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}
