package components.main;

import javax.swing.*;

public class MainSection extends JPanel {

    private final MainSectionUI view;

    public MainSection() {
        this.view = new MainSectionUI(this);
    }

}
