package pcd02.reports;

import pcd02.interfaces.ClassReport;
import pcd02.interfaces.InterfaceReport;
import pcd02.interfaces.PackageReport;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PackageReportImpl implements PackageReport {

    private String fullPackageName;
    private String packagePath;
    private final List<InterfaceReport> interfaceReports;
    private final List<ClassReport> classReports;
    private Optional<ClassReport> mainClass;

    public PackageReportImpl() {
        this.interfaceReports = new LinkedList<>();
        this.classReports = new LinkedList<>();
    }

    @Override
    public String getFullPackageName() {
        return this.fullPackageName;
    }

    @Override
    public String getFullPackagePath() {
        return this.packagePath;
    }

    @Override
    public List<InterfaceReport> getInterfaceInfo() {
        return this.interfaceReports;
    }

    @Override
    public List<ClassReport> getClassInfo() {
        return this.classReports;
    }

    @Override
    public Optional<ClassReport> getMainClass() {
        return this.mainClass;
    }

    @Override
    public void setFullPackageName(final String fullPackageName) {
        this.fullPackageName = fullPackageName;
    }

    @Override
    public void addInterfaceInfo(final InterfaceReport interfaceReport) {
        this.interfaceReports.add(interfaceReport);
    }

    @Override
    public void addClassInfo(final ClassReport classReport) {
        this.classReports.add(classReport);
        if (classReport.getMethodsInfo().stream().anyMatch(a -> a.getName().equals("main"))) {
            this.mainClass = Optional.of(classReport);
        }
    }

    @Override
    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    @Override
    public void setMainClass(Optional<ClassReport> mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public String toString() {
        return this.fullPackageName;
    }
}
