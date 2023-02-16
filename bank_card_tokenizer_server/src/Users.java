import java.util.Hashtable;

public class Users extends DataBase<Hashtable<String, User>> {
    public Users() {
        init("users.xml");
    }

    public synchronized User register(String name, String pass) {
        if (data.containsKey(name)) {
            return null;
        }
        User user = new User(name, pass);
        data.put(name, user);

        return user;
    }

    public synchronized User login(String name, String pass) {
        User user = data.get(name);

        if (user == null || !user.getPassword().equals(pass)) {
            return null;
        }

        return user;
    }

    public synchronized User getByName(String name) {
        return data.get(name);
    }

}
