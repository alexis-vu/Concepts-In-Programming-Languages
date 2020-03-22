import java.util.Hashtable;

public class DirectoryManager {
	private Hashtable<String, FileInfo> T = new Hashtable<String, FileInfo>();
	
	public void enter(String key, FileInfo file) {
		T.put(key.toString(), file);
	}
	
	public FileInfo lookup(String key) {
		return T.get(key);
	}
}
