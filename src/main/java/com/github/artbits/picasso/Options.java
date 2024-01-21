package com.github.artbits.picasso;

import java.awt.*;

public final class Options {
    public boolean coordinates;
    public int width;
    public int height;
    public int x;
    public int y;
    public int radius;
    public float scale;
    public float alpha;
    public float quality;
    public Color color;
    public Font font;
    Integer imageWidth;
    Integer imageHeight;
    Integer stringWidth;


    Options() { }


    int getX() {
        if (coordinates) {
            return x;
        }
        if (imageWidth != null) {
            return x - imageWidth / 2;
        }
        if (stringWidth != null) {
            return x - stringWidth / 2;
        }
        return x;
    }


    int getY() {
        return (coordinates || imageHeight == null) ? y : y - imageHeight / 2;
//        if (coordinates || image == null) {
//            return y;
//        } else {
//            return y - image.getHeight() / 2;
//        }
    }
}
