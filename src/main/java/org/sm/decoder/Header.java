package org.sm.decoder;

import java.io.IOException;

public class Header {

    public static final int LAYER1 = 0b11;
    public static final int LAYER2 = 0b10;
    public static final int LAYER3 = 0b01;
    public static final int LAYER_RESERVED = 0b00;

    public static final int[] LAYER_TO_INDEX = {
            -1, 2, 1, 0
    };

    public static final int BITRATE_FREE = -1;
    public static final int BITRATE_FORBIDDEN = -2;

    public static final int[][] BIT_RATES = {
            // Layer 1   ,   Layer 2   ,    Layer 3
            {BITRATE_FREE, BITRATE_FREE, BITRATE_FREE}, /*0000*/
            {32, 32, 32}, /*0001*/
            {64, 48, 40}, /*0010*/
            {96, 56, 48}, /*0011*/
            {128, 64, 56}, /*0100*/
            {160, 80, 64}, /*0101*/
            {192, 96, 80}, /*0110*/
            {224, 112, 96}, /*0111*/
            {256, 128, 112}, /*1000*/
            {288, 160, 128}, /*1001*/
            {320, 192, 160}, /*1010*/
            {352, 224, 192}, /*1011*/
            {384, 256, 224}, /*1100*/
            {416, 320, 256}, /*1101*/
            {448, 384, 320}, /*1110*/
            {BITRATE_FORBIDDEN, BITRATE_FORBIDDEN, BITRATE_FORBIDDEN}, /*1111*/
    };

    public static final int[] SAMPLING_FREQUENCIES = {
        44100,
        48000,
        32000,
    };

    public static final int MODE_STEREO = 0b00;
    public static final int MODE_JOINT_STEREO = 0b01;
    public static final int MODE_DUAL_CHANNEL = 0b10;
    public static final int MODE_SINGLE_CHANNEL = 0b11;

    public static final int CRC_CHECK_ADDED = 0;
    public static final int NO_CRC_CHECK_ADDED = 1;

    public static final int PADDING = 1;
    public static final int NO_PADDING = 0;

    public static final int COPYRIGHT = 1;
    public static final int NO_COPYRIGHT = 0;

    public static final int ORIGINAL = 1;
    public static final int COPY = 0;

    public static final int EMPHASIS_NONE = 0b00;
    public static final int EMPHASIS_50_15 = 0b01;
    public static final int EMPHASIS_RESERVED = 0b10;
    public static final int EMPHASIS_CCITT_J_17 = 0b11;


    public int id;
    public int layer;
    public int protectionBit;
    public int bitRateIndex;
    public int samplingFrequency;
    public int paddingBit;
    public int privateBit;
    public int mode;
    public int modeExtension;
    public int copyright;
    public int originalOrCopy;
    public int emphasis;

    public void parse(BitReader bitReader) throws IOException {
        id = bitReader.readBits(1);
        layer = bitReader.readBits(2);
        protectionBit = bitReader.readBits(1);
        bitRateIndex = bitReader.readBits(4);
        samplingFrequency = bitReader.readBits(2);
        paddingBit = bitReader.readBits(1);
        privateBit = bitReader.readBits(1);
        mode = bitReader.readBits(2);
        modeExtension = bitReader.readBits(2);
        copyright = bitReader.readBits(1);
        originalOrCopy = bitReader.readBits(1);
        emphasis = bitReader.readBits(2);
    }
}
