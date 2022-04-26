package pcd02.visitors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import pcd02.interfaces.InterfaceReport;

public class InterfaceReportCollector extends VoidVisitorAdapter<InterfaceReport> {

    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, InterfaceReport report) {
        super.visit(classOrInterfaceDeclaration, report);
        report.setFullInterfaceName(classOrInterfaceDeclaration.getNameAsString());
    }

    public void visit(MethodDeclaration md, InterfaceReport report ) {
        super.visit(md, report);
        report.addMethod(md.getNameAsString());
    }

}
