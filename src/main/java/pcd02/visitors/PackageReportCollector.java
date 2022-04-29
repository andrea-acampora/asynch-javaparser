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
        if (cu.findFirst(ClassOrInterfaceDeclaration.class).isPresent() && cu.findFirst(ClassOrInterfaceDeclaration.class).get().isInterface()) {
            InterfaceReportCollector interfaceReportCollector = new InterfaceReportCollector();
            InterfaceReport interfaceReport = new InterfaceReportImpl();
            interfaceReportCollector.visit(cu, interfaceReport);
            interfaceReport.setSrcFullFileName(packageReport.getFullPackagePath() + interfaceReport.getFullInterfaceName());
            packageReport.addInterfaceInfo(interfaceReport);
        } else {
            ClassReportCollector classReportCollector = new ClassReportCollector();
            ClassReport classReport = new ClassReportImpl();
            classReportCollector.visit(cu, classReport);
            classReport.setSrcFullFileName(packageReport.getFullPackageName() + classReport.getFullClassName());
            packageReport.addClassInfo(classReport);
        }
    }

    public void searchMainClass(final PackageReport packageReport) {
        packageReport.setMainClass(packageReport.getClassInfo()
                                                .stream()
                                                .filter(report -> report.getMethodsInfo()
                                                .stream()
                                                .anyMatch(methodReport -> methodReport.getName().equals("main"))).findFirst());
    }
}
