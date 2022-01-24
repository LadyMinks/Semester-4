package nl.minka;

/**
 * @author Minka Firth
 */
public class StreetNumberRange {

    private final StreetNumber firstNumber;
    private final StreetNumber secondNumber;

    public StreetNumberRange(StreetNumber firstNumber, StreetNumber secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public StreetNumber getFirstNumber() {
        return firstNumber;
    }

    public StreetNumber getSecondNumber() {
        return secondNumber;
    }

    @Override
    public String toString() {

        if (firstNumber.getNumber() == secondNumber.getNumber()){
            return String.valueOf(firstNumber);
        } else {
            return firstNumber + "-" + secondNumber;
        }
    }
}