package nl.minka;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World");
        Parser parser = new Parser();
        parser.readFile("Winkels Mario.txt");

//        SqlDatabaseConnection sqlDatabaseConnection = new SqlDatabaseConnection();
//        sqlDatabaseConnection.connect();
    }

}
