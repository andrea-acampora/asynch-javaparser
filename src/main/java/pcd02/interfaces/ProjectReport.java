package pcd02.interfaces;

import java.util.List;

public interface ProjectReport {

	ClassReport getMainClass();
	
	List<PackageReport> getAllPackages();
	
}
