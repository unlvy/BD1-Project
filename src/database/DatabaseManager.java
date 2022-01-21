package database;

import com.jcraft.jsch.*;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Properties;

public class DatabaseManager {

    private Session session = null;
    private Connection connection = null;
    private final String dbaseURL = "no";
    private final String dbUser = "nope";
    private final String dbPassword = "no way";

    /**
     * connects to pascal database
     * create tunnel to pascal.fis.agh.edu.pl (on port XX) and next connect to db
     * pascal:XX -> localhost:XX
     */
    public void connect(String user, String password) throws Exception {

        String host = "mhmm";
        final Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");

        final JSch jsch = new JSch();

        session = jsch.getSession(user, host, 22);
        session.setPassword(password);
        session.setConfig(config);
        session.connect();
        session.setPortForwardingL(2137, host, 5432);
    }

    public ResultSet executeQuery(String query) {
        try {
            connection = DriverManager.getConnection(dbaseURL, dbUser, dbPassword);
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            connection.close();
            return rs;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
            return null;
        }
    }

    public void insertQuery(String query) {
        try {
            connection = DriverManager.getConnection(dbaseURL, dbUser, dbPassword);
            PreparedStatement pst = connection.prepareStatement(query);
            pst.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
        }
    }

    /**
     * Adds trip to database
     * @param trip
     * */
    public void addTrip(Trip trip) {
        if (trip.getOrder() < 2) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wycieczka musi skladac sie z minimum jednego etapu!");
            alert.show();
            return;
        }
        try {
            String insert = "INSERT INTO projekt.wycieczka(nazwa_wycieczki, czas_przejscia, w_gore, w_dol, dlugosc) VALUES('" +
                    trip.getName() + "', " + trip.getTotalTime() + ", " + trip.getTotalElevGain() + ", " + trip.getTotalElevLoss() + ", " + trip.getTotalLength() + ")";
            connection = DriverManager.getConnection(dbaseURL, dbUser, dbPassword);
            PreparedStatement pst = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            ResultSet resultSet = pst.getGeneratedKeys();
            if (resultSet.next()) {
                int tripId = resultSet.getInt(1);
                resultSet.close();
                connection.close();
                for (var step : trip.getSteps().entrySet()) {
                    insert = "INSERT INTO projekt.wycieczka_szlak(kolejnosc, wycieczka_id, szlak_id) VALUES(" +
                            step.getKey() + ", " + tripId + ", " + step.getValue() + ")";
                    connection = DriverManager.getConnection(dbaseURL, dbUser, dbPassword);
                    pst = connection.prepareStatement(insert);
                    pst.executeUpdate();
                    connection.close();
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
        }
    }

    /**
     * closes session
     * */
    public void closeConnection(){
        if (session != null && session.isConnected())
            session.disconnect();
    }

}