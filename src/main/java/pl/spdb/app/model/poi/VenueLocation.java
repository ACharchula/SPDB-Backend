package pl.spdb.app.model.poi;

import pl.spdb.app.model.route.Location;

import java.util.ArrayList;
import java.util.List;

public class VenueLocation extends Location {

    private List<String> formattedAddress;

    public VenueLocation(double lat, double lng) {
        super(lat, lng);
    }

    public VenueLocation(Location location, List<String> address) {
        super(location.getLat(), location.getLng());

        formattedAddress = new ArrayList<>();
        formattedAddress.addAll(address);
    }

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(List<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
