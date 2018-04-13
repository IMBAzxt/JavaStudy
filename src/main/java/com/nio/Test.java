/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author ThinkPad
 */
public class Test {
    private static int length = 0x2FFFFFFF;//1G

    public static void main(String[] args) {
        try {
//            FileChannel channel = FileChannel.open(Paths.get("F:\\W764_1.ova"),
//                    StandardOpenOption.READ, StandardOpenOption.WRITE);
            File file = new File("F:\\W764_1.ova");
            MappedByteBuffer mapBuffer = new FileInputStream(file).getChannel()
                    .map(FileChannel.MapMode.READ_ONLY, 0, 2000000000);
//            MappedByteBuffer mapBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, length);
            while (mapBuffer.hasRemaining()) {
                System.out.println(mapBuffer.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
