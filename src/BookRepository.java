import java.sql.*;
import java.util.ArrayList;

public class BookRepository {

    String url = "jdbc:mysql://localhost:3306/bibliotek";
    String username = "root";
    String password = "nisse";

    public ArrayList<Book> searchBooks(String search){

        ArrayList<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE title LIKE '%" + search + "%'")) {

            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");

                //System.out.println(bookId + ": " + title);

                books.add(new Book(bookId, title));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

}
