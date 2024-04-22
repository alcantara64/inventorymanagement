package aad.project.InventoryManagementSystem.utils.storage.entity;

import aad.project.InventoryManagementSystem.config.scurity.exceptions.InvalidAuthRequest;
import aad.project.InventoryManagementSystem.storage.entity.Token;
import aad.project.InventoryManagementSystem.storage.entity.User;
import org.json.JSONObject;

import java.util.UUID;

public class RequestAuthUtils {

    public static String login(JSONObject userLoginRequest, String role) throws InvalidAuthRequest {
        User user = new User(userLoginRequest.getString("username"));
        if (user.validate(userLoginRequest.getString("password"))) {
            Token token = new Token(user.getUserId(), generateToken(user), Token.UserRole.valueOf(role));
            token.save();
            return token.getJwtToken();
        }
        throw new InvalidAuthRequest("Invalid username or password");
    }

    private static String generateToken(User user) {
        return UUID.randomUUID().toString();
    }

    public static boolean isValidToken(String authorizationHeader) throws InvalidAuthRequest {
        Token t = new Token(authorizationHeader);
        if (t.getUserRole() == null) {
            return false;
        }
        return true;
    }

    public static User getUser(String authorizationHeader) throws InvalidAuthRequest {
        Token t = new Token(authorizationHeader);
        if (t.getUserRole() == null) {
            throw new InvalidAuthRequest("Invalid auth");
        }
        return new User(t.getUserId());
    }
}
