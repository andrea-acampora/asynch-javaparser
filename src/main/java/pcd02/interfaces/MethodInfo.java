package pcd02.interfaces;

import pcd02.interfaces.ClassReport;

public interface MethodInfo {

	String getName();
	int getSrcBeginLine();
	int getEndBeginLine();
	ClassReport getParent();
		
}
