package kr.toolcat.tcputil.io.tcp;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import kr.toolcat.tcputil.io.IOStream;

public class ClientIOStream implements IOStream, Closeable {

    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public ClientIOStream(final String url) throws IOException {
        String[] _url = url.split(":");
        if (_url.length != 2) {
            throw new IllegalArgumentException("The host must bee specified like: 'hostName:port'");
        } else {
            String hostName = _url[0];
            int port = Integer.valueOf(_url[1]);
            socket = new Socket(hostName, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        }
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
    }
}
