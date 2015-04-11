package kr.toolcat.tcputil.io.tcp;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import kr.toolcat.tcputil.io.IOStream;
import kr.toolcat.tcputil.main.Starter;

public class ServerIOStream implements IOStream, Closeable {

    private final ServerSocket serverSocket;

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ServerIOStream(final String port) throws IOException {
        serverSocket = new ServerSocket(Integer.valueOf(port));
        Starter.EXECUTOR.execute(() -> {
            try {
                while (true) {
                    accept();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

	private void accept() throws IOException {
		Socket accept = serverSocket.accept();
		closeClient();
		socket = accept;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
	}

    @Override
    public int read() throws IOException {
    	if (inputStream != null) {
    		try {
				return inputStream.read();
			} catch (IOException e) {
				closeClient();
			}
    	}
		return -1;
    }

    @Override
    public void write(final int b) throws IOException {
    	if (outputStream != null) {
    		try {
        		outputStream.write(b);
        		outputStream.flush();
			} catch (IOException e) {
				closeClient();
			}
    	}
    }

    @Override
    public void close() throws IOException {
        closeClient();
        serverSocket.close();
    }

	private void closeClient() throws IOException {
		if (inputStream != null) {
			inputStream.close();
			inputStream = null;
		}
		if (outputStream != null) {
			outputStream.close();
			outputStream = null;
		}
		if (socket != null) {
			socket.close();
			socket = null;
		}
	}
}
