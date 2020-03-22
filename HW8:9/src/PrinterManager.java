public class PrinterManager extends ResourceManager {
	protected Printer[] printers;
	
	public PrinterManager(Printer[] printerList) {
		super(printerList.length);
		this.printers = printerList;
	}
}
