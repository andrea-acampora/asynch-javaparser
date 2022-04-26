package pcd02.visitors;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import pcd02.interfaces.PackageReport;
import pcd02.interfaces.ProjectReport;
import pcd02.reports.PackageReportImpl;

public class ProjectReportCollector extends VoidVisitorAdapter<ProjectReport> {

    @Override
    public void visit(PackageDeclaration pd, ProjectReport projectReport) {
        PackageReport packageReport = new PackageReportImpl();
        PackageReportCollector packageReportCollector = new PackageReportCollector();
        packageReportCollector.visit(pd, packageReport);
        projectReport.addPackageReport(packageReport);
    }


}
