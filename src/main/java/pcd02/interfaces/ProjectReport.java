package pcd02.interfaces;

import java.util.List;
import java.util.Optional;

public interface ProjectReport {

	Optional<ClassReport> getMainClass();
	
	List<PackageReport> getAllPackages();

	void addPackageReport(PackageReport packageReport);

}
