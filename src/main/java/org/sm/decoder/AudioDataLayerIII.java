package org.sm.decoder;

import java.io.IOException;

public class AudioDataLayerIII {

    public int mainDataBegin;
    public int privateBits;
    public int[][] scfsi = new int[2][4];
    public int[][] part2_3Length = new int[2][2];
    public int[][] bigValues = new int[2][2];
    public int[][] globalGain = new int[2][2];
    public int[][] scalefacCompress = new int[2][2];
    public int[][] windowSwitchingFlag = new int[2][2];
    public int[][] blockType = new int[2][2];
    public int[][] mixedBlockFlag = new int[2][2];
    public int[][][] tableSelect = new int[2][2][3];
    public int[][][] subblockGain = new int[2][2][3];
    public int[][] region0Count = new int[2][2];
    public int[][] region1Count = new int[2][2];
    public int[][] preflag = new int[2][2];
    public int[][] scalefacScale = new int[2][2];
    public int[][] count1TableSelect = new int[2][2];

    public void parse(BitReader bitReader, Header header) throws IOException {
        mainDataBegin = bitReader.readBits(9);
        if (header.mode == Header.MODE_SINGLE_CHANNEL) {
            privateBits = bitReader.readBits(5);
        } else {
            privateBits = bitReader.readBits(3);
        }
        int nch = Header.MODE_SINGLE_CHANNEL == header.mode ? 1 : 2;
        for(int ch = 0; ch < nch; ch++) {
            for (int scfciBand = 0; scfciBand < 4; scfciBand++) {
                scfsi[ch][scfciBand] = bitReader.readBits(1);
            }
        }
    }
}
