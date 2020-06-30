package org.sm.decoder;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MPEG1AudioDecoder {

    private final InputStream inputStream;
    private final BitReader bitReader;

    public MPEG1AudioDecoder(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "Input stream should not be null");
        this.inputStream = inputStream;
        this.bitReader = new BitReader(inputStream);
    }

    /*public short[] findHeader(short[] headerStorage) throws IOException {
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
    }*/

    public Header findHeader2(Header headerBuffer) throws IOException {
        int _byte;
        Header header = headerBuffer == null ? new Header() : headerBuffer;
        boolean headerFound = false;
        while((_byte = inputStream.read()) != -1) {
            if (_byte == 0xFF) {
                bitReader.readByte();
                if (bitReader.readBits(4) != 0x0F) continue;
                header.parse(bitReader);
                headerFound = true;
                break;
            }
        }
        if (headerFound) return header;
        throw new IOException("Cannot find a valid header in provided input stream");
    }

    public void process() throws IOException {
        Header headerBuffer = new Header();
        CrcCheck crcCheckBuffer = new CrcCheck();
        Header header = findHeader2(headerBuffer);
        if (header.protectionBit == Header.CRC_CHECK_ADDED) {
            crcCheckBuffer.parse(bitReader);
        }

    }

    public CrcCheck readCrc() {
        return null;
    }
}
