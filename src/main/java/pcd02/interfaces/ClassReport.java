package pcd02.interfaces;

import java.util.List;

public interface ClassReport {

	/**
	 *
	 * @return the full name of the class.
	 */
	String getFullClassName();

	/**
	 *
	 * @return the path of this file.
	 */
	String getSrcFullFileName();

	/**
	 *
	 * @return the reports of all methods inside the class.
	 */
	List<MethodReport> getMethodsInfo();

	/**
	 *
	 * @return the reports of all fields inside the class.
	 */
	List<FieldReport> getFieldsInfo();

	void setFullClassName(String fullFileName);

	void setSrcFullFileName(String srcFullFilesName);

	void addMethodInfo(MethodReport methodInfo);

	void addFieldInfo(FieldReport fieldInfo);
}
