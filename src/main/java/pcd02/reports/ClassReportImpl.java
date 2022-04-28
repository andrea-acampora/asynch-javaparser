package pcd02.reports;

import pcd02.interfaces.ClassReport;
import pcd02.interfaces.FieldReport;
import pcd02.interfaces.MethodReport;

import java.util.LinkedList;
import java.util.List;

public class ClassReportImpl implements ClassReport {

    private String srcFullFileName;
    private String fullClassName;
    private final List<MethodReport> methodsInfo;
    private final List<FieldReport> fieldsInfo;

    public ClassReportImpl() {
        this.methodsInfo = new LinkedList<>();
        this.fieldsInfo = new LinkedList<>();
    }

    @Override
    public String getFullClassName() {
        return this.fullClassName;
    }

    @Override
    public String getSrcFullFileName() {
        return this.srcFullFileName;
    }

    @Override
    public List<MethodReport> getMethodsInfo() {
        return this.methodsInfo;
    }

    @Override
    public List<FieldReport> getFieldsInfo() {
        return this.fieldsInfo;
    }

    @Override
    public void setFullClassName(final String fullFileName) {
        this.fullClassName = fullFileName;
    }

    @Override
    public void setSrcFullFileName(final String srcFullFileName) {
        this.srcFullFileName = srcFullFileName;
    }

    @Override
    public void addMethodInfo(final MethodReport methodInfo) {
        this.methodsInfo.add(methodInfo);
    }

    @Override
    public void addFieldInfo(final FieldReport fieldInfo) {
        this.fieldsInfo.add(fieldInfo);
    }
}
