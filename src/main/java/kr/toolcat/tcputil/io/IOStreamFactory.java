package kr.toolcat.tcputil.io;

import java.io.IOException;

import kr.toolcat.tcputil.io.consol.ConsoleIOStream;
import kr.toolcat.tcputil.io.file.FileIOStream;
import kr.toolcat.tcputil.io.tcp.ClientIOStream;
import kr.toolcat.tcputil.io.tcp.ServerIOStream;

public class IOStreamFactory {

    private final static String PROTOCOL_FILE = "file:";
    private final static String PROTOCOL_CONSOLE = "console";
    private final static String PROTOCOL_CLIENT = "client:";
    private final static String PROTOCOL_SERVER = "server:";

    public IOStream createIOStream(final String descriptor) throws IOException {
        if (descriptor.startsWith(PROTOCOL_CONSOLE)) {
            return new ConsoleIOStream();
        } else if (descriptor.startsWith(PROTOCOL_FILE)) {
            return new FileIOStream(descriptor.substring(PROTOCOL_FILE.length()));
        } else if (descriptor.startsWith(PROTOCOL_CLIENT)) {
            return new ClientIOStream(descriptor.substring(PROTOCOL_CLIENT.length()));
        } else if (descriptor.startsWith(PROTOCOL_SERVER)) {
            return new ServerIOStream(descriptor.substring(PROTOCOL_SERVER.length()));
        } else {
            throw new IllegalArgumentException("Not supported protocol:" + descriptor);
        }
    }
}
