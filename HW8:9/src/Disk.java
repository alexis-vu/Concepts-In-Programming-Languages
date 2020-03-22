import javafx.application.Platform;

public class Disk {
	protected int id;
	protected static final int NUM_SECTORS = 1024;
	protected StringBuffer sectors[];
	protected int nextFree;
	
	/**
	 * Instantiate Disk object with specified capacity
	 * @param capacity - capacity to initialize
	 * 
	 */
	public Disk(int id) {
		this.id = id;
		this.sectors = new StringBuffer[NUM_SECTORS];
		
		for (int i = 0; i < NUM_SECTORS; ++i) {
			sectors[i] = new StringBuffer();
		}
		
		this.nextFree = 0;
	}

	/**
	 * Write data to specified sector
	 * @param sector - sector to write to
	 * @param data - data to write
	 * @throws InterruptedException if sleep is interrupted
	 */
	public void write(int sector, StringBuffer data, OS141gui gui) throws InterruptedException {
		int sleepCount = 200;
		
		if (gui != null) 
			sleepCount = (int) Math.ceil((200 * gui.getSliderValue()));
		
		Thread.sleep(sleepCount);
		
		// ensure sector has enough room to hold new data
		if (data.capacity() > sectors[sector].capacity()) {
			sectors[sector].ensureCapacity(data.capacity());
		}
		
		// if the sector is empty, nothing to overwrite so we append
		if (sectors[sector].length() == 0) {
			sectors[sector].append(data);
		}
		// otherwise we overwrite the entire sector
		else {
			int sectorEnd = sectors[sector - 1].capacity();
			sectors[sector].replace(0, sectorEnd, data.toString());
		}
		
		if (gui != null) {
			Disk d = this;
			Platform.runLater(new Runnable() {
				public void run() {
					switch(id) {
					case(1):
						gui.disk1TA.setText(d.toString());
						break;
					case(2):
						gui.disk2TA.setText(d.toString());
						break;
					default:
						break;
				}
				}
			});
		}
	}
	
	/**
	 * Reads data at sector
	 * @param sector - sector to read from
	 * @return specified sector as StringBuffer
	 * @throws InterruptedException if sleep is interrupted
	 */
	public StringBuffer read(int sector, OS141gui gui) throws InterruptedException {
		int sleepCount = 200;
		
		if (gui != null)
			sleepCount = (int) Math.ceil((200 * gui.getSliderValue()));
		
		Thread.sleep(sleepCount);
		return sectors[sector];
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < this.sectors.length; ++i) {
			sb.append("Sector " + i + ": " + this.sectors[i] + "\n");
		}
		
		return sb.toString();
	}
}
