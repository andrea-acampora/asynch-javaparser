package pcd02.interfaces;

import java.util.List;

public interface InterfaceReport {

    String getFullInterfaceName();

    String getSrcFullFileName();

    List<MethodInfo> getMethodsInfo();

}
