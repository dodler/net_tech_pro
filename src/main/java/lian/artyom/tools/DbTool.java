package lian.artyom.tools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

/**
 * Created by dodler on 13/03/16.
 */
public class DbTool {

    SessionFactory sessionFactory;

    public DbTool(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public String sendRequest(String request) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/net_tech_pro", "dodler", "1123");

        Statement stmt = null;
        try {
            System.out.println("connecting");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(request);

            System.out.println("received result set");

            StringBuffer result = new StringBuffer();
            int columnCount = rs.getMetaData().getColumnCount();

            for(int i = 1; i<columnCount; i++){
                rs.getMetaData().getColumnName(i);
            }

            System.out.println("collected column names");

            while(rs.next()){
                for(int i = 1; i<columnCount; i++){
                    result.append(rs.getString(i));
                    result.append(" | ");
                }
                result.append("\n");
            }
            return result.toString();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) { stmt.close(); }
            if (connection != null){connection.close();}
        }
        return "failed to proceed";
    }
}
