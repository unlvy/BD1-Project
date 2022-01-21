package tables;

import javafx.scene.control.CheckBox;

public class ShelterAmenities {

    private final String description;
    private final CheckBox checkBox;
    private final int amenityId;

    public ShelterAmenities(String description, CheckBox checkBox, int amenityId) {
        this.description = description;
        this.checkBox = checkBox;
        this.amenityId = amenityId;
    }

    public String getDescription() {
        return description;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public int getAmenityId() {
        return amenityId;
    }
}
