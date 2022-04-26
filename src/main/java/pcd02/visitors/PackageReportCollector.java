package pcd02.visitors;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import pcd02.interfaces.ClassReport;
import pcd02.interfaces.InterfaceReport;
import pcd02.interfaces.PackageReport;
import pcd02.reports.ClassReportImpl;
import pcd02.reports.InterfaceReportImpl;

public class PackageReportCollector extends VoidVisitorAdapter<PackageReport> {

    public void visit(PackageDeclaration pd, PackageReport report) {
        super.visit(pd, report);
        report.setFullPackageName(pd.getNameAsString());
    }

    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, PackageReport packageReport) {
        if (classOrInterfaceDeclaration.isInterface()) {
            InterfaceReportCollector interfaceReportCollector = new InterfaceReportCollector();
            InterfaceReport interfaceReport = new InterfaceReportImpl(classOrInterfaceDeclaration.getNameAsString());
            interfaceReportCollector.visit(classOrInterfaceDeclaration, interfaceReport);
            packageReport.addInterfaceInfo(interfaceReport);
        } else {
            ClassReportCollector classReportCollector = new ClassReportCollector();
            ClassReport classReport = new ClassReportImpl(classOrInterfaceDeclaration.getNameAsString());
            classReportCollector.visit(classOrInterfaceDeclaration, classReport);
            packageReport.addClassInfo(classReport);
        }
    }
}
