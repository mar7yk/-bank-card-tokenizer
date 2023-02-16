import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {

        Users users = new Users();
        TokenRegister tokenRegister = new TokenRegister();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            users.save();
            tokenRegister.save();
        }, "Shutdown-thread"));

        try ( ServerSocket server = new ServerSocket(8423) ) {

            System.out.println("Server started...");

            while (true) {
                try {
                    ServerApp app = new ServerApp(server.accept(), users, tokenRegister);
                    app.start();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}