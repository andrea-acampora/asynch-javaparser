package pcd02.lib;

import io.vertx.core.Future;
import pcd02.interfaces.*;

import java.io.FileNotFoundException;
import java.util.function.Consumer;

public interface ProjectAnalyzer {

	/**
	 * Async method to retrieve the report about a specific class,
	 * given the full path of the class source file
	 * 
	 * @param srcClassPath
	 * @return
	 */
	Future<ClassReport> getClassReport(String srcClassPath) throws FileNotFoundException;

	/**
	 * Async method to retrieve the report about a specific interface,
	 * given the full path of the interface source file
	 *
	 * @param srcInterfacePath
	 * @return
	 */
	Future<InterfaceReport> getInterfaceReport(String srcInterfacePath) throws FileNotFoundException;

	/**
	 * Async method to retrieve the report about a package,
	 * given the full path of the package folder
	 * 
	 * @param srcPackagePath
	 * @return
	 */
	Future<PackageReport> getPackageReport(String srcPackagePath);

	/**
	 * Async method to retrieve the report about a project
	 * given the full path of the project folder 
	 * 
	 * @param srcProjectFolderPath
	 * @return
	 */
	Future<ProjectReport> getProjectReport(String srcProjectFolderPath);
	
	/**
	 * Async function that analyze a project given the full path of the project folder,
	 * executing the callback each time a project element is found 
	 * 
	 * @param srcProjectFolderName
	 * @param callback
	 */
	void analyzeProject(String srcProjectFolderName, Consumer<ProjectElem> callback);
}
