package pcd02.reports;

import pcd02.interfaces.ProjectElem;

public class ProjectElemImpl implements ProjectElem {

    private ElemType type;


    @Override
    public ElemType getType() {
        return type;
    }

    @Override
    public void setType(ElemType type) {
        this.type = type;
    }
}
