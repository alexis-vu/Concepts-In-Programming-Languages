public class ResourceManager {
	private boolean isFree[];
	
	public ResourceManager(int numItems) {
		isFree = new boolean[numItems];
		
		for (int i = 0; i < isFree.length; ++i) {
			isFree[i] = true;
		}
	}
	
	public synchronized int request() throws InterruptedException {
		while (true) {
			for (int i = 0; i < isFree.length; ++i) {
				if (isFree[i]) {
					isFree[i] = false;
					return i;
				}
			}
			
			this.wait(); // block until someone releases Resource
		}	
}
	
	public synchronized void release(int index) {
		isFree[index] = true;
		this.notify();
	}
}
