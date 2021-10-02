package nl.minka;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Minka Firth
 */
public class Processor {

    final Set<String> branchNames = new HashSet<>();

    public void processBlock(List<String> branchBlock) throws Exception {
        if (branchBlock.size() == 7) {
            String branchName = processBranchName(branchBlock.get(0));
            String street = branchBlock.get(1);
            StreetNumberRange range = processStreetRange(branchBlock.get(2));
            String city = processCity(branchBlock.get(3));
            final MarioBranch mb = new MarioBranch(branchName, street, range, city, branchBlock.get(4), branchBlock.get(5), branchBlock.get(6));
            System.out.println(mb.toString());
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
            String suffix = trimmedLine.replaceAll("[^A-Za-z]", "");
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
        String city;
        StringBuilder sb = new StringBuilder();
        if(cityArSize > 1){
            for (int i = 0; i < cityAr.length; i++) {
                String subCityString = cityAr[i];
                String subCity = subCityString.substring(0, 1).toUpperCase() + subCityString.substring(1).toLowerCase();

            }
            return Arrays.toString(newCityAr);
        } else {
            return line.substring(0,1).toUpperCase() + line.substring(1).toLowerCase();
        }
    }
}
