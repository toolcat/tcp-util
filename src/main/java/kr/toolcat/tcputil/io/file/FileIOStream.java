package kr.toolcat.tcputil.io.file;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import kr.toolcat.tcputil.io.IOStream;

public class FileIOStream implements IOStream, Closeable {

    private final File file;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;

    public FileIOStream(final String path) throws FileNotFoundException {
        file = new File(path);
    }

    @Override
    public int read() throws IOException {
        if (fileInputStream == null) {
            fileInputStream = new FileInputStream(file);
        }
        return fileInputStream.read();
    }

    @Override
    public void write(final int b) throws IOException {
        if (fileOutputStream == null) {
            fileOutputStream = new FileOutputStream(file);
        }
        fileOutputStream.write(b);
        fileOutputStream.flush();
    }

    @Override
    public void close() throws IOException {
        if (fileInputStream != null) {
            fileInputStream.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
    }
}
