package com.apress.spring6recipes.shop;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class Cashier implements BeanNameAware {

    private final String path;
    private String fileName;

    @Override
    public void setBeanName(String name) {
        this.fileName = name;
    }

    @Bean(initMethod = "openFile", destroyMethod = "closeFile")
    public Cashier cashier() {
        var path = System.getProperty("java.io.tmpdir") + "cashier";
        var cashier = new Cashier(path);
        return cashier;
    }

    private final String filename;
    private final String path;
    private BufferedWriter writer;

    public Cashier(String filename, String path) {
        this.filename = filename;
        this.path = path;
    }

    public void openFile() throws IOException {
        var checkoutPath = Path.of(path, filename + ".txt");

        if (Files.notExists(checkoutPath.getParent())) {
            Files.createDirectories(checkoutPath.getParent());
        }

        this.writer = Files.newBufferedWriter(
            checkoutPath,
            StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
            );
    }

    public void checkout(ShoppingCart cart) throws IOException {
        writer.write(LocalDateTime.now() + "\t" + cart.getItems() + "\r\n");
        writer.flush();
    }

    public void closeFile() throws IOException {
        writer.close();
    }
//    @Autowired
//    private MessageSource messageSource;
//
//    public void checkout(ShoppingCart cart) throws IOException {
//        var alert = messageSource.getMessage(
//            "alert.inventory.checkout",
//            new Object[] { cart.getItems(), new Date()},
//            Locale.US
//            );
//
//        System.out.println(alert);
//    }
}
