package pcd02.interfaces;

public interface FieldReport {

	String getName();

	String getFieldType();
	
	ClassReport getParent();

	void setName(String name);

	void setType(String type);

	void setParent(ClassReport parent);
}
