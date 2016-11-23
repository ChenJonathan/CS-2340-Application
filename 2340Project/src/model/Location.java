package model;

/**
 * Created by Wesley on 10/17/16. Adapted from Robert Waters's location class
 * Just a class to hold some data we might want to display on the map
 *
 * @author Wesley
 */
public class Location {

    /**Instance variable for the longitude. */
    private final double longitude;
    /**Instance variable for the latitude. */
    private final double latitude;
    /**Title of the location. */
    private final String title;

    /**
     * Constructor for a location object.
     * @param lat latitude
     * @param lg longitude
     * @param ti title
     */
    public Location(final double lat,
                    final double lg, final String ti) {
        longitude = lg;
        latitude = lat;
        title = ti;
    }

    /**
     * @return return longitude.
     */
    public final double getLongitude() {
        return longitude;
    }

    /**
     * @return returns latitude.
     */
    public final double getLatitude() {
        return latitude;
    }

    /**
     * @return returns title.
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Makes a location object from a string.
     * @param str The String to use.
     * @return returns a location object.
     */
    public static Location makeFromString(final String str) {

        String[] tokens = str.split("\t");
        double longit = 0;
        double lat = 0;
        try {
            longit = Double.parseDouble(tokens[0]);
            lat = Double.parseDouble(tokens[1]);
        } catch (Exception e) {
            return null;
        }
        return new Location(lat, longit, tokens[2]);

    }
}
