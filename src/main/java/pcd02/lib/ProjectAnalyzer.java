package pcd02.lib;

import io.vertx.core.Future;
import pcd02.interfaces.*;

import java.io.FileNotFoundException;

public interface ProjectAnalyzer {

	/**
	 * Async method to retrieve the report about a specific class,
	 * given the full path of the class source file
	 * 
	 * @param srcClassPath The path of the class to parse.
	 * @return a Future with the report of the class.
	 */
	Future<ClassReport> getClassReport(String srcClassPath) throws FileNotFoundException;

	/**
	 * Async method to retrieve the report about a specific interface,
	 * given the full path of the interface source file
	 *
	 * @param srcInterfacePath The path of the interface to parse.
	 * @return a Future with the report of the interface.
	 */
	Future<InterfaceReport> getInterfaceReport(String srcInterfacePath) throws FileNotFoundException;

	/**
	 * Async method to retrieve the report about a package,
	 * given the full path of the package folder
	 * 
	 * @param srcPackagePath The path of the package to parse.
	 * @return a Future with the report of the all package.
	 */
	Future<PackageReport> getPackageReport(String srcPackagePath);

	/**
	 * Async method to retrieve the report about a project
	 * given the full path of the project folder 
	 *
	 * @param srcProjectFolderPath The path of the project to parse.
	 * @return a Future with the report of the all project.
	 */
	Future<ProjectReport> getProjectReport(String srcProjectFolderPath);
	
	/**
	 * Async function that analyze a project given the full path of the project folder,
	 * executing the callback each time a project element is found 
	 * 
	 * @param srcProjectFolderName The path of the folder of the project.
	 * @param topicAddress The address of the topic.
	 */
	void analyzeProject(String srcProjectFolderName, String topicAddress);
}
