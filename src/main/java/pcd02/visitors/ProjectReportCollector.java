package pcd02.visitors;

import com.github.javaparser.ast.CompilationUnit;
import pcd02.interfaces.PackageReport;
import pcd02.interfaces.ProjectReport;
import pcd02.reports.PackageReportImpl;

import java.util.Optional;

public class ProjectReportCollector {

    PackageReportCollector packageReportCollector = new PackageReportCollector();

    public void visit(final CompilationUnit cu, final ProjectReport projectReport) {
        if (cu.getPackageDeclaration().isPresent() && projectReport.getAllPackages().stream().noneMatch(a -> a.getFullPackageName().equals(cu.getPackageDeclaration().get().getNameAsString()))) {
            PackageReport packageReport = new PackageReportImpl();
            packageReport.setFullPackageName(cu.getPackageDeclaration().get().getNameAsString());
            this.packageReportCollector.visit(cu, packageReport);
            projectReport.addPackageReport(packageReport);
        } else {
            Optional<PackageReport> packageReport = projectReport.getAllPackages().stream().filter(a -> a.getFullPackageName().equals(cu.getPackageDeclaration().get().getNameAsString())).findFirst();
            packageReport.ifPresent(report -> this.packageReportCollector.visit(cu, report));
        }
    }
}
