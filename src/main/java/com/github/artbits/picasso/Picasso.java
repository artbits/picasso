/**
 * Copyright 2024 Zhang Guanhu
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.artbits.picasso;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.function.Consumer;

public final class Picasso {

    private BufferedImage bufferedImage;
    private Graphics2D graphics2D;


    private Picasso() { }


    public static Picasso init(int width, int height) {
        Picasso picasso = new Picasso();
        return picasso.canvas(null, width, height);
    }


    public static Picasso init(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            return new Picasso().canvas(image, image.getWidth(), image.getHeight());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static Picasso init(String url) {
        try {
            BufferedImage image = ImageIO.read(new URL(url));
            return new Picasso().canvas(image, image.getWidth(), image.getHeight());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Picasso canvas(BufferedImage image, int width, int height) {
        if (width < 0) {
            throw new RuntimeException("Parameter error!   width ∈ [0, +∞)");
        }
        if (height < 0) {
            throw new RuntimeException("Parameter error!   height ∈ [0, +∞)");
        }
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, null);
        Optional.ofNullable(image).ifPresent(i -> graphics2D.drawImage(i, 0, 0, null));
        return this;
    }


    private Picasso addImage(BufferedImage image, Consumer<Options> consumer) {
        Options options = new Options();
        if (consumer != null) {
            consumer.accept(options);
        } else {
            options.coordinates = true;
        }
        image = Editor.handle(image, options);
        graphics2D.drawImage(image, getX(image, options), getY(image, options), null);
        return this;
    }


    public Picasso addImage(File file, Consumer<Options> consumer) {
        try {
            return addImage(ImageIO.read(file), consumer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Picasso addImage(String url, Consumer<Options> consumer) {
        try {
            return addImage(ImageIO.read(new URL(url)), consumer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Picasso addText(String text, Consumer<Options> consumer) {
        Options options = new Options();
        consumer.accept(options);
        graphics2D.setFont(options.font);
        graphics2D.setColor(options.color);
        graphics2D.drawString(text, getX(text, options), getY(text, options));
        return this;
    }


    public File toFile(String path, Consumer<Options> consumer) {
        try {
            if (consumer != null) {
                Options options = new Options();
                consumer.accept(options);
                bufferedImage = Editor.handle(bufferedImage, options);
            }
            File file = new File(path);
            ImageIO.write(bufferedImage, "png", file);
            graphics2D.dispose();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public File toFile(String path) {
        return toFile(path, null);
    }


    public byte[] toBytes(Consumer<Options> consumer) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            if (consumer != null) {
                Options options = new Options();
                consumer.accept(options);
                bufferedImage = Editor.handle(bufferedImage, options);
            }
            ImageIO.write(bufferedImage, "png", stream);
            stream.flush();
            graphics2D.dispose();
            return stream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public byte[] toBytes() {
        return toBytes(null);
    }


    public BufferedImage toBufferedImage(Consumer<Options> consumer) {
        if (consumer != null) {
            Options options = new Options();
            consumer.accept(options);
            bufferedImage = Editor.handle(bufferedImage, options);
        }
        return bufferedImage;
    }


    public BufferedImage toBufferedImage() {
        return toBufferedImage(null);
    }


    private int getX(BufferedImage image, Options o) {
        return (o.coordinates) ? o.x : o.x - image.getWidth() / 2;
    }


    private int getY(BufferedImage image, Options o) {
        return (o.coordinates) ? o.y : o.y - image.getHeight() / 2;
    }


    private int getX(String s, Options o) {
        return (o.coordinates) ? o.x : o.x - Editor.getFontMetrics(o.font).stringWidth(s) / 2;
    }


    private int getY(String s, Options o) {
        return (o.coordinates) ? o.y : o.y - Editor.getFontMetrics(o.font).getHeight() / 2;
    }

}