package pcd02;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class MethodNameCollector extends VoidVisitorAdapter<List<String>> {
    public void visit(MethodDeclaration md, List<String> collector) {
        super.visit(md, collector);
        collector.add(md.getNameAsString());
    }
}

public class TestMethodsCollector {

    public static void main(String[] args) throws Exception {
        CompilationUnit cu = StaticJavaParser.parse(new File("src/test/java/pcd02/TestFullCollector.java"));

		var methodNames = new ArrayList<String>();
		var methodNameCollector = new MethodNameCollector();
		methodNameCollector.visit(cu,methodNames);
		methodNames.forEach(n -> System.out.println("MethodNameCollected:" + n));

    }
}
