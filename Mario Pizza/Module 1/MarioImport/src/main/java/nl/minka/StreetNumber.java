package nl.minka;

/**
 * @author Minka Firth
 */
public class StreetNumber {

    private final int number;
    private final String suffix;

    public StreetNumber(int number, String suffix) {
        this.number = number;
        this.suffix = suffix;
    }

    public int getNumber() {
        return number;
    }

    public String getSuffix() {
        return suffix;
    }

    @Override
    public String toString() {

        if (suffix.isBlank()){
            return String.valueOf(number);
        } else {
            return number + suffix;
        }
    }
}
