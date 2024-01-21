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
        options.imageWidth = image.getWidth();
        options.imageHeight = image.getHeight();
        graphics2D.drawImage(image, options.getX(), options.getY(), null);
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
        options.stringWidth = Editor.getStringWidth(text, options.font);
        graphics2D.setFont(options.font);
        graphics2D.setColor(options.color);
        graphics2D.drawString(text, options.getX(), options.getY());
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

}