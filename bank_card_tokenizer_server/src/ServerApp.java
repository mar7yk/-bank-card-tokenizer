import java.beans.XMLEncoder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.stream.Stream;

public class ServerApp extends Thread {
    private final Socket serverClientSocket;
    private final int id;
    private static int lastId = 0;
    private Users users;
    private TokenRegister tokenRegister;

    public ServerApp(Socket serverClientSocket, Users users, TokenRegister tokenRegister) throws NullPointerException {
        this.serverClientSocket = serverClientSocket;
        id = lastId;

        if (serverClientSocket == null) {
            throw new NullPointerException("serverClientSocket value is null at ServerApp " + id);
        }

        this.users = users;
        this.tokenRegister = tokenRegister;

        ++lastId;
    }

    public void run() {

        try ( DataInputStream inStream = new DataInputStream(serverClientSocket.getInputStream());
              DataOutputStream outStream = new DataOutputStream(serverClientSocket.getOutputStream())

        ) {
            System.out.println("Client " + id + " connects...");

            String clientMessage;

            while (true) {
                clientMessage = inStream.readUTF();
                String[] words = clientMessage.split(" ");

                User user;
                switch (words[0]) {
                    case "login":
                        user = users.login(words[1], words[2]);

                        if (user == null) {
                            outStream.writeUTF("nothing");

                        } else {
                            outStream.writeUTF(user.getName());
                        }

                        break;
                    case "register":
                        user = users.register(words[1], words[2]);

                        if (user == null) {
                            outStream.writeUTF("nothing");

                        } else {
                            outStream.writeUTF(user.getName());
                        }
                        break;
                    case "register-token":
                        user = users.getByName(words[2]);
                        if (user == null) {
                            outStream.writeUTF("nothing");
                        } else {
                            String token = tokenRegister.add(words[1], user);

                            if (token.equals("")) {
                                outStream.writeUTF("nothing");

                            } else {
                                outStream.writeUTF(token);
                            }
                        }
                        break;
                    case "get-card-num":
                        user = users.getByName(words[2]);
                        if (user == null) {
                            outStream.writeUTF("nothing");
                        } else {
                            String cardNum = tokenRegister.get(words[1], user);

                            if (cardNum.equals("")) {
                                outStream.writeUTF("nothing");

                            } else {
                                outStream.writeUTF(cardNum);
                            }
                        }
                        break;
                }
                outStream.flush();
            }

        } catch (EOFException ignored) {
            System.out.println("Client " + id + " disconnects...");
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                serverClientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
