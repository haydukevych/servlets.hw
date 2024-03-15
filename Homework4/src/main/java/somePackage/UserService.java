package somePackage;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    List<User> listOfUsers = new ArrayList<>();
    private static UserService userService;

    private UserService(){
        listOfUsers.add(new User("admin", "admin").setAdmin(true));
        listOfUsers.add(new User("oles@gmail.com", "111"));
    }

    public static UserService getUserService(){
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getListOfUsers(){
        return listOfUsers;
    }
    public void saveUser(User user){
        listOfUsers.add(user);
    }
    public User getUser(String email) {
        return this.getListOfUsers().stream()
                .filter(user -> user != null && user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
