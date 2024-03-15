import somePackage.User;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class main {
    public static void main(String[] args) {
        User u = new User();
        User a = new User();

        u.isAdmin = true;
        LinkedHashMap<String, Object> users = new LinkedHashMap<>();

        users.put("user0", true);
        users.put("user1", u);
        users.put("user2", a);


        method(users);
    }
    public static void method (LinkedHashMap<String, Object> users){
        boolean s = false;
        for(String key : users.keySet()) {
            if(((User) users.get(key)).isAdmin){
                s = true;
                System.out.println("ssss");
                return;
            }
        }
        if(!s){
            System.out.println("aaaaaaaaaaaa");
        }
    }
}
