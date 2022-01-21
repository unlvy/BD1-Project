import database.DatabaseManager;
import database.Trip;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import tables.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    private final DatabaseManager dbManager;
    private final TableManager tableManager;
    private final Trip trip;
    private final HashMap<Integer, Integer> pointIndexId;
    private int userGroup = 3;

    @FXML
    private Text pointsViewHeader;

    @FXML
    private Text tripsViewHeader;

    @FXML
    private Button pointsViewButton;

    @FXML
    private Button trailAddButton;

    @FXML
    private Button tripsViewButton;

    @FXML
    private Button createTripViewButton;

    @FXML
    private Button addAmenityButton;

    @FXML
    private Button addPointViewButton;

    @FXML
    private Button addTrailViewButton;

    @FXML
    private Button addTrailDATypesButton;

    @FXML
    private Button addAmenityViewButton;

    @FXML
    private Button cancelNewTripButton;

    @FXML
    private Button saveNewTripButton;

    @FXML
    private Button addNewPointButton;

    @FXML
    private Button addNewTrailDButton;

    @FXML
    private Button addNewTrailAButton;

    @FXML
    private Button registerUserButton;

    @FXML
    private Button loginUserButton;

    @FXML
    private ChoiceBox<String> newUserGroup;

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane pointsView;

    @FXML
    private AnchorPane tripsView;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane tripDetailsPane;

    @FXML
    private StackPane newTripStackPane;

    @FXML
    private AnchorPane newTripView;

    @FXML
    private AnchorPane newTripNextPointView;

    @FXML
    private GridPane loginPane;

    @FXML
    private GridPane addNewPointPane;

    @FXML
    private GridPane userLoginPane;

    @FXML
    private GridPane addNewTrailPane;

    @FXML
    private GridPane addNewTrailDAPane;

    @FXML
    private GridPane addNewAmenityPane;

    @FXML
    private GridPane newPointValley;

    @FXML
    private GridPane newPointRidge;

    @FXML
    private GridPane newPointPeak;

    @FXML
    private GridPane newPointShelter;

    @FXML
    private Pane buttonsPane;

    @FXML
    private TextField newTrailADescription;

    @FXML
    private TextField newTrailDDescription;

    @FXML
    private TextField newAmenity;

    @FXML
    private TextField trailLength;

    @FXML
    private TextField trailABTime;

    @FXML
    private TextField trailABUp;

    @FXML
    private TextField trailABDown;

    @FXML
    private TextField trailBATime;

    @FXML
    private TextField trailColor;

    @FXML
    private ChoiceBox<String> trailAChoiceBox;

    @FXML
    private ChoiceBox<String> trailBChoiceBox;

    @FXML
    private TextField loginUser;

    @FXML
    private TextField newPointName;

    @FXML
    private TextField newPointX;

    @FXML
    private TextField newPointY;

    @FXML
    private TextField newPointValleyElevation;

    @FXML
    private TextField newPointRidgeMountain;

    @FXML
    private TextField newPointRidgeElevation;

    @FXML
    private TextField newPointPeakProminence;

    @FXML
    private TextField newPointPeakElevation;

    @FXML
    private TextField newPointShelterBeds;

    @FXML
    private ChoiceBox<String> newPointType;

    @FXML
    private TextField newTripName;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginButton;

    @FXML
    private TextField newUserLogin;

    @FXML
    private PasswordField newUserPassword;

    @FXML
    private TextField userLogin;

    @FXML
    private PasswordField userPassword;;

    @FXML
    private Text loginError;

    @FXML
    private TableView<tables.Point> pointsTable;

    @FXML
    private TableView<tables.ShelterAmenities> shelterAmenitiesTable;

    @FXML
    private TableColumn<TableView<tables.ShelterAmenities>, String> shelterAmenitiesTableDescriptionColumn;

    @FXML
    private TableColumn<TableView<tables.ShelterAmenities>, String> shelterAmenitiesTableCheckColumn;

    @FXML
    private TableColumn<TableView<tables.Point>, String> pointTableNameColumn;

    @FXML
    private TableColumn<TableView<tables.Point>, String> pointTableCordsColumn;

    @FXML
    private TableColumn<TableView<tables.Point>, String> pointTableTypeColumn;

    @FXML
    private TableColumn<TableView<tables.Point>, String> pointTableNeighboursColumn;

    @FXML
    private TableColumn<TableView<tables.Point>, String> pointTableInfoColumn;

    @FXML
    private TableView<tables.ShortPoint> newTripTable;

    @FXML
    private TableColumn<TableView<tables.ShortPoint>, String> newTripTableNameColumn;

    @FXML
    private TableColumn<TableView<tables.ShortPoint>, String> newTripTableCoordinatesColumn;

    @FXML
    private TableColumn<TableView<tables.ShortPoint>, Button> newTripTableActionColumn;

    @FXML
    private TableView<tables.NextPoint> nextPointTable;

    @FXML
    private TableColumn<TableView<tables.NextPoint>, String> nextPointNameColumn;

    @FXML
    private TableColumn<TableView<tables.NextPoint>, String> nextPointCoordinatesColumn;

    @FXML
    private TableColumn<TableView<tables.NextPoint>, String> nextPointColorColumn;

    @FXML
    private TableColumn<TableView<tables.NextPoint>, String> nextPointLengthColumn;

    @FXML
    private TableColumn<TableView<tables.NextPoint>, String> nextPointTimeColumn;

    @FXML
    private TableColumn<TableView<tables.NextPoint>, String> nextPointElevationColumn;

    @FXML
    private TableColumn<TableView<tables.NextPoint>, String> nextPointDetailsColumn;

    @FXML
    private TableColumn<TableView<tables.NextPoint>, Button> nextPointActionColumn;

    @FXML
    private TableView<tables.TripView> tripsTable;

    @FXML
    private TableColumn<TableView<tables.TripView>, String> tripsTableNameColumn;

    @FXML
    private TableColumn<TableView<tables.TripView>, String> tripsTableLengthColumn;

    @FXML
    private TableColumn<TableView<tables.TripView>, String> tripsTableTimeColumn;

    @FXML
    private TableColumn<TableView<tables.TripView>, String> tripsTableElevationColumn;

    @FXML
    private TableColumn<TableView<tables.TripView>, Button> tripsTableShowColumn;

    @FXML
    private TableView<tables.TripStage> tripDetailsTable;

    @FXML
    private TableColumn<TableView<tables.TripStage>, String> tripDetailsTableOrderColumn;

    @FXML
    private TableColumn<TableView<tables.TripStage>, String> tripDetailsTableStartPointColumn;

    @FXML
    private TableColumn<TableView<tables.TripStage>, String> tripDetailsTableEndPointColumn;

    @FXML
    private TableColumn<TableView<tables.TripStage>, String> tripDetailsTableLengthColumn;

    @FXML
    private TableColumn<TableView<tables.TripStage>, String> tripDetailsTableTimeColumn;

    @FXML
    private TableColumn<TableView<tables.TripStage>, String> tripDetailsTableElevationColumn;

    @FXML
    private TableColumn<TableView<tables.TripStage>, String> tripDetailsTableColorColumn;

    @FXML
    private TableColumn<TableView<tables.TripStage>, String> tripDetailsTableDangerColumn;

    @FXML
    private TableColumn<TableView<tables.TripStage>, String> tripDetailsTableAttractionColumn;

    @FXML
    private TableView<tables.TrailDanger> trailDangerTable;

    @FXML
    private TableColumn<TableView<tables.TrailDanger>, String> trailDangerTypeColumn;

    @FXML
    private TableColumn<TableView<tables.TrailDanger>, TextField> trailDangerDescriptionColumn;

    @FXML
    private TableColumn<TableView<tables.TrailDanger>, CheckBox> trailDangerCheckColumn;

    @FXML
    private TableView<tables.TrailAttraction> trailAttractionTable;

    @FXML
    private TableColumn<TableView<tables.TrailAttraction>, String> trailAttractionTypeColumn;

    @FXML
    private TableColumn<TableView<tables.TrailAttraction>, TextField> trailAttractionDescriptionColumn;

    @FXML
    private TableColumn<TableView<tables.TrailAttraction>, CheckBox> trailAttractionCheckColumn;

    public Controller() {
        dbManager = new DatabaseManager();
        tableManager = new TableManager();
        trip = new Trip();
        pointIndexId = new HashMap<>();
    }

    @FXML
    private void initialize() {
        pointTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pointTableCordsColumn.setCellValueFactory(new PropertyValueFactory<>("coordinates"));
        pointTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        pointTableNeighboursColumn.setCellValueFactory(new PropertyValueFactory<>("neighbours"));
        pointTableInfoColumn.setCellValueFactory(new PropertyValueFactory<>("info"));

        newTripTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        newTripTableCoordinatesColumn.setCellValueFactory(new PropertyValueFactory<>("coordinates"));
        newTripTableActionColumn.setCellValueFactory(new PropertyValueFactory<>("button"));

        nextPointNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nextPointCoordinatesColumn.setCellValueFactory(new PropertyValueFactory<>("coordinates"));
        nextPointColorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        nextPointLengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        nextPointTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        nextPointElevationColumn.setCellValueFactory(new PropertyValueFactory<>("elevation"));
        nextPointDetailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));
        nextPointActionColumn.setCellValueFactory(new PropertyValueFactory<>("button"));

        tripsTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tripsTableLengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        tripsTableTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        tripsTableElevationColumn.setCellValueFactory(new PropertyValueFactory<>("elevation"));
        tripsTableShowColumn.setCellValueFactory(new PropertyValueFactory<>("button"));

        tripDetailsTableOrderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        tripDetailsTableStartPointColumn.setCellValueFactory(new PropertyValueFactory<>("startPoint"));
        tripDetailsTableEndPointColumn.setCellValueFactory(new PropertyValueFactory<>("endPoint"));
        tripDetailsTableLengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        tripDetailsTableTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        tripDetailsTableElevationColumn.setCellValueFactory(new PropertyValueFactory<>("elevation"));
        tripDetailsTableColorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        tripDetailsTableDangerColumn.setCellValueFactory(new PropertyValueFactory<>("danger"));
        tripDetailsTableAttractionColumn.setCellValueFactory(new PropertyValueFactory<>("attraction"));

        shelterAmenitiesTableDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        shelterAmenitiesTableCheckColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        trailDangerTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        trailDangerDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        trailDangerCheckColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        trailAttractionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        trailAttractionDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        trailAttractionCheckColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        newPointType.getItems().add("1: Skrzyzowanie szlakow");
        newPointType.getItems().add("2: Dolina");
        newPointType.getItems().add("3: Przelecz");
        newPointType.getItems().add("4: Szczyt");
        newPointType.getItems().add("5: Schronisko");

        newPointType.setOnAction(event -> {
            pointType(newPointType.getValue());
        });

        newUserGroup.getItems().add("1: gość");
        newUserGroup.getItems().add("2: edytor");
        newUserGroup.getItems().add("3: administrator");
    }

    @FXML
    private void pointType(String value) {
        newPointValley.setVisible(false);
        newPointRidge.setVisible(false);
        newPointPeak.setVisible(false);
        newPointShelter.setVisible(false);

        int type = Character.getNumericValue(value.charAt(0));
        switch (type) {
            case 2:
                newPointValley.setVisible(true);
                break;
            case 3:
                newPointRidge.setVisible(true);
                break;
            case 4:
                newPointPeak.setVisible(true);
                break;
            case 5:
                newPointShelter.setVisible(true);
                shelterAmenitiesTable.getItems().clear();
                shelterAmenitiesTable.getItems().addAll(tableManager.getShelterAmenities(dbManager));
                break;
        }

    }

    @FXML
    private void showPointsView() {
        hideAll();
        pointsView.setVisible(true);
        pointsTable.getItems().clear();

        ResultSet resultSet = dbManager.executeQuery("SELECT COUNT(*) FROM projekt.punkt");
        try {
            resultSet.next();
            pointsViewHeader.setText("Przegląd wszystkich dostępnych w bazie punktów (" + resultSet.getInt(1) + " rekordów)");
            resultSet.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
        }
        ArrayList<Point> points = tableManager.getAllPoints(dbManager);
        pointsTable.getItems().addAll(points);

    }

    @FXML
    private void showTripsView() {
        hideAll();
        tripsView.setVisible(true);
        tripsTable.getItems().clear();

        ResultSet resultSet = dbManager.executeQuery("SELECT COUNT(*) FROM projekt.wycieczka") ;
        try {
            resultSet.next();
            tripsViewHeader.setText("Przegląd wszystkich dostępnych w bazie wycieczek (" + resultSet.getInt(1) + " rekordów)");
            resultSet.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
        }
        ArrayList<TripView> tripViews = tableManager.getTrips(dbManager);
        for (TripView tripView : tripViews) {
            tripView.getButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    showTripDetails(tripView.getTripId());
                }
            });
        }
        tripsTable.getItems().addAll(tripViews);

    }

    @FXML
    public void showAddPointView() {
        hideAll();
        if (userGroup < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "BRAK UPRAWNIEN");
            alert.show();
            return;
        }
        addNewPointPane.setVisible(true);
    }

    @FXML
    public void showAddTrailView() {
        if (userGroup < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "BRAK UPRAWNIEN");
            alert.show();
            return;
        }
        hideAll();
        addNewTrailPane.setVisible(true);
        prepareNewTrail();
    }

    @FXML
    public void showAddTrailDAView() {
        hideAll();
        if (userGroup < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "BRAK UPRAWNIEN");
            alert.show();
            return;
        }
        addNewTrailDAPane.setVisible(true);
    }

    @FXML
    public void showAddAmenityView() {
        hideAll();
        if (userGroup < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "BRAK UPRAWNIEN");
            alert.show();
            return;
        }
        addNewAmenityPane.setVisible(true);
    }

    @FXML
    private void showCreateTripView() {

        hideAll();
        if (userGroup < 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "BRAK UPRAWNIEN");
            alert.show();
            return;
        }
        newTripNextPointView.setVisible(false);
        newTripStackPane.setVisible(true);
        newTripView.setVisible(true);

        trip.clear();

        newTripTable.getItems().clear();
        ArrayList<ShortPoint> shortPoints = tableManager.getShortPoints(dbManager);
        for (ShortPoint shortPoint : shortPoints) {
            shortPoint.getButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    chooseNextPoint(shortPoint.getPointId());
                }
            });
        }
        newTripTable.getItems().addAll(shortPoints);
    }

    @FXML
    private void chooseNextPoint(int pointId) {

        newTripView.setVisible(false);
        newTripNextPointView.setVisible(true);
        nextPointTable.getItems().clear();

        ArrayList<NextPoint> nextPoints = tableManager.getNextPoints(dbManager, pointId);
        for (NextPoint nextPoint : nextPoints) {
            nextPoint.getButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    trip.addStep(nextPoint.getTrailId(), nextPoint.getLengthInt(), nextPoint.getTimeInt(),
                            nextPoint.getElevGain(), nextPoint.getElevLoss());
                    chooseNextPoint(nextPoint.getEndPointId());
                }
            });
        }
        nextPointTable.getItems().addAll(nextPoints);

    }

    @FXML
    private void prepareNewTrail() {
        trailAChoiceBox.getItems().clear();
        trailBChoiceBox.getItems().clear();
        trailAttractionTable.getItems().clear();
        trailDangerTable.getItems().clear();
        pointIndexId.clear();
        ResultSet resultSet = dbManager.executeQuery("SELECT punkt_id, nazwa FROM projekt.punkt");
        try {
            int index = 0;
            while(resultSet.next()) {
                int id = resultSet.getInt("punkt_id");
                String name = resultSet.getString("nazwa");
                pointIndexId.put(index++, id);
                trailAChoiceBox.getItems().add(name);
                trailBChoiceBox.getItems().add(name);
            }
            resultSet.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
        }
        trailAttractionTable.getItems().addAll(tableManager.getTrailAttractions(dbManager));
        trailDangerTable.getItems().addAll(tableManager.getTrailDangers(dbManager));
    }

    @FXML
    private void login() {
        String user = loginUser.getText();
        String pass = loginPassword.getText();
        loginButton.setDisable(true);
        try {
            dbManager.connect(user, pass);
            loginPane.setVisible(false);
            userLoginPane.setVisible(true);
//            buttonsPane.setVisible(true);
//            stackPane.setVisible(true);
        } catch (Exception e) {
            loginButton.setDisable(false);
            loginError.setText("Exception: " + e);
        }
    }

    public void stop() {
        dbManager.closeConnection();
    }

    @FXML
    private void showTripDetails(int tripId) {

        hideAll();
        tripDetailsPane.setVisible(true);
        tripDetailsTable.getItems().clear();
        tripDetailsTable.getItems().addAll(tableManager.getTripStages(dbManager, tripId));

    }

    @FXML
    private void cancelNewTrip() {
        showCreateTripView();
    }

    @FXML
    private void saveNewTrip() {
        trip.setName(newTripName.getText());
        dbManager.addTrip(trip);
        showCreateTripView();
    }

    @FXML
    private void addNewPoint() {
        String name = newPointName.getText();
        String xS = newPointX.getText();
        String yS = newPointY.getText();
        int x, y;
        try {
            x = Integer.parseInt(xS);
            y = Integer.parseInt(yS);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "x i y musza byc typu int!");
            alert.show();
            return;
        }

        if (newPointType.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nalezy wybrac typ punktu!");
            alert.show();
            return;
        }

        int type = Character.getNumericValue(newPointType.getValue().charAt(0));
        String query = "";
        switch (type) {
            case 1:
                query = "SELECT projekt.nowy_punkt_skrzyzowanie('" + name + "', " + x + ", " + y + ")";
                break;
            case 2:
                int elevation;
                try {
                    elevation = Integer.parseInt(newPointValleyElevation.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "wysokosc musi byc typu int!");
                    alert.show();
                    return;
                }
                query = "SELECT projekt.nowy_punkt_dolina('" + name + "', " + x + ", " + y + ", " + elevation + ")";
                break;
            case 3:
                try {
                    elevation = Integer.parseInt(newPointRidgeElevation.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "wysokosc musi byc typu int!");
                    alert.show();
                    return;
                }
                String mtn = newPointRidgeMountain.getText();
                query = "SELECT projekt.nowy_punkt_przelecz('" + name + "', " + x + ", " + y + ", " + elevation + ", '" + mtn + "')";
                break;
            case 4:
                int prominence;
                try {
                    elevation = Integer.parseInt(newPointPeakElevation.getText());
                    prominence = Integer.parseInt(newPointPeakProminence.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "wysokosc i wybitnosc musza byc typu int!");
                    alert.show();
                    return;
                }
                query = "SELECT projekt.nowy_punkt_szczyt('" + name + "', " + x + ", " + y + ", " + elevation + ", " + prominence + ")";
                break;
            case 5:
                int numBeds;
                try {
                    numBeds = Integer.parseInt(newPointShelterBeds.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "ilosc lozek musi byc typu int!");
                    alert.show();
                    return;
                }
                StringBuilder amenitiesIds = new StringBuilder();
                amenitiesIds.append("ARRAY[");
                boolean flag = false;
                for (ShelterAmenities shelterAmenities : shelterAmenitiesTable.getItems()) {
                    if (shelterAmenities.getCheckBox().isSelected()) {
                        if (flag) {
                            amenitiesIds.append(',');
                        }
                        amenitiesIds.append(shelterAmenities.getAmenityId());
                        flag = true;
                    }
                }
                amenitiesIds.append(']');
                if (!flag)
                    amenitiesIds.append("integer[]");
                query = "SELECT projekt.nowy_punkt_schronisko('" + name + "', " + x + ", " + y + ", " + numBeds + ", " + amenitiesIds.toString() + ")";
                break;
        }
        dbManager.executeQuery(query);
    }

    @FXML
    private void hideAll() {
        pointsView.setVisible(false);
        tripsView.setVisible(false);
        tripDetailsPane.setVisible(false);
        newTripStackPane.setVisible(false);
        addNewPointPane.setVisible(false);
        addNewTrailPane.setVisible(false);
        addNewTrailDAPane.setVisible(false);
        addNewAmenityPane.setVisible(false);
    }

    @FXML
    private void addTrail() {
        int pointAId, pointBId, timeAB, timeBA, length, upAB, downAB;
        try {
            pointAId = pointIndexId.get(trailAChoiceBox.getSelectionModel().getSelectedIndex());
            pointBId = pointIndexId.get(trailBChoiceBox.getSelectionModel().getSelectedIndex());
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Punkty A i B nie moga byc puste!");
            alert.show();
            return;
        }
        try {
            timeAB = Integer.parseInt(trailABTime.getText());
            timeBA = Integer.parseInt(trailBATime.getText());
            length = Integer.parseInt(trailLength.getText());
            upAB = Integer.parseInt(trailABUp.getText());
            downAB = Integer.parseInt(trailABDown.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "czasy, dlugosc i przewyzszenia musza byc typu int!");
            alert.show();
            return;
        }
        HashMap<Integer, String> attractions = new HashMap<>();
        HashMap<Integer, String> dangers = new HashMap<>();
        for (TrailAttraction trailAttraction : trailAttractionTable.getItems()) {
            if (trailAttraction.getCheckBox().isSelected()) {
                attractions.put(trailAttraction.getAttractionTypeId(), trailAttraction.getDescription().getText());
            }
        }
        for (TrailDanger trailDanger : trailDangerTable.getItems()) {
            if (trailDanger.getCheckBox().isSelected()) {
                dangers.put(trailDanger.getDangerTypeId(), trailDanger.getDescription().getText());
            }
        }
        String color = trailColor.getText();
        if (color.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Kolor nie moze byc pusty!");
            alert.show();
            return;
        }

        StringBuilder dangerIds = new StringBuilder("ARRAY[");
        StringBuilder dangersDescriptions = new StringBuilder("ARRAY[");
        boolean flag = false;
        for (var entry : dangers.entrySet()) {
            if (flag) {
                dangerIds.append(',');
                dangersDescriptions.append(',');
            }
            dangerIds.append(entry.getKey());
            dangersDescriptions.append('\'').append(entry.getValue()).append('\'');
            flag = true;
        }
        if (!flag) {
            dangerIds.append("]::INTEGER[");
            dangersDescriptions.append("]::VARCHAR[");
        }
        dangerIds.append(']');
        dangersDescriptions.append(']');

        StringBuilder attractionsIds = new StringBuilder("ARRAY[");
        StringBuilder attractionsDescriptions = new StringBuilder("ARRAY[");
        flag = false;
        for (var entry : attractions.entrySet()) {
            if (flag) {
                attractionsIds.append(',');
                attractionsDescriptions.append(',');
            }
            attractionsIds.append(entry.getKey());
            attractionsDescriptions.append('\'').append(entry.getValue()).append('\'');
            flag = true;
        }
        if (!flag) {
            attractionsIds.append("]::INTEGER[");
            attractionsDescriptions.append("]::VARCHAR[");
        }
        attractionsIds.append(']');
        attractionsDescriptions.append(']');

        String query = String.format("SELECT projekt.nowa_droga_nowe_szlaki(%d, %d, %d, %d, %d, %d, %d, '%s', %s, %s, %s, %s)",
                pointAId, pointBId, timeAB, timeBA, upAB, downAB, length, color, attractionsIds.toString(),
                attractionsDescriptions.toString(), dangerIds.toString(), dangersDescriptions.toString());
        dbManager.executeQuery(query);
    }

    public void addNewTrailA() {
        String attraction = newTrailADescription.getText();
        if (attraction == null || attraction.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Opis nie moze byc pusty!");
            alert.show();
            return;
        }
        dbManager.insertQuery("INSERT INTO projekt.atrakcja_typ(atrakcja_typ) VALUES('" + attraction + "')");
    }

    public void addNewTrailD() {
        String danger = newTrailDDescription.getText();
        if (danger == null || danger.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Opis nie moze byc pusty!");
            alert.show();
            return;
        }
        dbManager.insertQuery("INSERT INTO projekt.zagrozenie_typ(zagrozenie_typ) VALUES('" + danger + "')");
    }

    public void addNewAmenity() {
        String amenity = newAmenity.getText();
        if (amenity == null || amenity.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Opis nie moze byc pusty!");
            alert.show();
            return;
        }
        dbManager.insertQuery("INSERT INTO projekt.udogodnienie(opis_udogodnienia) VALUES('" + amenity + "')");
    }

    @FXML
    public void registerUser() {
        String login = newUserLogin.getText();
        if (login == null || login.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "login nie moze byc pusty!");
            alert.show();
            return;
        }
        String password = newUserPassword.getText();
        if (password == null || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "haslo nie moze byc puste!");
            alert.show();
            return;
        }
        int group;
        try {
            group = Character.getNumericValue(newUserGroup.getValue().charAt(0));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "nalezy wybrac grupe!");
            alert.show();
            return;
        }
        dbManager.insertQuery("INSERT INTO projekt.uzytkownik VALUES('" + login + "', '" + password + "', " + group + ")");
    }

    @FXML
    public void loginUser() {

        String login = userLogin.getText();
        if (login == null || login.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "login nie moze byc pusty!");
            alert.show();
            return;
        }
        String password = userPassword.getText();
        if (password == null || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "haslo nie moze byc puste!");
            alert.show();
            return;
        }
        String pass;
        int group;
        try {
            ResultSet resultSet = dbManager.executeQuery("SELECT haslo, grupa FROM projekt.uzytkownik WHERE login = '" + login + "'");
            resultSet.next();
            pass = resultSet.getString("haslo");
            group = resultSet.getInt("grupa");
            resultSet.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Blad logowania");
            alert.show();
            return;
        }

        if (!password.equals(pass)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Bledne haslo! Poprawne: " + pass);
            alert.show();
            return;
        }

        else userGroup = group;
        userLoginPane.setVisible(false);
        buttonsPane.setVisible(true);
        stackPane.setVisible(true);
    }

}
