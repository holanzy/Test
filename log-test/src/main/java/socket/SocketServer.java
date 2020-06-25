package socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        int port = 5000;
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        try {
            System.out.println("server开始接收数据1");
            serverSocket = new ServerSocket(port);
            System.out.println("server开始接收数据2");
            socket = serverSocket.accept();
            System.out.println("server开始接收数据3");
            inputStream = socket.getInputStream();
            System.out.println("server开始接收数据4");
            byte[] bytes = new byte[1024];
            int len = 0;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len, "UTF-8"));
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void send() {
        Socket socket = null;
        try {
            socket = new Socket();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
