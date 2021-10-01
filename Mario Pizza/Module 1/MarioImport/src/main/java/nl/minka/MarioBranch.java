package nl.minka;

/**
 * @author Minka Firth
 */
public class MarioBranch {

    private final String branchName;
    private final String streetName;
    private final int streetNumber;
    private final String streetNumberSuffix;
    private final String city;
    private final String country;
    private final String postalCode;
    private final String phoneNumber;

    public MarioBranch(String branchName, String streetName, int streetNumber, String streetNumberSuffix, String city, String country, String postalCode, String phoneNumber) {
        this.branchName = branchName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.streetNumberSuffix = streetNumberSuffix;
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

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getStreetNumberSuffix() {
        return streetNumberSuffix;
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
}
