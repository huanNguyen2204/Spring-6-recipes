package com.apress.spring6recipes.shop.config;

import com.apress.spring6recipes.shop.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Map;

@Configuration
@ComponentScan("com.apress.spring6recipes.shop")
public class ShopConfiguration {

    @Bean
    public Battery aaa() {
        return new Battery("AAA", 2.5, true); }
    @Bean
    public Disc cdrw() {
        return new Disc("CD-RW", 1.5, 700); }
    @Bean
    public Disc dvdrw() {
        return new Disc("DVD-RW", 3.0, 4700); }

    @Bean
    public DiscountFactoryBean discountFactoryBeanAAA(Battery aaa) {
        var factory = new DiscountFactoryBean();
        factory.setProduct(aaa);
        factory.setDiscount(0.2);
        return factory;
    }

    @Bean
    public DiscountFactoryBean discountFactoryBeanDVDRW(Disc dvdrw) {
        var factory = new DiscountFactoryBean();
        factory.setProduct(dvdrw);
        factory.setDiscount(0.1);
        return factory;
    }

//    @Bean
//    public ProductCreator productCreatorFactory() {
//        var products = Map.of(
//            "aaa", new Battery("AAA", 2.5, true),
//            "cdrw", new Disc("CD-RW", 1.5, 700),
//            "dvdrw", new Disc("DVD-RW", 3.0, 4700)
//        );
//        return new ProductCreator(products);
//    }
//
//    @Bean
//    public Product aaa(ProductCreator productCreator) {
//        return productCreator.createProduct("aaa");
//    }
//
//    @Bean
//    public Product cdrw(ProductCreator productCreator) {
//        return productCreator.createProduct("cdrw");
//    }
//
//    @Bean
//    public Product dvdrw(ProductCreator productCreator) {
//        return productCreator.createProduct("dvdrw");
//    }

//    @Bean
//    public ReloadableResourceBundleMessageSource messageSource() {
//        var messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasenames("classpath:messages");
//        messageSource.setCacheSeconds(1);
//        return messageSource
//    }
//
//
//    @Value("${endofyear.discount:0}")
//    private double specialEndofyearDiscountField;
//
//    @Value("classpath:banner.txt")
//    private Resource banner;
//
//    @Bean
//    public BannerLoader bannerLoader() {
//        return new BannerLoader(banner);
//    }
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer pspc() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//
//    @Bean
//    public Product dvdrw() {
//        return new Disc("DVD-RW", 1.5, 4700, specialEndofyearDiscountField);
//    }
//
//    @Bean
//    public Product aaa() {
//        return new Battery("AAA", 2.5, true);
//    }
//
//    @Bean
//    public Product cdrw() {
//        return new Disc("CD-RW", 1.5, 700);
//    }
//
//    @Bean
//    public Product dvdrw() {
//        return new Disc("DVD-RW", 3.0, 4900);
//    }
}
