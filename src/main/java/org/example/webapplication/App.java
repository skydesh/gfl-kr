package org.example.webapplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.webapplication.repositories.ProductRepository;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

///*
public class App {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        ObjectMapper objectMapper = new ObjectMapper();
        Set<Product> products;
        Set<Manufacturer> manufacturers;
        File fileProducts = new File("./product.json");
        File fileManufacturers = new File("./manufacturers.json");
        try(Scanner scanner = new Scanner(System.in);
            FileWriter fileProductsWriter = new FileWriter(fileProducts, true);
            FileWriter fileManufacturersWriter = new FileWriter(fileManufacturers, true);
            BufferedReader bufferedReaderProducts = new BufferedReader(new FileReader(fileProducts));
            BufferedReader bufferedReaderManufacturers = new BufferedReader(new FileReader(fileManufacturers));
        ) {
            if (bufferedReaderProducts.readLine() == null) {
                System.out.println("File is empty. Please, add some souvenirs.");
                products = new HashSet<>();
            } else {products = objectMapper.readValue(fileProducts, new TypeReference<>() {});}

            if (bufferedReaderManufacturers.readLine() == null) {
                System.out.println("File is empty. Please, add some manufacturers");
                manufacturers = new HashSet<>();
            } else {manufacturers = objectMapper.readValue(fileManufacturers, new TypeReference<>() {});}
            
            System.out.println(
                    "Please select the filter option number and press Enter:"
            );
            System.out.println("""
                1 - Select all souvenirs;
                2 - Select all manufacturers;
                3 - Filter souvenirs by country;
                4 - Filter souvenirs by price less than;
                5 - Show souvenirs grouped by manufacturers;
                6 - Filter souvenirs by year;
                7 - Show souvenirs grouped by year;
                8 - Edit souvenir or manufacturer;
                9 - Delete souvenir or manufacturer;
                0 - Exit.
                """);
            System.out.println("Your choice: ");
            String userChoice = scanner.nextLine();


            switch (userChoice) {
                case"1" -> System.out.println(productRepository.findAllProducts(products));
                case "2" -> System.out.println(productRepository.findAllManufacturer(manufacturers));
                case "3" -> System.out.println(productRepository.findProductsByCountry(products, scanner.nextLine()));
                case "4" -> System.out.println(productRepository.findByPriceLessThan(products, scanner.nextDouble()));
                case "5" -> System.out.println(productRepository.getManufacturersProducts(products, manufacturers));
                case "6" -> System.out.println(productRepository.findManufacturersByYearAndSouvenir(products, scanner.nextLine(), scanner.nextLine()));
                case "7" -> System.out.println(productRepository.getProductsByYear(products));
                case "9" -> {
                    Manufacturer manufacturer = productRepository.makeManufacturer(scanner);
                    products = productRepository.deleteByManufacturer(products, manufacturer);
                    manufacturers = productRepository.deleteManufacturer(manufacturers, manufacturer);
                    objectMapper.writeValue(fileProducts, products);
                    objectMapper.writeValue(fileManufacturers, manufacturers);
                    System.out.println(products);
                    System.out.println(manufacturers);
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    public void productPrettyPrint(List<Product> products) {
        products.forEach(product -> System.out.println(product.getName() + ",\n" + product.getDate() + ",\n" + product.getPrice() + ";"));
    }
}
//*/
