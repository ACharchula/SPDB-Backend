package pl.spdb.app.model.poi;

import pl.spdb.app.model.route.Location;

import java.util.List;

public class VenueLocation extends Location {

    private List<String> formattedAddress;

    public VenueLocation(double lat, double lng) {
        super(lat, lng);
    }

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(List<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
