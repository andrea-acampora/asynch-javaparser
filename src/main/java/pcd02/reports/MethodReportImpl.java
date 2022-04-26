package pcd02.reports;

import pcd02.interfaces.ClassReport;
import pcd02.interfaces.MethodReport;

public class MethodReportImpl implements MethodReport {

    private String name;
    private String type;
    private int srcBeginLine;
    private int srcEndLine;
    private ClassReport parent;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSrcBeginLine() {
        return this.srcBeginLine;
    }

    @Override
    public int getSrcEndLine() {
        return this.srcEndLine;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public ClassReport getParent() {
        return this.parent;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSrcBeginLine(int srcBeginLine) {
        this.srcBeginLine = srcBeginLine;
    }

    @Override
    public void setSrcEndLine(int srcEndLine) {
        this.srcEndLine = srcEndLine;
    }

    @Override
    public void setParent(final ClassReport report) {
        this.parent = report;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MethodReportImpl{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", srcBeginLine=" + srcBeginLine +
                ", srcEndLine=" + srcEndLine +
                ", parent=" + parent +
                '}';
    }
}
