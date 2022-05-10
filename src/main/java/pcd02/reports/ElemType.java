package pcd02.reports;

public enum ElemType {
    PACKAGE("package"),
    INTERFACE("interface"),
    CLASS("class"),
    METHOD("method"),
    FIELD("field");

    private final String text;

    ElemType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
