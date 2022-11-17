package com.applet.particle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class JavaSwingAbstractApp {

    private JFrame jFrame;

    public JavaSwingAbstractApp() {
        this.jFrame = new JFrame();
        this.jFrame.setVisible(true);
    }

    public void init() {}

    public void start() {}

    public void stop() {}

    public void add(Component component) {
        this.jFrame.add(component);
        this.jFrame.setSize(component.getSize());
    }

    public Component[] getComponents() {
        return this.jFrame.getComponents();
    }


}
