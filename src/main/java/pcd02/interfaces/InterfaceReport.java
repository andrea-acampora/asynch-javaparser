package pcd02.interfaces;

import java.util.List;

public interface InterfaceReport {

    String getFullInterfaceName();

    String getSrcFullFileName();

    List<String> getMethods();

    void addMethod(String methodName);

    void setFullInterfaceName(String fullInterfaceName);

    void setSrcFullFileName(String srcFullFileName);
}
