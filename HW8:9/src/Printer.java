import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Printer {
	protected int index;
	protected String fileName;
	protected File file;
	protected StringBuffer content;
	
	public Printer(int index) throws IOException {
		this.index = index;
		this.fileName = "PRINTER" + index;
		this.file = new File(this.fileName);
		this.content = new StringBuffer();
	}

	/**
	 * Prints data to PRINTER file
	 * @param data - data to print
	 * @throws InterruptedException if sleep is interrupted
	 * @throws IOException if writing error
	 */
	public void print(StringBuffer data, OS141gui gui) throws InterruptedException, IOException {
		int sleepCount = 2750;
		if (gui != null)
			sleepCount = (int) Math.ceil(2750 * gui.getSliderValue());
		
		//Thread.sleep(sleepCount);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true));
		writer.append(data.toString() + "\n");		
		
		if (gui != null) {
			content.append(data.toString() + "\n");
			
			switch(index) {
			case(1):
				gui.printer1TA.setText(this.content.toString());
				break;
			case(2):
				gui.printer2TA.setText(this.content.toString());
				break;
			case(3):
				gui.printer3TA.setText(this.content.toString());
				break;
			default:
				break;
			}
		}
		
		writer.close();
	}
}
