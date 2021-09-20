package components.toolbar;

import utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GalleryToolbarUI {

    private final GalleryToolbar galleryToolbar;

    private final JToggleButton
            btnCategoryPeople = new JToggleButton(),
            btnCategoryPlaces = new JToggleButton(),
            btnCategoryPets = new JToggleButton();

    public GalleryToolbarUI(GalleryToolbar galleryToolbar) {
        this.galleryToolbar = galleryToolbar;
    }

    public void initializeUI() {
        setToggleButtonsListeners();

        galleryToolbar.setFloatable(true);
        galleryToolbar.setOrientation(SwingConstants.VERTICAL);
        galleryToolbar.add(btnCategoryPeople);
        galleryToolbar.add(btnCategoryPlaces);
        galleryToolbar.add(btnCategoryPets);
    }

    private void setToggleButtonsListeners() {
        setToggleButton(btnCategoryPeople, Utils.getCategories()[0]);
        setToggleButton(btnCategoryPlaces, Utils.getCategories()[1]);
        setToggleButton(btnCategoryPets, Utils.getCategories()[2]);
    }

    private void setToggleButton(JToggleButton btnCategory, String category) {
        btnCategory.setText(category + Utils.getUnselected());
        btnCategory.setBorder(new EmptyBorder(8, 8, 8, 8));

        btnCategory.addActionListener(listener -> {
            if (btnCategory.isSelected()) {
                btnCategory.setText(category + Utils.getSelected());
            } else {
                btnCategory.setText(category + Utils.getUnselected());
            }
        });
    }

}
