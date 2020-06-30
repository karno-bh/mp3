package org.sm.snippets;

import org.sm.decoder.MPEG1AudioDecoder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test00010HeaderReading {

    public static void main(String[] args) {
        Test00010HeaderReading test00010HeaderReading = new Test00010HeaderReading();
        test00010HeaderReading.test001();
    }

    void test001() {
        try (InputStream is = new BufferedInputStream(this.getClass().getResourceAsStream("/joint_stereo_kikuo.mp3"))){
            MPEG1AudioDecoder decoder = new MPEG1AudioDecoder(is);
            short[] header = decoder.findHeader(null);
            for (int val : header) {
                System.out.println(val);
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error while reading MP3", e);
        }
    }
}
