package pcd02.interfaces;

import java.util.List;

public interface ClassReport {

	String getFullClassName();
	
	String getSrcFullFileName();

	List<MethodReport> getMethodsInfo();

	List<FieldReport> getFieldsInfo();

	void setFullClassName(String fullFileName);

	void setSrcFullFileName(String srcFullFilesName);

	void addMethodInfo(MethodReport methodInfo);

	void addFieldInfo(FieldReport fieldInfo);
}
