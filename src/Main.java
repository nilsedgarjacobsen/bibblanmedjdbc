import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bibliotek";
        String username = "root";
        String password = "nisse";

        Connection conn = DriverManager.getConnection(url, username, password);

        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM members";
        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Member> members = new ArrayList<>();

        while (rs.next()) {
            int memberId = rs.getInt("member_id");
            String name = rs.getString("name");
            String email = rs.getString("email");

            members.add(new Member(memberId, name, email));

            //System.out.println(member.getId() + ": " + member.getName() + ": " + member.getEmail());
        }

        for(Member member: members){
            System.out.println(member.getId() + ": " + member.getName() + ": " + member.getEmail());
        }

        rs.close();
        stmt.close();
        conn.close();

        BookRepository bookRepository = new BookRepository();
        bookRepository.searchBooks("pippi");

        ArrayList<Book> books = bookRepository.searchBooks("m");

        for(Book book : books){
            System.out.println(book.getTitle());
        }
    }
}