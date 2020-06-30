package org.sm.snippets;

import org.sm.decoder.BitReader;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test00020BinaryLogicTest {

    public static void main(String[] args) {
        Test00020BinaryLogicTest test00020BinaryLogicTest = new Test00020BinaryLogicTest();
        //test00020BinaryLogicTest.test001TestCaseWhenNumberOfBitsSmallerThanWholeByte();
        test00020BinaryLogicTest.test003();
    }

    void test001TestCaseWhenNumberOfBitsSmallerThanWholeByte() {
        int _wholeByte = 0b0011_1100;
        int ptr = 3;
        int bitsNumber = 3;
        int shifted = 8 - (ptr + bitsNumber);
        int _byte = _wholeByte >>> shifted;
        int mask = (1 << shifted) - 1;
        _byte &= mask;
        System.out.println(_byte);
    }

    void test002() {
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new byte[] {0b0011_1100, 0b0011_1110}
        );
        try {
            BitReader bitReader = new BitReader(bais);
            bitReader.readByte();
            System.out.println(bitReader.readBits(3));
            System.out.println(bitReader.readBits(3));
            System.out.println(bitReader.readBits(2));
            System.out.println(bitReader.readBits(4));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Exception while reading input stream", e);
        }
    }

    void test003() {
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new byte[] {0b0011_1100, 0b0011_1110, 0b01100110}
        );
        try {
            BitReader bitReader = new BitReader(bais);
            bitReader.readByte();
            System.out.println(bitReader.readBits(3));
            System.out.println(bitReader.readBits(15));
            System.out.println(bitReader.readBits(3));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Exception while reading input stream", e);
        }
    }
}
