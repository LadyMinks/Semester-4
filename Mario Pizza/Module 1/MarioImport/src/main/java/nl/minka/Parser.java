package nl.minka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Minka Firth
 */
class Parser {

    private final List<String> stringList = new ArrayList<>();
    final List<String> branchBlock = new ArrayList<>();

    public List<String> readFile(String filename) {

        final File resourcesFolder = new File("./src/main/resources/");
        final File marioDataFolder = new File(resourcesFolder, "MarioData/");
        final File file = new File(marioDataFolder, filename);
        int lineNumber = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                processLine(line);
            }
            processLine("");
        } catch (Exception e) {
            final String message = "We failed to read file " + filename + ". Errors at line " + lineNumber;
            throw new RuntimeException(message, e);
        }
        return stringList;
    }

    private void processLine(String line) {
        if (!line.isEmpty()) {
            if (!line.startsWith("--")) {
                branchBlock.add(line);
            }
        } else if (!branchBlock.isEmpty()) {
            processBlock(branchBlock);
            branchBlock.clear();
        }
    }

    private void processBlock(List<String> branchBlock) {
        if (branchBlock.size() == 7) {
            System.out.println(branchBlock);
        }
    }
}
