package pcd02.visitors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import pcd02.interfaces.ClassReport;
import pcd02.interfaces.InterfaceReport;
import pcd02.interfaces.PackageReport;
import pcd02.reports.ClassReportImpl;
import pcd02.reports.InterfaceReportImpl;

public class PackageReportCollector {

    public void visit(CompilationUnit cu, PackageReport packageReport) {
        if (cu.findFirst(ClassOrInterfaceDeclaration.class).get().isInterface()) {
            InterfaceReportCollector interfaceReportCollector = new InterfaceReportCollector();
            InterfaceReport interfaceReport = new InterfaceReportImpl();
            interfaceReport.setSrcFullFileName(cu.getPackageDeclaration().get().getNameAsString());
            interfaceReportCollector.visit(cu, interfaceReport);
            packageReport.addInterfaceInfo(interfaceReport);
        } else {
            ClassReportCollector classReportCollector = new ClassReportCollector();
            ClassReport classReport = new ClassReportImpl();
            classReport.setSrcFullFileName(cu.getPackageDeclaration().get().getNameAsString());
            classReportCollector.visit(cu, classReport);
            packageReport.addClassInfo(classReport);
        }
    }
}
