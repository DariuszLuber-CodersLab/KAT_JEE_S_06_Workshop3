package pl.coderslab.service;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.UserDAO;
import pl.coderslab.entity.User;

import java.sql.SQLException;

public class UserService {

    public static User register(String username, String password, String email) throws Exception{
        try {
            //validate password
            if(!password.matches("^\\w{5,7}$")){
                throw new Exception("Password not matches requirements");
            }

            User user = new User(username,email, password);
            user.setPassword(BCrypt.hashpw(password,BCrypt.gensalt()));

            UserDAO userDAO = new UserDAO();
            userDAO.create(user);

            return user;
        } catch (SQLException e) {
            throw new Exception("Error");
        }
    }

    public static User login(String username, String password){
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByUsername(username);
        if(user == null){
            return null;
        }

        if(BCrypt.checkpw(password, user.getPassword()) == false){
            return null;
        }

        return user;
    }


}
