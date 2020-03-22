public class DiskManager extends ResourceManager {
	protected DirectoryManager directoryManager = new DirectoryManager();
	protected Disk[] disks;
	
	
	public DiskManager(Disk[] diskList) {
		super(diskList.length);
		this.disks = diskList;
	}
	
	public int getNextFreeSector(int disk) {
		return this.disks[disk].nextFree;
	}
	
	public void setNextFreeSector(int disk, int sector) {
		this.disks[disk].nextFree = sector;
	}
}
