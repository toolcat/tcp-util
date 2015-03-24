package kr.toolcat.tcputil.io.tcp;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import kr.toolcat.tcputil.io.IOStream;

public class ServerIOStream implements IOStream, Closeable {

    private final ServerSocket serverSocket;
    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public ServerIOStream(final String port) throws IOException {
        serverSocket = new ServerSocket(Integer.valueOf(port));
        socket = serverSocket.accept();
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    @Override
    public void write(final int b) throws IOException {
        outputStream.write(b);
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
    }
}
