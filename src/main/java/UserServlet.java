import Model.User;
import dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Servlet", value = "/users")
public class UserServlet extends HttpServlet {
private static final long serialVersionUID=1L;
private UserDao userDao;
public void init(){
    userDao = new UserDao();
}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "sort":
                    sortbyName(request,response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertUser(request, response);
                    break;
                case "edit":
                    updateUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

}

    private void sortbyName(HttpServletRequest request, HttpServletResponse response) throws ServletException,SQLException,IOException {
        List<User> usersortbyName = userDao.SortUserByName();
        request.setAttribute("listUser",usersortbyName);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request,response);
    }

    public void listUser(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException,ServletException {
List<User> listUser = userDao.selectAllUser();
request.setAttribute("listUser",listUser);
RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
dispatcher.forward(request,response);
}
private void showNewForm(HttpServletRequest request,HttpServletResponse response)   throws ServletException, IOException{
    RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
    dispatcher.forward(request,response);
}
private void showEditForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,SQLException,IOException{
    int id = Integer.parseInt(request.getParameter("id"));
    RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
    User existingUser = userDao.selectUser(id);
    request.setAttribute("user",existingUser);
    dispatcher.forward(request,response);
}
private void insertUser(HttpServletRequest request,HttpServletResponse response) throws
        ServletException,SQLException,IOException{
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String country = request.getParameter("country");
    User newUser = new User(name,email,country);
    userDao.insertUser(newUser);
    RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
    dispatcher.forward(request,response);
}
private void updateUser(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException,SQLException{
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String country = request.getParameter("country");
    User book = new User(id,name,email,country);
    userDao.updateUser(book);
    RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
    dispatcher.forward(request,response);
}
private void deleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException,SQLException{
    int id = Integer.parseInt(request.getParameter("id"));
    userDao.deleteUser(id);
List<User> listUser = userDao.selectAllUser();
request.setAttribute("listUser",listUser);
RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
dispatcher.forward(request,response);
}
}
