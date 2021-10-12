package nl.minka;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Minka Firth
 */
public class Processor {
    SqlDatabaseConnection connection = new SqlDatabaseConnection();

    final Set<String> branchNames = new HashSet<>();

    public void processBlock(List<String> branchBlock) throws Exception {
        if (branchBlock.size() == 7) {
            String branchName = processBranchName(branchBlock.get(0));
            String street = branchBlock.get(1);
            StreetNumberRange range = processStreetRange(branchBlock.get(2));
            String city = processCity(branchBlock.get(3));
            String country = processCountry(branchBlock.get(4));
            String postalCode = processPostalCode(branchBlock.get(5));
            String phoneNumber = processPhoneNumber(branchBlock.get(6));
            final MarioBranch mb = new MarioBranch(branchName, street, range, city, country, postalCode, phoneNumber);
            connection.connect(mb);
        } else throw new Exception("Branch incomplete");
    }

    private String processBranchName(String branchName) throws Exception {
        if (!branchNames.contains(branchName)) {
            branchNames.add(branchName);
        } else {
            throw new Exception("Branch name already exists");
        }
        return branchName;
    }

    private StreetNumber processStreetNumber(String line) throws Exception {
        if (line.matches(".*[0-9].*")) {
            final String trimmedLine = line.replace(" ", "");
            int number = Integer.parseInt(trimmedLine.replaceAll("[^0-9]", ""));
            String suffix = trimmedLine.replaceAll("[^A-Za-z]", "").toLowerCase();
            return new StreetNumber(number, suffix);
        } else throw new Exception("Must contain a Street Number");
    }

    private StreetNumberRange processStreetRange(String line) throws Exception {
        String[] range = line.split("-");
        int rangeSize = range.length;
        if (rangeSize > 1) {
            final StreetNumber firstNumber = processStreetNumber(range[0]);
            final StreetNumber secondNumber = processStreetNumber(range[1]);
            return new StreetNumberRange(firstNumber, secondNumber);
        } else {
            final StreetNumber firstNumber = processStreetNumber(line);
            final StreetNumber secondNumber = processStreetNumber(line);
            return new StreetNumberRange(firstNumber, secondNumber);
        }
    }

    private String processCity(String line) {
        String[] cityAr = line.split("\s");
        int cityArSize = cityAr.length;
        String[] newCityAr = new String[cityAr.length];
        String newString = "";
        if (cityArSize > 1) {
            for (int i = 0; i < cityAr.length; i++) {
                String subCityString = cityAr[i];
                String subCity = subCityString.substring(0, 1).toUpperCase() + subCityString.substring(1).toLowerCase();
                newCityAr[i] = subCity;
                newString = newString + " " + subCity + " ";
            }
            return newString.trim();
        } else {
            return line.substring(0, 1).toUpperCase() + line.substring(1).toLowerCase();
        }
    }

    private String processCountry(String line) throws Exception {
        if (line.length() == 2) {
            return line;
        } else {
            throw new Exception("please enter a valid country abbreviation");
        }
    }

    private String processPostalCode(String line) throws Exception {
        String newLine = line.trim().replaceAll(" ", "");
        String numberSubString = newLine.replaceAll("[^0-9]", "").trim();
        String letterSubString = newLine.replaceAll("[^A-Za-z]", "").toUpperCase().trim();

        if (numberSubString.length() != 4) {
            throw new Exception("PostalCode must contain 4 numbers");
        }
        if (letterSubString.length() != 2) {
            throw new Exception("PostalCode must contain 2 letters");
        }
        return numberSubString + letterSubString;
    }

    private String processPhoneNumber(String line) {
        return line.replaceAll("-", "").replaceAll("[^0-9]", "").trim();
    }
}
