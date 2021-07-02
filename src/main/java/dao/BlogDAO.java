package dao;

import Model.Blog;
import Model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO implements IBlogDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";


    protected Connection getConection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        } catch (SQLException e) {
//                      // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public BlogDAO() {
    }

    @Override
    public void insertBlog(Blog blog) throws SQLException {

    }

    @Override
    public User selectBlog(int id) {
        return null;
    }

    @Override
    public List<Blog> selectAllBlog() {
        List<Blog> listblog = new ArrayList<>();
        try (Connection connection = getConection(); PreparedStatement preparedStatement = connection.prepareStatement("select * from blog")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String user_id = rs.getString("user_id");
                listblog.add(new Blog(id, title, content, user_id));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listblog;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateBlog(Blog blog) throws SQLException {
        return false;
    }
}
