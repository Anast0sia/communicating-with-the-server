import java.io.*;
import java.net.Socket;

public class Client {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        System.out.println("Клиент запущен!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        System.out.println(reader.readLine());
        while (true) {
            String word = in.readLine();
            writer.println(word);
            System.out.println(reader.readLine());
            if (word.equalsIgnoreCase("stop")) {
                writer.close();
                reader.close();
                socket.close();
                break;
            }
        }
    }
}