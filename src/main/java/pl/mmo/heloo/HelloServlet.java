package pl.mmo.heloo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class HelloServlet extends HttpServlet{

    HikariDataSource dataSource;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Moj init");

        HikariConfig hConfig = new HikariConfig();
        hConfig.setJdbcUrl(System.getenv("JDBC_DATABASE_URL"));
        if (hConfig.getJdbcUrl() != null)
            dataSource = new HikariDataSource(hConfig);
        else
            dataSource = new HikariDataSource();
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Moj destroy");
        dataSource.close();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("Hello Heroku, koko maroko z posta");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();

        out.println("Hello Heroku, koko maroko");
        
        
        
        
        
    
        
        
        
        
        
        
        
        
        
        
        
        HttpSession session = req.getSession();
        Object value = session.getValue("ddd");
        if(value!= null)
            out.write(value.toString().getBytes());
        else
            out.write("jeszcze nie ma".getBytes());
        System.out.println("--- helloo ---");

        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            ArrayList<String> output = new ArrayList<String>();
            while (rs.next()) {
                output.add("Read from DB: " + rs.getTimestamp("tick"));
                out.write(("\nRead from DB: " + rs.getTimestamp("tick")).getBytes());
            }
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        }

        out.flush();
        out.close();
    }

}
