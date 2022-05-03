package pcd02.controller;

public interface Observer {

    void notifyStart(String path);

    void notifyStop();
}
