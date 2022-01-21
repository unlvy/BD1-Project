package tables;
import database.DatabaseManager;
import javafx.scene.control.*;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableManager {

    public ArrayList<Point> getAllPoints(DatabaseManager dbManager) {

        ArrayList<Point> result = new ArrayList<>();
        ResultSet resultSet = dbManager.executeQuery("SELECT * FROM projekt.punkty_szczegolowo");
        try {
            while(resultSet.next()) {
                String name = resultSet.getString("nazwa");
                double pointLatitude = resultSet.getDouble("szerokosc_geograficzna");
                double pointLongitude = resultSet.getDouble("wysokosc_geograficzna");
                String type = resultSet.getString("typ");
                String coordinates = String.format("%.1f %c %.1f %c",
                        pointLongitude, (Math.abs(pointLongitude) < 0 ? 'S' : 'N'),
                        pointLatitude, (Math.abs(pointLatitude) < 0 ? 'E' : 'W'));
                String neighbours = resultSet.getString("sasiedzi").replaceAll("\\\\n", System.lineSeparator());
                String details = resultSet.getString("szczegoly").replaceAll("\\\\n", System.lineSeparator());
                result.add(new Point(name, coordinates, type, neighbours, details));
            }
            resultSet.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
            return null;
        }
        return result;
    }

    public ArrayList<ShortPoint> getShortPoints(DatabaseManager dbManager) {
        ArrayList<ShortPoint> result = new ArrayList<>();
        ResultSet resultSet = dbManager.executeQuery("SELECT nazwa, szerokosc_geograficzna, wysokosc_geograficzna, punkt_id FROM projekt.points_view");
        try {
            while(resultSet.next()) {
                String pointName = resultSet.getString("nazwa");
                double pointLatitude = resultSet.getDouble("szerokosc_geograficzna");
                double pointLongitude = resultSet.getDouble("wysokosc_geograficzna");
                int pointId = resultSet.getInt("punkt_id");
                String coordinates = String.format("%.1f %c %.1f %c",
                        pointLongitude, (Math.abs(pointLongitude) < 0 ? 'S' : 'N'),
                        pointLatitude, (Math.abs(pointLatitude) < 0 ? 'E' : 'W'));
                result.add(new ShortPoint(pointName, coordinates, new Button("wybierz"), pointId));
            }
        } catch(SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
            return null;
        } try {
            resultSet.close();
        } catch (Exception ignored) {}
        return result;
    }

    public ArrayList<NextPoint> getNextPoints(DatabaseManager dbManager, int pointId) {
        ArrayList<NextPoint> result = new ArrayList<>();
        ResultSet resultSet = dbManager.executeQuery(
                "SELECT " +
                    "szlak.stop_punkt_id, " +
                    "szlak.w_gore, " +
                    "szlak.w_dol, " +
                    "szlak.czas_przejscia, " +
                    "droga.dlugosc, " +
                    "droga.kolor, " +
                    "droga.droga_id, " +
                    "szlak.szlak_id " +
                "FROM " +
                    "projekt.szlak " +
                "INNER JOIN projekt.droga ON " +
                        "szlak.droga_id = droga.droga_id " +
                "WHERE " +
                    "szlak.start_punkt_id = " + pointId
        );
        try {
            while(resultSet.next()) {
                int endPointId = resultSet.getInt("stop_punkt_id");
                int elevGain = resultSet.getInt("w_gore");
                int elevLoss = resultSet.getInt("w_dol");
                int time = resultSet.getInt("czas_przejscia");
                int length = resultSet.getInt("dlugosc");
                String color = resultSet.getString("kolor");
                int routeId = resultSet.getInt("droga_id");
                int trailId = resultSet.getInt("szlak_id");

                // end point details
                ResultSet resultSet2 = dbManager.executeQuery("SELECT nazwa, szerokosc_geograficzna, wysokosc_geograficzna FROM projekt.punkt WHERE punkt_id = " + endPointId);
                resultSet2.next();
                String name = resultSet2.getString("nazwa");
                double pointLatitude = resultSet2.getDouble("szerokosc_geograficzna");
                double pointLongitude = resultSet2.getDouble("wysokosc_geograficzna");
                String coordinates = String.format("%.1f %c %.1f %c",
                        pointLongitude, (Math.abs(pointLongitude) < 0 ? 'S' : 'N'),
                        pointLatitude, (Math.abs(pointLatitude) < 0 ? 'E' : 'W'));

                // trails details
                StringBuilder details = new StringBuilder();
                resultSet2.close();
                resultSet2 = dbManager.executeQuery(
                        "SELECT " +
                                "atrakcja_typ, " +
                                "opis_atrakcji " +
                        "FROM " +
                                "projekt.droga_atrakcja " +
                        "INNER JOIN projekt.atrakcja_typ ON " +
                                "droga_atrakcja.atrakcja_typ_id = atrakcja_typ.atrakcja_typ_id " +
                        "WHERE " +
                                "droga_id = " + routeId
                );
                details.append("Atrakcje:");
                while(resultSet2.next()) {
                    details.append('\n').append(resultSet2.getString("atrakcja_typ")).append(
                            ": ").append(resultSet2.getString("opis_atrakcji"));
                }
                resultSet2.close();
                resultSet2 = dbManager.executeQuery(
                        "SELECT " +
                                "zagrozenie_typ, " +
                                "zagrozenie_opis " +
                        "FROM " +
                                "projekt.droga_zagrozenie " +
                        "INNER JOIN projekt.zagrozenie_typ ON " +
                                "droga_zagrozenie.zagrozenie_typ_id = zagrozenie_typ.zagrozenie_typ_id " +
                        "WHERE " +
                            "droga_id = " + routeId
                );

                details.append("\nZagrozenia:");
                while(resultSet2.next()) {
                    details.append('\n').append(resultSet2.getString("zagrozenie_typ")).append(
                            ": ").append(resultSet2.getString("zagrozenie_opis"));
                }
                resultSet2.close();
                result.add(new NextPoint(name, coordinates, color, length, time, elevGain, elevLoss, details.toString(), new Button("wybierz"), endPointId, trailId));
            }
        } catch(SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
            return null;
        } try {
            resultSet.close();
        } catch (Exception ignored) {}
        return result;
    }

    public ArrayList<TripView> getTrips(DatabaseManager dbManager) {
        ArrayList<TripView> result = new ArrayList<>();
        ResultSet resultSet = dbManager.executeQuery("SELECT * FROM projekt.wycieczka");
        try {
            while(resultSet.next()) {
                int tripId = resultSet.getInt("wycieczka_id");
                String name = resultSet.getString("nazwa_wycieczki");
                int time = resultSet.getInt("czas_przejscia");
                int elevGain = resultSet.getInt("w_gore");
                int elevLoss = resultSet.getInt("w_dol");
                int length = resultSet.getInt("dlugosc");
                result.add(new TripView(name, length, time, elevGain, elevLoss, tripId));
            }
            resultSet.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
            return null;
        }
        return result;
    }

    public ArrayList<TripStage> getTripStages(DatabaseManager dbManager, int tripId) {
        ArrayList<TripStage> result = new ArrayList<>();
        ResultSet resultSet = dbManager.executeQuery(
                "SELECT " +
                    "wycieczka_szlak.kolejnosc, " +
                    "sp.nazwa AS n1, " +
                    "ep.nazwa AS n2, " +
                    "droga.dlugosc, " +
                    "szlak.czas_przejscia, " +
                    "szlak.w_gore, " +
                    "szlak.w_dol, " +
                    "droga.kolor, " +
                    "droga.droga_id " +
                "FROM " +
                    "projekt.wycieczka_szlak, " +
                    "projekt.szlak, " +
                    "projekt.punkt sp, " +
                    "projekt.punkt ep, " +
                    "projekt.droga " +
                "WHERE " +
                    "wycieczka_szlak.wycieczka_id = " + tripId + " " +
                    "AND wycieczka_szlak.szlak_id = szlak.szlak_id " +
                    "AND szlak.start_punkt_id = sp.punkt_id " +
                    "AND szlak.stop_punkt_id = ep.punkt_id " +
                    "AND szlak.droga_id = droga.droga_id " +
                "ORDER BY " +
                    "wycieczka_szlak.kolejnosc ASC");
        try {
            while(resultSet.next()) {
                int order = resultSet.getInt("kolejnosc");
                String startPointName = resultSet.getString("n1");
                String endPointName = resultSet.getString("n2");
                int length = resultSet.getInt("dlugosc");
                int time = resultSet.getInt("czas_przejscia");
                int elevGain = resultSet.getInt("w_gore");
                int elevLoss = resultSet.getInt("w_dol");
                String color = resultSet.getString("kolor");
                int routeId = resultSet.getInt("droga_id");

                StringBuilder dangers = new StringBuilder();
                ResultSet resultSet2 = dbManager.executeQuery(
                        "SELECT " +
                                "zagrozenie_typ, " +
                                "zagrozenie_opis " +
                        "FROM " +
                                "projekt.droga_zagrozenie " +
                        "INNER JOIN projekt.zagrozenie_typ ON " +
                                "droga_zagrozenie.zagrozenie_typ_id = zagrozenie_typ.zagrozenie_typ_id " +
                        "WHERE " +
                                "droga_id = " + routeId
                );
                while(resultSet2.next()) {
                    dangers.append(resultSet2.getString("zagrozenie_typ")).append(
                            ":\n ").append(resultSet2.getString("zagrozenie_opis")).append('\n');
                }
                resultSet2.close();

                StringBuilder attractions = new StringBuilder();
                resultSet2 = dbManager.executeQuery(
                        "SELECT " +
                                "atrakcja_typ, opis_atrakcji " +
                        "FROM " +
                                "projekt.droga_atrakcja " +
                        "INNER JOIN projekt.atrakcja_typ ON " +
                                "droga_atrakcja.atrakcja_typ_id = atrakcja_typ.atrakcja_typ_id " +
                         "WHERE " +
                                "droga_id = " + routeId
                );
                while(resultSet2.next()) {
                    attractions.append(resultSet2.getString("atrakcja_typ")).append(
                            ":\n ").append(resultSet2.getString("opis_atrakcji")).append('\n');
                }
                resultSet2.close();
                result.add(new TripStage(order, startPointName, endPointName, length, time, elevGain, elevLoss, color, dangers.toString(), attractions.toString()));
            }
            resultSet.close();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
            return null;
        }
        return result;
    }

    public ArrayList<ShelterAmenities> getShelterAmenities(DatabaseManager dbManager) {
        ArrayList<ShelterAmenities> result = new ArrayList<>();
        ResultSet resultSet = dbManager.executeQuery("SELECT * FROM projekt.udogodnienie");
        try {
            while (resultSet.next()) {
                String description = resultSet.getString("opis_udogodnienia");
                int id = resultSet.getInt("udogodnienie_id");
                result.add(new ShelterAmenities(description, new CheckBox(), id));
            }
            resultSet.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
            return null;
        }
        return result;
    }

    public ArrayList<TrailAttraction> getTrailAttractions(DatabaseManager dbManager) {
        ArrayList<TrailAttraction> result = new ArrayList<>();
        ResultSet resultSet = dbManager.executeQuery("SELECT * FROM projekt.atrakcja_typ");
        try {
            while(resultSet.next()) {
                int id = resultSet.getInt("atrakcja_typ_id");
                String type = resultSet.getString("atrakcja_typ");
                result.add(new TrailAttraction(type, new TextField(), new CheckBox(), id));
            }
            resultSet.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
            return null;
        }
        return result;
    }

    public ArrayList<TrailDanger> getTrailDangers(DatabaseManager dbManager) {
        ArrayList<TrailDanger> result = new ArrayList<>();
        ResultSet resultSet = dbManager.executeQuery("SELECT * FROM projekt.zagrozenie_typ");
        try {
            while(resultSet.next()) {
                int id = resultSet.getInt("zagrozenie_typ_id");
                String type = resultSet.getString("zagrozenie_typ");
                result.add(new TrailDanger(type, new TextField(), new CheckBox(), id));
            }
            resultSet.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQLException: " + e);
            alert.show();
            return null;
        }
        return result;
    }

}
