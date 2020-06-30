package org.sm.snippets;

import org.sm.decoder.Header;
import org.sm.decoder.MPEG1AudioDecoder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test00010HeaderReading {

    public static void main(String[] args) {
        Test00010HeaderReading test00010HeaderReading = new Test00010HeaderReading();
//        test00010HeaderReading.test001();
//        test00010HeaderReading.test002();
//        test00010HeaderReading.test003();
        test00010HeaderReading.test004();
    }

    /*void test001() {
        try (InputStream is = new BufferedInputStream(this.getClass().getResourceAsStream("/joint_stereo_kikuo.mp3"))){
            MPEG1AudioDecoder decoder = new MPEG1AudioDecoder(is);
            short[] header = decoder.findHeader(null);
            for (int val : header) {
                System.out.println(val);
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error while reading MP3", e);
        }
    }*/

    void test002() {
        try (InputStream is = new BufferedInputStream(this.getClass().getResourceAsStream("/joint_stereo_kikuo.mp3"))){
            MPEG1AudioDecoder decoder = new MPEG1AudioDecoder(is);
            Header headerBuffer = new Header();
            Header header = decoder.findHeader2(headerBuffer);
            System.out.println(header);
            int layerToIndex = Header.LAYER_TO_INDEX[header.layer];
            int bitRate = Header.BIT_RATES[header.bitRateIndex][layerToIndex];
            int samplingFrequency = Header.SAMPLING_FREQUENCIES[header.samplingFrequency];
            System.out.println("BitRate: " + bitRate + " Sampling Frequency: " + samplingFrequency + " Padding: " + header.paddingBit);
            double slotDistance = 144 * ((double)bitRate / ((double)samplingFrequency / 1000d));
            System.out.println("Slot Distance: " + slotDistance);
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error while reading MP3", e);
        }
    }

    void test003() {
        try (InputStream is = new BufferedInputStream(this.getClass().getResourceAsStream("/test_song_001.mp3"))){
            MPEG1AudioDecoder decoder = new MPEG1AudioDecoder(is);
            Header headerBuffer = new Header();
            Header header = decoder.findHeader2(headerBuffer);
            System.out.println(header);
            int layerToIndex = Header.LAYER_TO_INDEX[header.layer];
            int bitRate = Header.BIT_RATES[header.bitRateIndex][layerToIndex];
            int samplingFrequency = Header.SAMPLING_FREQUENCIES[header.samplingFrequency];
            System.out.println("BitRate: " + bitRate + " Sampling Frequency: " + samplingFrequency + " Padding: " + header.paddingBit);
            double slotDistance = 144 * ((double)bitRate / ((double)samplingFrequency / 1000d));
            System.out.println("Slot Distance: " + slotDistance);
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error while reading MP3", e);
        }
    }

    void test004() {
        try (InputStream is = new BufferedInputStream(this.getClass().getResourceAsStream("/test_song_001.mp3"))){
            MPEG1AudioDecoder decoder = new MPEG1AudioDecoder(is);
            Header headerBuffer = new Header();
            Header header = decoder.findHeader2(headerBuffer);
            System.out.println(header);
            int layerToIndex = Header.LAYER_TO_INDEX[header.layer];
            int bitRate = Header.BIT_RATES[header.bitRateIndex][layerToIndex];
            int samplingFrequency = Header.SAMPLING_FREQUENCIES[header.samplingFrequency];
            System.out.println("BitRate: " + bitRate + " Sampling Frequency: " + samplingFrequency + " Padding: " + header.paddingBit);
            double slotDistance = 144 * ((double)bitRate / ((double)samplingFrequency / 1000d));
            System.out.println("Slot Distance: " + slotDistance);
            System.out.println("Protection bit " + header.protectionBit );
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error while reading MP3", e);
        }
    }
}
