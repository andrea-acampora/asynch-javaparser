package pcd02.visitors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

import java.util.HashSet;
import java.util.Set;

public class ProjectVisitor extends VoidVisitorAdapter<Void> {

    private final Vertx vertx;
    private final String topicAddress;
    private final Set<String> packages;

    public ProjectVisitor(Vertx vertx, String topicAddress) {
        this.vertx = vertx;
        this.topicAddress = topicAddress;
        this.packages = new HashSet<>();
    }


    public void visit(CompilationUnit cu, Void collector){
        super.visit(cu, collector);
        System.out.println("visiting cu...");
    }

    public void visit(PackageDeclaration pd, Void collector) {
        super.visit(pd, collector);
        if (this.packages.add(pd.getNameAsString())){
            this.vertx.eventBus().publish(topicAddress, "package");
        }
    }

    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Void collector) {
        super.visit(classOrInterfaceDeclaration, collector);
        if (classOrInterfaceDeclaration.isInterface()) {
            this.vertx.eventBus().publish(topicAddress, "interface");
        } else {
            this.vertx.eventBus().publish(topicAddress, "class");
        }
    }

    public void visit(MethodDeclaration md, Void collector) {
        super.visit(md, collector);
        this.vertx.eventBus().publish(topicAddress, "method");
    }

    public void visit(FieldDeclaration fd, Void collector) {
        super.visit(fd, collector);
        this.vertx.eventBus().publish(topicAddress, "field");
    }
}