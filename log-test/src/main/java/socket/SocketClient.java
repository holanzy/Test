package socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        int port = 5000;
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket(ip, port);
            outputStream = socket.getOutputStream();
            String message = "Socket发送的数据";
            socket.getOutputStream().write(message.getBytes("UTF-8"));
            System.out.println("发送完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
            socket.close();
        }
    }
}
