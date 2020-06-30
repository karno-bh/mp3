package org.sm.decoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MPEG1AudioDecoder {

    private final InputStream inputStream;

    public MPEG1AudioDecoder(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "Input stream should not be null");
        this.inputStream = inputStream;
    }

    public short[] findHeader(short[] headerStorage) throws IOException {
        int _byte;
        short[] header = headerStorage == null ? new short[4] : headerStorage;
        int i = 0;
        while ((_byte = inputStream.read()) != -1) {
            if (_byte == 0xFF) {
                header[i++] = (short)_byte;
                for (;(_byte = inputStream.read()) != -1 && i < 4; i++) {
                    if (i == 1) {
                        if ((_byte & 0xF0) == 0xF0) header[i] = (short)_byte;
                        else break;
                    } else {
                        header[i] = (short)_byte;
                    }
                }
                if (i == 3) break;
                else i = 0;
            }
        }
        return header;
    }

    public int findHeader2() throws IOException {
        return 0;
    }
}