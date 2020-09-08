package Main;
import Electronic.MobilePhoneSpecifications;
import Electronic.TVSpecifications;
import Products.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {

    static Scanner scanner = new Scanner(System.in);

    static String inMarket = "Y";
    static Integer option = 0;
    static Double cost = 0.0;
    static Integer numberOfProducts = 0;

    static HashMap<String, Aliment> aliments = new HashMap<>();
    static HashMap<String, ElectronicMP> mobilePhones = new HashMap<>();
    static HashMap<String, ElectronicTV> TVS  = new HashMap<>();
    static ArrayList<Object> productList  = new ArrayList<Object>();
    static ShopingCart shopingCart = new ShopingCart();

    public static void main(String[] args) throws ParseException {

        aliments.put("Vegetable1", new Aliment("Tomato", 7, ProductType.Vegetable,
                              new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2020")));
        aliments.put("Vegetable2", new Aliment("Cucumber", 10, ProductType.Vegetable,
                new SimpleDateFormat("dd/MM/yyyy").parse("10/11/2020")));
        aliments.put("Fruit1", new Aliment("Peach", 4, ProductType.Fruit,
                new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2020")));

        MobilePhoneSpecifications mobile1 = new MobilePhoneSpecifications("S9+", 4.1, 64);
        MobilePhoneSpecifications mobile2 = new MobilePhoneSpecifications("S10+", 4.2, 128);
        ArrayList<MobilePhoneSpecifications> mobiles = new ArrayList<MobilePhoneSpecifications>();
        mobiles.add(mobile1);
        mobiles.add(mobile2);
        mobilePhones.put("Mobile1", new ElectronicMP("SamsungPhones", 3000,
                                  ProductType.Samsung, mobiles));

        TVSpecifications TV1 = new TVSpecifications("COD", 81, "HD");
        TVSpecifications TV2 = new TVSpecifications("REV", 120, "4K");
        ArrayList<TVSpecifications> televisions = new ArrayList<TVSpecifications>();
        televisions.add(TV1);
        televisions.add(TV2);
        TVS.put("TV1", new ElectronicTV("LG TV", 2000, ProductType.LG,televisions));

        System.out.println("***************** Welcome To Andrei's Supermarket *****************");

        String logIn = "";
        HashMap<String, String> userType = new HashMap<String, String>();
        userType.put("1", "Admin");
        userType.put("2", "Client");

        while(!logIn.equalsIgnoreCase("exit")) {

            System.out.println("\n What kind of user are you ? \n " +
                    "1.Admin \n " +
                    "2.Client");

            String user = scanner.next();

            while(!user.equalsIgnoreCase("1") && !user.equalsIgnoreCase("2") ){
                System.out.println("This type of user doesn't exist! \n Please enter a valid one: ");
                user = scanner.next();
            }
            Integer tries = 3;

            if(userType.get(user).equalsIgnoreCase("Admin")){
                System.out.println("\n You need to enter the password to log in: ");
                String password = scanner.next();
                while(tries != 1) {
                    if(password.equals("admin")) {
                        System.out.println("\n You've logged in successfully, " + userType.get(user));
                        break;
                    }else{
                        tries--;
                        System.out.println("\n Wrong password, please reenter it! Tries left : " + tries);
                        password = scanner.next();
                        if(tries == 1){
                            System.out.println("You've enter the password wrong too much time!");
                            break;
                        }
                    }

                }
            }else {
                System.out.println("\n You've logged in successfully, " + userType.get(user));
            }

            if(tries != 1) {
                switch (userType.get(user)) {
                    case "Admin":
                        inMarket = "Y";
                        Admin();
                        break;
                    case "Client":
                        inMarket = "Y";
                        Client();
                        break;
                }
            }

                System.out.println("Do you want to log again?(Y/exit)");
                logIn = scanner.next();
        }
    }

    public static void Admin() throws ParseException {
        while (inMarket.equalsIgnoreCase("Y")) {

            System.out.println(" Here are the options:\n "+
                    "1.Add a product \n" +
                    " 2.Get the details about a product \n" +
                    " 3.Change something about a product!");
            while(true) {
                try {
                    option = scanner.nextInt();
                    break;
                }catch (Exception e){
                    System.out.println("WrongInput");
                    scanner.next();
                    continue;
                }
            }
            switch(option) {
                case 1:
                    System.out.println("You chose to add a product!");
                    addProduct();
                    break;
                case 2:
                    System.out.println("You chose to get details about a product!");
                    getDetails();
                    break;
                case 3:
                    System.out.println("You chose to change something about a product!");
                    changeSomething();
                    break;
                default:
                    System.out.println("There is no option of this kind!");
            }

            System.out.println("Do you still want to do something? (Y/N)");
            inMarket = scanner.next();

        }
    }

    public static void Client() {
        while (inMarket.equalsIgnoreCase("Y")) {

            System.out.println("\n Here are the options:\n " +
                    "1.Buy a product! \n" +
                    " 2.Get the details about a product \n" +
                    " 3.See your shop cart! \n" +
                    " 4.Remove a product from the basket! \n" +
                    " 5.Buy all!(end of the shopping!)");
            while(true) {
                try {
                    option = scanner.nextInt();
                    break;
                }catch (Exception e){
                    System.out.println("WrongInput");
                    scanner.next();
                    continue;
                }
            }
            switch (option) {
                case 1:
                    System.out.println("You chose to buy a product!");
                    numberOfProducts++;
                    buyProduct();
                    break;
                case 2:
                    System.out.println("You chose to get details about a product!");
                    getDetails();
                    break;
                case 3:
                    System.out.println("You chose to see your shop cart!");
                    cartShop();
                    break;
                case 4:
                    System.out.println("You chose to remove a product from your cart!");
                    numberOfProducts++;
                    removeitem();
                    break;
                case 5:
                    System.out.println("You chose to buy all!");
                    inMarket = "N";
                    cartShop();
                    break;
                default:
                    System.out.println("There is no option of this kind!");
                    break;
            }
            if (inMarket.equalsIgnoreCase("N")) {
                Payment();
                break;
            } else {
                System.out.println("Do you still want to do something? (Y/N)");
                inMarket = scanner.next();
            }
        }
    }

    public static void addProduct() throws ParseException {
        System.out.println("What kind of product do you want to add?(Aliment/Electronic)");
        String product = scanner.next();

        if(product.equalsIgnoreCase("Aliment")){
            createAliment();
        }else if(product.equalsIgnoreCase("Electronic")){
            System.out.println("What kind of Electronic do you want to add? (Mobile/TV) ");
            String type = scanner.next();

            if (type.equalsIgnoreCase("Mobile")){
                createMobilePhone();

            }else if(type.equalsIgnoreCase("TV")){
                createTV();
            }else{
                System.out.println("We don't have this kind of electronic");
            }
        }else {
            System.out.println("We don't have this kind of category");
        }

        System.out.println("There are all the products in the Stock:");
        System.out.println("ALIMENTS:");
        aliments.forEach((Code,aliment) -> System.out.println(Code + " " + aliment));
        System.out.println("MOBILE PHONES:");
        mobilePhones.forEach((Code,mobile) -> System.out.println(Code + " " + mobile));
        System.out.println("TVS:");
        TVS.forEach((Code,tv) -> System.out.println(Code + " " + tv));

    }

    public static void createAliment() throws ParseException {
        System.out.println("What kind of aliment is: (Vegetable/Fruit)");
        String type = scanner.next();
        if(type.equalsIgnoreCase("Vegetable")) {
            System.out.println("Enter the " + ProductType.Vegetable + "(name, price and expiration date) + the Code: ");
            String name = scanner.next();
            Integer price = 0;
            while (true) {
                try {
                    price = Integer.parseInt(scanner.next());
                    break;
                } catch (Exception e) {
                    System.out.println("You must enter a number!");
                    scanner.next();
                    continue;
                }
            }
            Date expirationDate = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
            String Code = scanner.next();
            Aliment aliment = new Aliment(name, price, ProductType.Vegetable, expirationDate);
            aliment.inStock();
            aliments.put(Code, aliment);
        }else if(type.equalsIgnoreCase("Fruit")){
            System.out.println("Enter the " + ProductType.Fruit + "(name, price and expiration date) + the Code: ");
            String name = scanner.next();
            Integer price = 0;
            while (true) {
                try {
                    price = Integer.parseInt(scanner.next());
                    break;
                } catch (Exception e) {
                    System.out.println("You must enter a number!");
                    scanner.next();
                    continue;
                }
            }
            Date expirationDate = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
            String Code = scanner.next();
            Aliment aliment = new Aliment(name,price,ProductType.Fruit,expirationDate);
            aliment.inStock();
            aliments.put(Code, aliment);
        }else{
            System.out.println("We don't have this category of aliment");
        }

    }

    public static void createMobilePhone(){
        ArrayList<MobilePhoneSpecifications> phoneListSpecifications = new ArrayList<MobilePhoneSpecifications>();

        while(scanner.next().equalsIgnoreCase("enough")) {

            System.out.println("Enter a MobilePhoneSpecifications(model, display size and memory(64, 128, 256) in the list: ");

            String model = scanner.next();
            Double display = Double.parseDouble(scanner.next());
            Integer memory = Integer.parseInt(scanner.next());

            MobilePhoneSpecifications phone = new MobilePhoneSpecifications(model, display , memory);
            phoneListSpecifications.add(phone);
        }

        System.out.println("Enter an ElectronicMP(name, price) + the Code: ");
        String name = scanner.next();
        Double price = Double.parseDouble(scanner.next());
        String Code = scanner.next();
        System.out.println("What is the brand of the phone?(Samsung,LG or Apple) ");
        if(scanner.next().equalsIgnoreCase("Samsung")){
            ElectronicMP electronic1 = new ElectronicMP(name, price, ProductType.Samsung, phoneListSpecifications);
            electronic1.inStock();
            mobilePhones.put(Code, electronic1);
        }else if(scanner.next().equalsIgnoreCase("LG")){
            ElectronicMP electronic2 = new ElectronicMP(name, price, ProductType.LG, phoneListSpecifications);
            electronic2.inStock();
            mobilePhones.put(Code, electronic2);
        }else if(scanner.next().equalsIgnoreCase("Apple")){
            ElectronicMP electronic3 = new ElectronicMP(name, price, ProductType.Apple, phoneListSpecifications);
            electronic3.inStock();
            mobilePhones.put(Code, electronic3);
        }else{
            System.out.println("We don't have this kind of brand!");
        }

    }

    public static void createTV(){

        ArrayList<TVSpecifications> TVSpecificationsList = new ArrayList<TVSpecifications>();

        while(scanner.next().equalsIgnoreCase("enough")) {

            System.out.println("Enter a TVSpecifications (model, display size and resolution(HD, FULL HD, 4K)): ");

            String model = scanner.next();
            Integer display = Integer.parseInt(scanner.nextLine());
            String resolution = scanner.next();
            TVSpecifications television = new TVSpecifications(model, display, resolution);
            TVSpecificationsList.add(television);

        }

        System.out.println("Enter an ElectronicTV(name, price) + The Code: ");
        String name = scanner.next();
        Double price = Double.parseDouble(scanner.nextLine());
        String Code = scanner.next();

        System.out.println("What is the brand of the TVSpecifications?(Samsung,LG or Apple) ");
        if(scanner.next().equalsIgnoreCase("Samsung")){
            ElectronicTV electronic1 = new ElectronicTV(name, price, ProductType.Samsung, TVSpecificationsList);
            electronic1.inStock();
            TVS.put(Code, electronic1);
        }else if(scanner.next().equalsIgnoreCase("LG")){
            ElectronicTV electronic2 = new ElectronicTV(name, price, ProductType.LG, TVSpecificationsList);
            electronic2.inStock();
            TVS.put(Code, electronic2 );
        }else if(scanner.next().equalsIgnoreCase("Apple")){
            ElectronicTV electronic3 = new ElectronicTV(name, price, ProductType.Apple, TVSpecificationsList);
            electronic3.inStock();
            TVS.put(Code,electronic3);
        }else{
            System.out.println("We don't have this kind of brand!");
        }
    }

    public static void getDetails(){
        System.out.println("What's kind of product it is ?(Aliment/ElectronicMP/ElectronicTV)");
        String product = scanner.next();
        if (product.equalsIgnoreCase("Aliment")){
            System.out.println("What's is the name?");
            String name = scanner.next();
            aliments.forEach((Code, aliment) -> System.out.println(aliment.Exists(name)));


        }else if (product.equalsIgnoreCase("ElectronicMP")){
            System.out.println("What's is the name?");
            String name = scanner.next();
            mobilePhones.forEach((Code, mobile) -> System.out.println(mobile.Exists(name)));

        }else if (product.equalsIgnoreCase("ElectronicTV")){
            System.out.println("What's is the name?");
            String name = scanner.next();
            TVS.forEach((Code, TV) -> System.out.println(TV.Exists(name)));

        }else{
            System.out.println("We don't have this kind of Product!");
        }
    }

    public static void changeSomething(){
        System.out.println("What's kind of product it is ?(Aliment/ElectronicMP/ElectronicTV)");
        String product = scanner.next();
        if (product.equalsIgnoreCase("Aliment")){
            System.out.println("What's is the name?");
            String name = scanner.next();
            System.out.println("What do you want to change?(name, price)");
            String change = scanner.next();
            if(change.equalsIgnoreCase("name")){
                System.out.println("What will be the new name?");
                String newName = scanner.next();
                aliments.forEach((Code, aliment) -> aliment.changeName(name, newName));
            }else if(change.equalsIgnoreCase("price")){
                System.out.println("What will be the new price?");
                Double newPrice = Double.parseDouble(scanner.next());
                aliments.forEach((Code, aliment) -> aliment.changePrice(name, newPrice));
            }
        }else if (product.equalsIgnoreCase("ElectronicMP")){
            System.out.println("What's is the name?");
            String name = scanner.next();
            System.out.println("What do you want to change?(name, price)");
            String change = scanner.next();
            if(change.equalsIgnoreCase("name")){
                System.out.println("What will be the new name?");
                String newName = scanner.next();
                mobilePhones.forEach((Code, mobile) -> mobile.changeName(name, newName));
            }else if(change.equalsIgnoreCase("price")){
                System.out.println("What will be the new price?");
                Double newPrice = Double.parseDouble(scanner.nextLine());
                mobilePhones.forEach((Code, mobile) -> mobile.changePrice(name, newPrice));
            }
        }else if (product.equalsIgnoreCase("ElectronicTV")){
            System.out.println("What's is the name?");
            String name = scanner.nextLine();
            System.out.println("What do you want to change?(name, price)");
            String change = scanner.nextLine();
            if(change.equalsIgnoreCase("name")){
                System.out.println("What will be the new name?");
                String newName = scanner.nextLine();
                TVS.forEach((Code, TV) -> TV.changeName(name, newName));
            }else if(change.equalsIgnoreCase("price")){
                System.out.println("What will be the new price?");
                Double newPrice = Double.parseDouble(scanner.nextLine());
                TVS.forEach((Code, TV) -> TV.changePrice(name, newPrice));
            }
        }else{
            System.out.println("We don't have this kind of product!");
        }
    }

    private static void buyProduct() {

        System.out.println("What do you want to buy?(Aliment/ElectronicMP/ElectronicTV)");
        String preferece = scanner.next();

        if (preferece.equalsIgnoreCase("Aliment")){
            while(true) {
                try {
                    System.out.println("This is a list of all the Aliments: ");
                    aliments.forEach((Code, aliment) -> System.out.println(aliment.toString() + " and has the Code: " + Code));
                    System.out.println("Type the Code of the Aliment that you want: ");
                    String buy = scanner.next();
                    cost += aliments.get(buy).getPrice();
                    productList.add(aliments.get(buy));
                    shopingCart.setCost(cost);
                    shopingCart.setProductList(productList);
                    aliments.get(buy).addInTheCart();
                    break;
                } catch (NullPointerException e) {
                    System.out.println("There is a problem!Try to reenter the Code:");
                    continue;
                }
            }

        }else if(preferece.equalsIgnoreCase("ElectronicMP")){
            System.out.println("This is a list of all the ElectronicMP: ");
            mobilePhones.forEach((Code,mobile) -> System.out.println(mobile.toString() + " and has the Code: " + Code));

            System.out.println("Type the Code of the MobilePhone that you want: ");
            String buy = scanner.next();
            productList.add(mobilePhones.get(buy));
            cost += mobilePhones.get(buy).getPrice();
            shopingCart.setCost(cost);
            shopingCart.setProductList(productList);


        }else if(preferece.equalsIgnoreCase("ElectronicTV")){
            System.out.println("This is a list of all the ElectronicTV: ");
            TVS.forEach((Code,TV) -> System.out.println(TV.toString() + " and has the Code: " + Code));
            System.out.println("Type the Code of the TV that you want: ");
            String buy = scanner.next();
            productList.add(TVS.get(buy));
            cost += TVS.get(buy).getPrice();
            shopingCart.setCost(cost);
            shopingCart.setProductList(productList);

        }else{
            System.out.println("We don't have this kind of product!");
        }
    }

    private static void cartShop() {

        if(inMarket.equalsIgnoreCase("N")){
            System.out.println("This is your final cart: ");
            shopingCart.printAll();
            System.out.println("Give us you details and the Address to deliver your order:");
            Scanner sc = new Scanner(System.in);
            String details = sc.nextLine();
            shopingCart.setDetails(details);

            String address = sc.nextLine();
            shopingCart.setDeliveryAddress(address);
        }else{
            shopingCart.printAll();
        }
    }

    private static void removeitem() {
        shopingCart.printAll();
        while (true) {
            try {
                System.out.println("What do you want to remove? (write the name)");
                String remove = scanner.next();

                if (shopingCart.findName(remove) != null) {
                    ArrayList<Object> objectToRemove = shopingCart.findName(remove);
                    shopingCart.getProductList().remove(objectToRemove.get(0));
                    Double costToRemove = Double.parseDouble(objectToRemove.get(1).toString());
                    cost -= costToRemove;
                    shopingCart.setCost(cost);
                }
                break;
            } catch (Exception e) {
                System.out.println("You don't have this product in the Cart! Enter another one:");
                scanner.next();
                continue;
            }
        }
        System.out.println("This is your new cart:");
        shopingCart.printAll();

    }

    private static void Payment() {
        System.out.println("It is all ok?");
        shopingCart.printAllDetails();
        String verification = scanner.next();
        while(verification.equalsIgnoreCase("No")){

            Scanner sc = new Scanner(System.in);
            String details = scanner.nextLine();
            shopingCart.setDetails(details);

            String address = scanner.nextLine();
            shopingCart.setDeliveryAddress(address);

            sc.close();

            System.out.println("It is all ok?");
            verification = scanner.next();
        }

        if(cost > 200 || numberOfProducts > 5){
            System.out.println("There is a discount because you have > 200 or 5 products in the cart!");
            shopingCart.sale();
        }

        System.out.println("This is the total of your cart: " + cost);
        System.out.println("Do you want to buy it?");
        String answer = scanner.next();
        if(answer.equalsIgnoreCase("Yes")) {
            System.out.println("The order has been send successfully!");

            cost = 0.0;
            shopingCart.setCost(cost);
            shopingCart.setDeliveryAddress(" ");
            shopingCart.setDetails(" ");
            shopingCart.setProductList(null);
        }else{
            System.out.println("Why? (buy,remove)");
            String answer2 = scanner.next();

            switch (answer2){
                case "buy":
                    buyProduct();
                    break;
                case "remove":
                    removeitem();
                    break;
                default:
                    System.out.println("Sorry no other option!");
                    break;
            }
        }
    }

}
