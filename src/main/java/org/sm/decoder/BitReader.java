package org.sm.decoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class BitReader {

    private InputStream is;
    private int bitPtr = 0;
    private int currentByte;

    public BitReader(InputStream is) {
        Objects.requireNonNull(is, "Input Stream cannot be null");
        this.is = is;
    }

    public int readBits(int bitsNumber) throws IOException {
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

    public void readByte() throws IOException {
        currentByte = is.read();
        if (currentByte == -1) {
            throw new IOException("Unexpected end of input");
        }
    }

    public void set(int currentByte, int bytePtr) {
        this.currentByte = currentByte;
        this.bitPtr = bytePtr;
    }

    public int getBitPtr() {
        return bitPtr;
    }

    public int getCurrentByte() {
        return currentByte;
    }
}
