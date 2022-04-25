package pcd02.interfaces;

import java.util.List;

public interface PackageReport {

	String getFullPackageName();
	
	String getSrcFullFileName();

	List<InterfaceReport> getInterfaceInfo();

	List<ClassReport> getClassInfo();
	
}
