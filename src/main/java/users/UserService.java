package users;

import java.util.List;
import java.util.stream.Collectors;

import common.ResponseString;
import exceptions.InvalidRequestException;
import exceptions.UserAlreadyExist;

public class UserService {

    private final UserDAO userDAO;

    public UserService (UserDAO userDAO){
        this.userDAO=userDAO;
    }

    public List <UserResponse> getAllUsers(){
        return userDAO.allUsers().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public UserResponse getUserById(String id) {

        if (id == null || id.length() <= 0) {
            throw new InvalidRequestException("id can not be null");
        }

        try {
            return userDAO.findUserById(id)
                    .map(UserResponse::new)
                    .orElseThrow(InvalidRequestException::new);

        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Inaccurate id was provided");
        }
    }

    public UserResponse getUserByUsername(String username) {

        if (username == null || username.length() <= 0) {
            throw new InvalidRequestException("username can not be null!");
        }

        try {
            return userDAO.findUserByUsername(username)
                    .map(UserResponse::new)
                    .orElseThrow(InvalidRequestException::new);

        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("An invalid username string was provided.");
        }
    }

    public UserResponse getUserByEmail(String email) {

        if (email == null || email.length() <= 0) {
            throw new InvalidRequestException("Email can't be null or empty");
        }

        try {
            return userDAO.findUserByEmail(email)
                    .map(UserResponse::new)
                    .orElseThrow(InvalidRequestException::new);

        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Input provided was inaccuarate.");
        }
    }

    public ResponseString register(NewUserRequest newUser){

        if (newUser == null) {
            throw new InvalidRequestException("Provided request should not be null!");
        }

        if (newUser.getFirstName() == null || newUser.getFirstName().length() <= 0 ||
                newUser.getLastName() == null || newUser.getLastName().length() <= 0)
        {
            throw new InvalidRequestException("please provide firstName and lastName!");
        }

        if (newUser.getEmail() == null || newUser.getEmail().length() <= 0) {
            throw new InvalidRequestException("please provide an email!");
        }

        if (newUser.getUsername() == null || newUser.getUsername().length() < 4) {
            throw new InvalidRequestException("A username with at least 4 characters must be provided!");
        }

        if (newUser.getPassword() == null || newUser.getPassword().length() < 8) {
            throw new InvalidRequestException("A password with at least 8 characters must be provided!");
        }

        if (userDAO.isEmailTaken(newUser.getEmail())) {
            throw new UserAlreadyExist("This email is being used.");
        }

        if (userDAO.isUsernameTaken(newUser.getUsername())) {
            throw new UserAlreadyExist("This username is being used.");
        }

        User userToPersist = newUser.extractEntity();
        String newUserId = userDAO.register(userToPersist);
        return new ResponseString(newUserId);
    }

    public ResponseString updateFristNmae (UpdateRequest updateRequest){

        if (updateRequest == null) {
            throw new InvalidRequestException("This request cannot be null!");
        }

        if (updateRequest.getUpdateTo() == null || updateRequest.getUpdateTo().length() <= 0 ||
                updateRequest.getUserId() == null || updateRequest.getUserId().length() <= 0) {

            throw new InvalidRequestException("Please provide both first name and user id");
        }

        if (!userDAO.isIdValid(updateRequest.getUserId())){

            throw new InvalidRequestException("Please provide a correct user id");
        }

        String updateSuccessfullMessage = userDAO.updateUserFristName(updateRequest.getUpdateTo(), updateRequest.getUserId());
        return new ResponseString(updateSuccessfullMessage);
    }

    public ResponseString updateLastNmae (UpdateRequest updateRequest){

        if (updateRequest == null) {
            throw new InvalidRequestException("Input should not be null or empty!");
        }

        if (updateRequest.getUpdateTo() == null || updateRequest.getUpdateTo().length() <= 0 ||
                updateRequest.getUserId() == null || updateRequest.getUserId().length() <= 0) {

            throw new InvalidRequestException("Please provide a last name and an user id");
        }

        if (!userDAO.isIdValid(updateRequest.getUserId())){

            throw new InvalidRequestException("Please provide a correct user id");
        }

        String updateSuccessfullMessage = userDAO.updateUserLastName(updateRequest.getUpdateTo(), updateRequest.getUserId());
        return new ResponseString(updateSuccessfullMessage);
    }

    public ResponseString updateEmail (UpdateRequest updateRequest){

        if (updateRequest == null) {
            throw new InvalidRequestException("Provided request must not be null!");
        }

        if (updateRequest.getUpdateTo() == null || updateRequest.getUpdateTo().length() <= 0 ||
                updateRequest.getUserId() == null || updateRequest.getUserId().length() <= 0) {

            throw new InvalidRequestException("please provide email and user id");
        }

        if (!userDAO.isIdValid(updateRequest.getUserId())){

            throw new InvalidRequestException("please provide a valid user id");
        }


        String updateSuccessfullMessage = userDAO.updateUserEmail(updateRequest.getUpdateTo(), updateRequest.getUserId());
        return new ResponseString(updateSuccessfullMessage);
    }

    public ResponseString updatePassword (UpdateRequest updateRequest){

        if (updateRequest == null) {
            throw new InvalidRequestException("Provided request must not be null!");
        }

        if (updateRequest.getUpdateTo() == null || updateRequest.getUpdateTo().length() <= 0 ||
                updateRequest.getUserId() == null || updateRequest.getUserId().length() <= 0) {

            throw new InvalidRequestException("please provide password and user id");
        }

        if (!userDAO.isIdValid(updateRequest.getUserId())){

            throw new InvalidRequestException("please provide a valid user id");
        }

        if (updateRequest.getUpdateTo().length() < 8) {
            throw new InvalidRequestException("A password with at least 8 characters must be provided!");
        }


        String updateSuccessfullMessage = userDAO.updateUserPassword(updateRequest.getUpdateTo(), updateRequest.getUserId());
        return new ResponseString(updateSuccessfullMessage);
    }

    public ResponseString updateIsActive (UpdateRequest updateRequest){

        if (updateRequest == null) {
            throw new InvalidRequestException("request can not be null!");
        }

        if (updateRequest.getUpdateTo() == null || updateRequest.getUpdateTo().length() <= 0 ||
                updateRequest.getUserId() == null || updateRequest.getUserId().length() <= 0) {

            throw new InvalidRequestException("please provide IsActive status and user id");
        }

        if (!userDAO.isIdValid(updateRequest.getUserId())){

            throw new InvalidRequestException("please provide a valid user id");
        }


        if (!updateRequest.getUpdateTo().equals(String.valueOf(false)) && !updateRequest.getUpdateTo().equals(String.valueOf(true))) {

            throw new InvalidRequestException("IsActive status must be true or false)");
        }


        String updateSuccessfullMessage = userDAO.updateUserIsActive(updateRequest.getUpdateTo(), updateRequest.getUserId());
        return new ResponseString(updateSuccessfullMessage);
    }

    public ResponseString updateRoleId (UpdateRequest updateRequest){

        if (updateRequest == null) {
            throw new InvalidRequestException("this request can not be null!");
        }

        if (updateRequest.getUpdateTo() == null || updateRequest.getUpdateTo().length() <= 0 ||
                updateRequest.getUserId() == null || updateRequest.getUserId().length() <= 0) {

            throw new InvalidRequestException("please provide role id and user id");
        }

        if (!userDAO.isIdValid(updateRequest.getUserId())){

            throw new InvalidRequestException("please provide a valid user id");
        }


        if (!updateRequest.getUpdateTo().equals("1") && !updateRequest.getUpdateTo().equals("2") && !updateRequest.getUpdateTo().equals("3")) {

            throw new InvalidRequestException("Role ID must be one of these numbers:(1)ADMIN (2)Manager (3)Employee");
        }

        String updateSuccessfullMessage = userDAO.updateUserRoleId(updateRequest.getUpdateTo(), updateRequest.getUserId());
        return new ResponseString(updateSuccessfullMessage);
    }
}