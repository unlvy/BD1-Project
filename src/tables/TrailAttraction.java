package tables;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class TrailAttraction {

    private final String type;
    private final TextField description;
    private final CheckBox checkBox;
    private final int attractionTypeId;

    public TrailAttraction(String type, TextField description, CheckBox checkBox, int attractionTypeId) {
        this.type = type;
        this.description = description;
        this.checkBox = checkBox;
        this.attractionTypeId = attractionTypeId;
    }

    public String getType() {
        return type;
    }

    public TextField getDescription() {
        return description;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public int getAttractionTypeId() {
        return attractionTypeId;
    }
}
