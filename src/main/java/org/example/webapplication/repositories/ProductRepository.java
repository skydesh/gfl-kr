package org.example.webapplication.repositories;

import org.example.webapplication.Manufacturer;
import org.example.webapplication.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository {
    public Set<Product> findAllProducts(Set<Product> products) {
        return products;
    }

    public Set<Manufacturer> findAllManufacturer(Set<Manufacturer> manufacturers) {
        return manufacturers;
    }

    public boolean isManufacturerPresent(List<Manufacturer> manufacturers, String manufacturersName) {
        for (Manufacturer manufacturer : manufacturers) {
            if (manufacturer.getName().equals(manufacturersName)) {
                return true;
            }
        }
        return false;
    }

    public List<Product> findProductsByManufacturer(List<Product> products, String manufacturersName, String country) {
        return products.stream()
                .filter(product -> product.getManufacturer().getName().equals(manufacturersName) && product.getManufacturer().getCountry().equalsIgnoreCase(country)).toList();
    }

    public boolean isCountryPresent(List<Manufacturer> manufacturers, String country) {
        for (Manufacturer manufacturer : manufacturers) {
            if (manufacturer.getCountry().equals(country)) {
                return true;
            }
        }
        return false;
    }

    public List<Product> findProductsByCountry(Set<Product> products, String country) {
        return products.stream()
                .filter(product -> product.getManufacturer().getCountry().equals(country)).toList();
    }

    public List<Manufacturer> findByPriceLessThan(Set<Product> products, double price) {
        return products.stream()
                .filter(product -> product.getPrice() < price)
                .map(Product::getManufacturer).toList();
    }

    public Map<Manufacturer, List<Product>> getManufacturersProducts (Set<Product> products, Set<Manufacturer> manufacturers){
        Map<Manufacturer, List<Product>> manufacturerListMap = new HashMap<>();
        for (Manufacturer manufacturer : manufacturers) {
            manufacturerListMap.put(manufacturer, products.stream().filter(product -> product.getManufacturer().equals(manufacturer)).toList());
        }
        return manufacturerListMap;
    }

    public List<Manufacturer> findManufacturersByYearAndSouvenir(Set<Product> products, String souvenir, String year) {
        return products.stream()
                .filter(product -> product.getDate().split("\\.")[2].equals(year) && product.getName().equalsIgnoreCase(souvenir))
                .map(Product::getManufacturer).toList();
    }

    public Map<String, List<Product>> getProductsByYear (Set<Product> products){
        return products.stream()
                .collect(Collectors.groupingBy(product -> product.getDate().split("\\.")[2]));
    }

    public Optional<Product> getProduct(List<Product> products, String name, Manufacturer manufacturer){
        return products.stream()
                .filter(product -> product.getManufacturer()
                        .equals(manufacturer) && product.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<Product> getProduct(Set<Product> products, String name, String manufacturerName, String country){
        return products.stream()
                .filter(product -> product.getManufacturer().getName()
                        .equalsIgnoreCase(manufacturerName)
                        && product.getManufacturer().getCountry().equalsIgnoreCase(country)
                        && product.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Set<Product> deleteByManufacturer(Set<Product> products, Manufacturer manufacturer) {
        return products.stream().filter(product -> !product.getManufacturer().equals(manufacturer)).collect(Collectors.toSet());
    }

    public Set<Manufacturer> deleteManufacturer (Set<Manufacturer> manufacturers, Manufacturer manufacturer){
        return manufacturers.stream().filter(e -> !e.equals(manufacturer)).collect(Collectors.toSet());
    }

    public Manufacturer makeManufacturer(Scanner scanner){
        String manufacturerName;
        String country;
        System.out.println("Input the necessary values to update. " +
                "In case value should be left not upbdated - type '-': ");
        System.out.print("manufacturers name: ");
        manufacturerName = scanner.nextLine();
        System.out.print("manufacturers country: ");
        country = scanner.nextLine();
        return new Manufacturer(manufacturerName, country);
    }
    public Product makeProduct(Scanner scanner){
        String name;
        String date;
        Double price;
        String manufacturerName;
        String country;
        System.out.println("Input the necessary values to update. " +
                "In case value should be left not upbdated - type '-': ");
        System.out.print("name: ");
        scanner.skip("-");
        name = scanner.nextLine();
        System.out.print("date: ");scanner.skip("-");
        date = scanner.nextLine();
        System.out.print("price: ");scanner.skip("-");
        String getPrice = scanner.nextLine();
        if (getPrice.isEmpty()) {
            price = null;
        } else {
            price = Double.parseDouble(getPrice);
        }
        System.out.print("manufacturers name: ");
        scanner.skip("-");
        manufacturerName = scanner.nextLine();
        System.out.print("manufacturers country: ");
        scanner.skip("-");
        country = scanner.nextLine();
        return new Product(name, date, price, new Manufacturer(manufacturerName, country));
    }
    public Set<Manufacturer> postManufacturer(Set<Manufacturer> manufacturers, String name, String country){
        manufacturers.add(new Manufacturer(name, country));
        return manufacturers;
    }

    public Set<Product> postProduct(Set<Product> products, String name, String date, Double price,
                                    String manufacturerName, String country){
        products.add(new Product(name, date, price, new Manufacturer(manufacturerName, country)));
        return products;
    }
//    List<> Scanner scanner,List<Product>
    public Product updateProduct(Set<Product> products, Product product, String name,
                                       String manufacturerName, String country){
//        String manufacturerName = scanner.nextLine();
//        String country = scanner.nextLine();
//        new Manufacturer()
        Optional<Product> productOptional = getProduct(products, name, manufacturerName, country);
        if (!productOptional.isPresent()) {
            return null;
        }
        Product productToUpdate = productOptional.get();
//        , Manufacturer manufacturer
        if(!product.getName().isEmpty()) {
            productToUpdate.setName(product.getName());
        }
        if (!product.getDate().isEmpty()){
            productToUpdate.setDate(product.getDate());
        }
        if(product.getPrice() != null) {
            productToUpdate.setPrice(product.getPrice());
        }
        Manufacturer manufacturerToUpdate = productToUpdate.getManufacturer();
//                new Manufacturer();
        if(!product.getManufacturer().getName().isEmpty()){
            manufacturerToUpdate.setName(product.getManufacturer().getName());
//            manufacturerToUpdate.setCountry(productToUpdate.getManufacturer().getCountry());
//            productToUpdate.setManufacturer(manufacturerToUpdate);
        }
        if(!product.getManufacturer().getCountry().isEmpty()){
                manufacturerToUpdate.setCountry(product.getManufacturer().getCountry());
//                manufacturerToUpdate.setName(product.getManufacturer().getName());
//            productToUpdate.setManufacturer(manufacturerToUpdate);
        }
//        if(!product.getPrice())
//        Product product2 = getProduct(products, name, manufacturer);
//        Scanner scanner = new Scanner(System.in);
//        String n = null;
//        String n2 = null;
//        Double b = null;
//        scanner.skip("-");
//        n = scanner.nextLine();
//        n2 = scanner.nextLine();
//        b = scanner.nextDouble();
//        System.out.println(n.isBlank());
//        product.setName(n);
//        product.setDate(n2);
//        product.setPrice(b);
//        return products;
        return productToUpdate;
    }
}
