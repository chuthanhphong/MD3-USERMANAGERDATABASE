package dao;

import Model.Blog;
import Model.User;

import java.sql.SQLException;
import java.util.List;

public interface IBlogDAO {
    public void insertBlog(Blog blog) throws SQLException;
    public User selectBlog(int id);
    public List<Blog>selectAllBlog();
    public boolean delete(int id) throws SQLException;
    public boolean updateBlog(Blog blog) throws SQLException;
}
