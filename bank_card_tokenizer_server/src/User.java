import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class User {
    private String name;
    private String password;
    private List<String> tokens;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public User() {
        tokens = new LinkedList<>();
    }

    public User(String name, String password) {
        setName(name);
        setPassword(password);
    }

    public synchronized void addToken(String token) {
        tokens.add(token);
    }

    public synchronized boolean canUseToken(String token) {
        return tokens.contains(token);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public void fromString(String str) {
        String str1 = str;

        int num = str1.indexOf("name");
        str1 = str1.substring(num + 6);
        num = str1.indexOf('\'');
        name = str1.substring(0, num);


        num = str1.indexOf("password");
        str1 = str1.substring(num + 10);
        num = str1.indexOf('\'');
        password = str1.substring(0, num);
    }

    public void save() {
        try (FileOutputStream fos = new FileOutputStream("user.xml");
             XMLEncoder encoder = new XMLEncoder(fos)
        ) {

            encoder.setExceptionListener(Throwable::printStackTrace);

            encoder.writeObject(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
