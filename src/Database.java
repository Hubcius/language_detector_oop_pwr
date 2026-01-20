import java.sql.*;

public class Database {
    static String url = "jdbc:sqlite:language_database.db"; 
    public static void database_initialize() {
        try(Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String creat_pages =
                    "CREATE TABLE IF NOT EXISTS Pages (" +
                    "page_id TEXT PRIMARY KEY, " +
                    "language_name TEXT NOT NULL)";

                String create_lettersamount =
                    "CREATE TABLE IF NOT EXISTS LettersAmount (" +
                    "page_id TEXT, " +
                    "letter TEXT, " +
                    "percentage_amount Double, " +
                    "PRIMARY KEY (page_id, letter), " +
                    "FOREIGN KEY (page_id) REFERENCES Task(page_id))";

                Statement stmt = conn.createStatement();
                stmt.execute(creat_pages);
                stmt.execute(create_lettersamount);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    static public void add_data(String data_string){
        try(Connection conn = DriverManager.getConnection(url)) {
            String [] data = data_string.split("#");

            String insert_Task =
                "INSERT OR IGNORE INTO Pages(page_id, language_name) VALUES (?, ?)";
            PreparedStatement task_stmt = conn.prepareStatement(insert_Task);
            task_stmt.setString(1, data[0]);
            task_stmt.setString(2, data[1]);
            int ret = task_stmt.executeUpdate();

            if(ret != 0){

                String insert_LettersAmount =
                    "INSERT INTO LettersAmount(page_id, letter, percentage_amount) " +
                    "VALUES (?, ?, ?)";
                PreparedStatement lettersAmount_stmt = conn.prepareStatement(insert_LettersAmount);

                String [] letters = data[2].split(",");
                double sum_of_letters = 0;
                for (String l : letters) {
                    String[] parts = l.split(":");
                    sum_of_letters += Double.parseDouble(parts[1]);
                }
                for (String l : letters) {
                    String[] parts = l.split(":");
                    String letter = parts[0];
                    Double amount = Double.parseDouble(parts[1]);

                    lettersAmount_stmt.setString(1, data[0]);
                    lettersAmount_stmt.setString(2, letter);
                    lettersAmount_stmt.setDouble(3, amount/sum_of_letters);
                    lettersAmount_stmt.executeUpdate();
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public String get_data_language(String language){
        String result_of_method ="";
        try(Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            String get_no_languages ="SELECT COUNT(page_id) AS no_of_pages, language_name "+
                "FROM Pages " + 
                "WHERE language_name = '" + language + "' " +
                "GROUP BY language_name";
            ResultSet rs = stmt.executeQuery(get_no_languages);
            rs.next();
            String no_of_pages = rs.getString("no_of_pages");
            result_of_method += no_of_pages +"#";
            String get_sum_of_letters = "SELECT language_name, letter, AVG(percentage_amount) AS avg_percentage_amount_of_letters, SUM(percentage_amount * percentage_amount) AS sum_squared_percentage_amount_of_letters " +
                "FROM Pages " +
                "INNER JOIN LettersAmount ON Pages.page_id = LettersAmount.page_id " +
                "WHERE language_name = '"+ language + "' " +
                "GROUP BY language_name, letter";
            
            rs = stmt.executeQuery(get_sum_of_letters);
            while(rs.next()){
                result_of_method += rs.getString("letter") + "," + rs.getString("avg_percentage_amount_of_letters") + "," + rs.getString("sum_squared_percentage_amount_of_letters") + ";";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result_of_method;
    }
    
}
