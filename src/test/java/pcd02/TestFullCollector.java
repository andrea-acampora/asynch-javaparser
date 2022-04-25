package pcd02;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.List;

class FullCollector extends VoidVisitorAdapter<Void> {

    public void visit(PackageDeclaration fd, Void collector) {
        super.visit(fd, collector);
        System.out.println(fd);
    }

    public void visit(ClassOrInterfaceDeclaration cd, Void collector) {
        super.visit(cd, collector);
        System.out.println(cd.getNameAsString());
    }

    public void visit(FieldDeclaration fd, Void collector) {
        super.visit(fd, collector);
        System.out.println(fd);
    }

    public void visit(MethodDeclaration md, Void collector) {
        super.visit(md, collector);
        System.out.println(md.getName());
    }
}


public class TestFullCollector {

    public static void main(String[] args) throws Exception {

        CompilationUnit cu = StaticJavaParser.parse(new File("src/test/java/pcd02/TestFullCollector.java"));
        var fullc = new FullCollector();
        fullc.visit(cu, null);
    }
}
