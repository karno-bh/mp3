package org.sm.decoder;

import java.io.IOException;

public class CrcCheck {

    public int crcCheck;

    public void parse(BitReader bitReader) throws IOException {
        crcCheck = bitReader.readBits(16);
    }
}
