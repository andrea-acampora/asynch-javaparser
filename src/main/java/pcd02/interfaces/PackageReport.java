package pcd02.interfaces;

import java.util.List;
import java.util.Optional;

public interface PackageReport {

	String getFullPackageName();

	List<InterfaceReport> getInterfaceInfo();

	List<ClassReport> getClassInfo();

	Optional<ClassReport> getMainClass();

	void setFullPackageName(String fullPackageName);

	void addInterfaceInfo(InterfaceReport interfaceReport);

	void addClassInfo(ClassReport classReport);
	
}
