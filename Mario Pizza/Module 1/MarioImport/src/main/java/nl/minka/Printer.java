package nl.minka;

/**
 * @author Minka Firth
 */
public class Printer {

    private final MarioBranch marioBranch;

    public Printer(MarioBranch marioBranch) {
        this.marioBranch = marioBranch;
    }

    public MarioBranch getMarioBranch() {
        return marioBranch;
    }

    public void printBlock(MarioBranch marioBranch) throws Exception {
        String name = marioBranch.getBranchName();
        String city = marioBranch.getCity();
        String street = marioBranch.getStreetName();
        String country = marioBranch.getCountry();
        String postalCode = marioBranch.getPostalCode();
        String phoneNumber = marioBranch.getPhoneNumber();
        System.out.println(name + "\n" + street + " ");
        printNumber(marioBranch.getRange());
        System.out.println(city.replaceAll("[,]", " ") + "\n" + country + "\n" + postalCode + "\n" + phoneNumber + "\n");
    }

    private void printNumber(StreetNumberRange streetNumberRange) {
        StreetNumber firstStreetNumber = streetNumberRange.getFirstNumber();
        StreetNumber secondStreetNumber = streetNumberRange.getSecondNumber();

        if (firstStreetNumber.getNumber() == secondStreetNumber.getNumber()) {
            if (firstStreetNumber.getSuffix().isBlank()) {
                System.out.println(firstStreetNumber.getNumber());
            } else {
                System.out.println(firstStreetNumber.getNumber() + firstStreetNumber.getSuffix());
            }

        } else {
            if (firstStreetNumber.getSuffix().isBlank() && secondStreetNumber.getSuffix().isBlank()) {
                System.out.println(firstStreetNumber.getNumber() + "-" + secondStreetNumber.getNumber());
            } else if (firstStreetNumber.getSuffix().isBlank() && !secondStreetNumber.getSuffix().isBlank()) {
                System.out.println(firstStreetNumber.getNumber() + "-" + secondStreetNumber.toString());
            } else {
                System.out.println(firstStreetNumber.getNumber() + firstStreetNumber.getSuffix() + "-" + secondStreetNumber.getNumber() + secondStreetNumber.getSuffix());
            }
        }
    }
}
