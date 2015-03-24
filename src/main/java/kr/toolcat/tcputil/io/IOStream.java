package kr.toolcat.tcputil.io;

import java.io.IOException;

public interface IOStream {

	int read() throws IOException;
	
	void write(int b) throws IOException;
}
