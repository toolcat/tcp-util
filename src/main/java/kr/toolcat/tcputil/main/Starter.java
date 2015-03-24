package kr.toolcat.tcputil.main;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import kr.toolcat.tcputil.io.IOStream;
import kr.toolcat.tcputil.io.IOStreamFactory;

public class Starter {

    public static final Executor EXECUTOR = Executors.newCachedThreadPool();

    public static void main(final String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("The program sould have two argument (from, to)!");
        } else {
            IOStreamFactory factory = new IOStreamFactory();
            IOStream from = factory.createIOStream(args[0]);
            IOStream to = factory.createIOStream(args[1]);
            EXECUTOR.execute(() -> {
                try {
                    while (true) {
                        int b = from.read();
                        if (b != -1) {
                            to.write(b);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}