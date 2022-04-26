package pcd02.interfaces;

public interface MethodReport {

	String getName();

	int getSrcBeginLine();

	int getSrcEndLine();

	String getType();

	ClassReport getParent();

	void setName(String name);

	void setSrcBeginLine(int srcBeginLine);

	void setSrcEndLine(int srcEndLine);

	void setParent(ClassReport report);

	void setType(String type);
		
}
