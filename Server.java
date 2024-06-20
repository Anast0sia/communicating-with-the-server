import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    private BufferedReader reader;
    private PrintWriter writer;
    Socket socket;

    public Server(Socket socket, BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
        this.socket = socket;
    }

    public void serv() throws IOException {
        writer.println("Приветствую Вас! Я - сервер. Мне Вы можете говорить абсолютно обо всём. Можете говорить обо всех своих проблемах, и я Вас постараюсь поддержать. " +
                "Начнём? (чтобы остановить программу, напишите слово \"stop\")");
        while (true) {
            String word = reader.readLine();
            randomMsg();
            if (word.equalsIgnoreCase("stop")) {
                writer.println("Спасибо, что доверились. Надеюсь, я смог Вам помочь)");
                break;
            }
        }
    }

    public void randomMsg() {
        String[] arr = {"Я Вас слушаю...",
                "Спасибо за то, что доверяете)",
                "Тааак...",
                "Хорошо...",
                "У Вас есть что ещё сказать?",
                "Спасибо, что рассказываете)",
                "Всё будет хорошо!"};
        int index = new Random().nextInt(arr.length);
        String answer = arr[index];
        writer.println(answer);
    }
}


class MainServ {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Сервер запущен!");
        Socket socket = serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Server server = new Server(socket, reader, writer);
        server.serv();

    }
}