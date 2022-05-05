package pcd02.visitors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import io.vertx.core.Future;
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
        log("visiting compilation unit");
        this.vertx.executeBlocking(promise -> super.visit(cu, collector));
        log("after visiting compilation unit");
    }

    public void visit(PackageDeclaration pd, Void collector) {
        log("visiting package declaration");
        Future<Void> fut = this.vertx.executeBlocking(promise -> {
            super.visit(pd, collector);
            promise.complete();
        });
        fut.onSuccess(res -> {
            if (this.packages.add(pd.getNameAsString())) {
                this.vertx.eventBus().publish(topicAddress, "package");
            }
        });
        log("after visiting package declaration");
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
    private void log(String message) {
        System.out.println("[ Thread: " + Thread.currentThread().getName() + " ]" + ": " + message);
    }

}
