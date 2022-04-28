package pcd02.visitors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import pcd02.interfaces.ClassReport;
import pcd02.interfaces.FieldReport;
import pcd02.interfaces.MethodReport;
import pcd02.reports.FieldReportImpl;
import pcd02.reports.MethodReportImpl;

public class ClassReportCollector extends VoidVisitorAdapter<ClassReport> {

    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, ClassReport report) {
        super.visit(classOrInterfaceDeclaration, report);
        report.setFullClassName(classOrInterfaceDeclaration.getNameAsString());
    }

    public void visit(MethodDeclaration md, ClassReport report ) {
        super.visit(md, report);
        MethodReport methodInfo = new MethodReportImpl();
        methodInfo.setName(md.getNameAsString());
        methodInfo.setType(md.getType().asString());
        methodInfo.setParent(report);
        methodInfo.setSrcBeginLine(md.getBegin().get().line);
        methodInfo.setSrcEndLine(md.getEnd().get().line);
        report.addMethodInfo(methodInfo);
    }

    public void visit(FieldDeclaration fd, ClassReport report ) {
        super.visit(fd, report);
        FieldReport fieldInfo = new FieldReportImpl();
        fd.getVariables().forEach(field ->{
            fieldInfo.setName(field.getNameAsString());
            fieldInfo.setType(field.getTypeAsString());
            fieldInfo.setParent(report);
        } );
       report.addFieldInfo(fieldInfo);
    }
}
