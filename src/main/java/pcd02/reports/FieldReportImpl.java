package pcd02.reports;

import pcd02.interfaces.ClassReport;
import pcd02.interfaces.FieldReport;

public class FieldReportImpl implements FieldReport {

    private String name;
    private String type;
    private ClassReport parent;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getFieldType() {
        return this.type;
    }

    @Override
    public ClassReport getParent() {
        return this.parent;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public void setParent(final ClassReport parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "FieldReportImpl{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", parent=" + parent +
                '}';
    }
}
