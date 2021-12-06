package nl.minka;

/**
 * @author Minka Firth
 */
public class Importer {

    public void writeToDb(MarioBranch mb){
        String branchName = mb.getBranchName();
        String insertSql = "INSERT INTO Branch branchName=" + branchName;
    }

}
