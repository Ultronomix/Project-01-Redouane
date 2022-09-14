package auth;

import exceptions.AuthenticationException;
import exceptions.InvalidRequestException;
import users.UserDAO;
import users.UserResponse;


public class AuthService {

    private final UserDAO userDAO;

    public AuthService (UserDAO userDAO){
        this.userDAO=userDAO;
    }

    public UserResponse authenticate (Credentials credentials){

        if (credentials == null) {
            throw new InvalidRequestException("The provided credentials object must not be null!");
        }

        if (credentials.getUsername().length() < 4) {
            throw new InvalidRequestException("The provided username must be at least 4 characters!");
        }

        if (credentials.getPassword().length() < 8) {
            throw new InvalidRequestException("The provided password must be at least 8 characters!");
        }

        UserResponse userResponse = userDAO.login(credentials.getUsername(), credentials.getPassword()).map(UserResponse::new).orElseThrow(AuthenticationException::new);

        if(userResponse.isActive()){
            return userResponse;
        }else{
            throw new AuthenticationException();
        }
    }
}