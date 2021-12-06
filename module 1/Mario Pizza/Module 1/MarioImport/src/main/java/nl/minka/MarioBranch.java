package nl.minka;

/**
 * @author Minka Firth
 */
public class MarioBranch {

    private final String branchName;
    private final String streetName;
    private final StreetNumberRange range;
    private final String city;
    private final String country;
    private final String postalCode;
    private final String phoneNumber;

    public MarioBranch(String branchName, String streetName, StreetNumberRange range, String city, String country, String postalCode, String phoneNumber) {
        this.branchName = branchName;
        this.streetName = streetName;
        this.range = range;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getStreetName() {
        return streetName;
    }

    public StreetNumberRange getRange() {
        return range;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "MarioBranch{" +
                "branchName='" + branchName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", range=" + range +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
