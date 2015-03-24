package kr.toolcat.tcputil.io.consol;

import java.io.IOException;

import kr.toolcat.tcputil.io.IOStream;

public class ConsoleIOStream implements IOStream {

    @Override
    public int read() throws IOException {
        return System.in.read();
    }

    @Override
    public void write(final int b) throws IOException {
        System.out.write(b);
    }
}
