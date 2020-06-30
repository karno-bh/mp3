package org.sm.decoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class BitReader {

    private final InputStream is;
    private int bitPtr = 0;
    private int currentByte;

    public BitReader(InputStream is) {
        Objects.requireNonNull(is, "Input Stream cannot be null");
        this.is = is;
        readByte();
    }

    public int readBits(int bitsNumber) {
        if(bitsNumber <= 0 || bitsNumber > 16) {
            throw new IllegalStateException("Number or read bits should be in range (0, 16)");
        }
        int acc = 0, _byte, mask, remainingBits;
        while (bitsNumber > (remainingBits = 8 - bitPtr)) {
            mask = (1 << remainingBits) - 1;
            _byte = currentByte & mask;
            acc <<= remainingBits;
            acc |= _byte;
            bitsNumber -= remainingBits;
            bitPtr = 0;
            readByte();
        }
        int shiftedBits = 8 - (bitPtr + bitsNumber);
        _byte = currentByte >>> shiftedBits;
        mask = (1 << bitsNumber) - 1;
        _byte &= mask;
        acc <<= bitsNumber;
        acc |= _byte;
        bitPtr += bitsNumber;
        if (bitPtr == 8) {
            bitPtr = 0;
            readByte();
        }
        return acc;
    }

    private void readByte() {
        try {
            currentByte = is.read();
            if (currentByte == -1) {
                throw new IOException("Unexpected end of input");
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Cannot read next byte from input stream");
        }
    }
}
