package pcd02.reports;

import pcd02.interfaces.InterfaceReport;

import java.util.LinkedList;
import java.util.List;

public class InterfaceReportImpl implements InterfaceReport {

    private String srcFullFileName;
    private String fullInterfaceName;
    private final List<String> methods;

    public InterfaceReportImpl() {
        this.methods = new LinkedList<>();
    }

    @Override
    public String getFullInterfaceName() {
        return this.fullInterfaceName;
    }

    @Override
    public String getSrcFullFileName() {
        return this.srcFullFileName;
    }

    @Override
    public List<String> getMethods() {
        return this.methods;
    }

    @Override
    public void addMethod(final String methodName) {
        this.methods.add(methodName);
    }

    @Override
    public void setFullInterfaceName(final String fullInterfaceName) {
        this.fullInterfaceName = fullInterfaceName;
    }

    @Override
    public void setSrcFullFileName(final String srcFullFileName) {
        this.srcFullFileName = srcFullFileName;
    }

}
