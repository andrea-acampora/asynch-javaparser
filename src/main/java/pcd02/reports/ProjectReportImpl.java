package pcd02.reports;

import pcd02.interfaces.ClassReport;
import pcd02.interfaces.PackageReport;
import pcd02.interfaces.ProjectReport;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProjectReportImpl implements ProjectReport {

    private final List<PackageReport> packageReports;

    public ProjectReportImpl() {
        this.packageReports = new LinkedList<>();
    }

    @Override
    public Optional<ClassReport> getMainClass() {
        return this.packageReports.stream().filter(a -> a.getMainClass().isPresent()).findFirst().flatMap(PackageReport::getMainClass);
    }

    @Override
    public List<PackageReport> getAllPackages() {
        return this.packageReports;
    }

    @Override
    public void addPackageReport(final PackageReport packageReport) {
        this.packageReports.add(packageReport);
    }

}
