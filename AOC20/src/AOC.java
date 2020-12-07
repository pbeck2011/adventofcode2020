import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC {


    public static void main(String[] args) {

        //exercise1();
        //exercise2();
        //exercise3();
        //exercise4();
        //exercise5();
        //exercise6();
        exercise7();

    }

    //---------------------------------------
    //--################ 1 ################--
    //---------------------------------------

    public static void exercise1() {

        List<Integer> values = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ypreb\\Desktop\\aoc\\input1.txt"));
            String line = reader.readLine();
            while (line != null) {

                //Process Line
                values.add(Integer.parseInt(line));
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // First question
        getProductFromTwo(2020, values);
        System.out.println("--------------------");

        // Second question

        int product = 0;

        for(int i = 0; i < values.size(); i++) {
            int sum = 2020 - values.get(i);
            int result = getProductFromTwo(sum, values);

            if(result != 0) {
                System.out.println("Factor3: " + values.get(i));
                product = values.get(i) * result;
                break;
            }
        }

        System.out.println("Product: " + product);

    }
    public static int getProductFromTwo(int sum, List<Integer> values) {
        boolean solved = false;
        int factor1 = 0;
        int factor2 = 0;

        for(int i = 0; i < values.size(); i++) {

            if(solved) break;

            int current = values.get(i);
            int required = sum - current;

            for(int j = 0; j < values.size(); j++) {
                if(values.get(j) == required) {

                    factor1 = current;;
                    factor2 = required;
                    solved = true;
                    break;
                }

            }

        }



        if(solved) {

            System.out.println("Factor1: " + factor1);
            System.out.println("Factor2: " + factor2);
            System.out.println("Product: " + factor1*factor2);

            return factor1 * factor2;
        }
        else {
            return 0;
        }

    }

    //---------------------------------------
    //--################ 2 ################--
    //---------------------------------------

    public static void exercise2() {

        List<String> values = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ypreb\\Desktop\\aoc\\input2.txt"));
            String line = reader.readLine();
            while (line != null) {

                //Process Line
                values.add(line);
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // First question
        int counter = 0;

        for(String line: values) {
            if(isValidPassword(line)) {
                counter++;
            }
        }

        System.out.println("Number of correct passwords: " + counter);


        // Second question
        counter = 0;

        for(String line: values) {
            if(isActuallyAValidPassword(line)) {
                counter++;
            }
        }

        System.out.println("Number of correct passwords: " + counter);

    }
    public static boolean isValidPassword(String line) {

        int lowest = Integer.parseInt(line.split("-")[0]);
        int highest = Integer.parseInt(line.split("-")[1].split(" ")[0]);
        char letter = line.charAt(line.indexOf(':')-1);
        String password = line.split("-")[1].split(" ")[2];

        if(password.chars().filter(ch -> ch == letter).count() < lowest || password.chars().filter(ch -> ch == letter).count() > highest) {
            return false;
        }

        return true;
    }
    public static boolean isActuallyAValidPassword(String line) {
        int first = Integer.parseInt(line.split("-")[0]);
        int second = Integer.parseInt(line.split("-")[1].split(" ")[0]);
        char letter = line.charAt(line.indexOf(':')-1);
        String password = line.split("-")[1].split(" ")[2];

        if(password.charAt(first-1) == letter && password.charAt(second-1) == letter) {
            return false;
        }
        if(password.charAt(first-1) != letter && password.charAt(second-1) != letter) {
            return false;
        }

        return true;
    }

    //---------------------------------------
    //--################ 3 ################--
    //---------------------------------------

    public static void exercise3() {

        List<String> values = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ypreb\\Desktop\\aoc\\input3.txt"));
            String line = reader.readLine();
            while (line != null) {

                //Process Line
                values.add(line);
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // First question
        System.out.println("First question: I encountered " + numberOfTrees(3,1, values) + " trees!");

        // Second question

        int totalAmountOfTrees =
                numberOfTrees(1,1,values)
                * numberOfTrees(3,1,values)
                * numberOfTrees(5,1,values)
                * numberOfTrees(7,1,values)
                * numberOfTrees(1,2,values);

        System.out.println("Second question: The product of all trees is: " + totalAmountOfTrees);


    }
    public static int numberOfTrees(int x, int y, List<String> values) {

        int trees = 0;
        int dx = 0;

        for(int dy = 0; dy < values.size(); dy += y) {
            String current = values.get(dy);
            if(current.charAt(dx % current.length()) == '#') trees++;
            dx += x;
        }
        return trees;
    }

    //---------------------------------------
    //--################ 4 ################--
    //---------------------------------------

    public static void exercise4() {

        List<String> values = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ypreb\\Desktop\\aoc\\input4.txt"));
            String line = reader.readLine();
            while (line != null) {

                //Process Line

                StringBuilder passport = new StringBuilder(line);
                System.out.println(line);
                line = reader.readLine();

                while(!line.equals("")) {
                    passport.append(" ").append(line);
                    line = reader.readLine();
                    if(line == null) break;
                }

                values.add(passport.toString());
                System.out.println(passport);
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // First question
        System.out.println("First question: Number of correct Passports is " + countValidPassportsFromFields(values));

        // Second question
        System.out.println("Second question: Number of correct Passports is " + countValidPassportsFromData(values));

    }
    public static int countValidPassportsFromFields(List<String> values) {

        int counter = 0;

        for(int i = 0; i < values.size(); i++) {
            String current = values.get(i);
            if(hasAllImportantFields(current)) ++counter;
        }

        return counter;
    }
    public static int countValidPassportsFromData(List<String> values) {

        int counter = 0;

        for(int i = 0; i < values.size(); i++) {
            System.out.println("NEW PASSPORT: NUMBER " + i);
            String current = values.get(i);
            if(hasAllImportantFields(current)) {
                String[] fields = current.split(" ");
                boolean valid = true;

                for(int j = 0; j < fields.length; j++) {
                    String[] entry = fields[j].split(":");
                    String field = entry[0];
                    String value = entry[1];
                    System.out.println("Now checking field '" + field + "' and value '" + value + "'");

                    switch (field) {
                        case "byr":
                            if(Integer.parseInt(value) < 1920 || Integer.parseInt(value) > 2002) {
                                System.out.println("FALSE: field " + field + " had a value " + value + " which is out of range");
                                valid = false;
                                break;
                            }
                            break;

                        case "iyr":
                            if(Integer.parseInt(value) < 2010 || Integer.parseInt(value) > 2020) {
                                System.out.println("FALSE: field " + field + " had a value " + value + " which is out of range");
                                valid = false;
                                break;
                            }
                            break;

                        case "eyr":
                            if(Integer.parseInt(value) < 2020 || Integer.parseInt(value) > 2030) {
                                System.out.println("FALSE: field " + field + " had a value " + value + " which is out of range");
                                valid = false;
                                break;
                            }
                            break;

                        case "hgt":

                            if(!value.contains("cm") && !value.contains("in")) {
                                System.out.println("FALSE: field " + field + " had a value " + value + " which is missing a unit");
                                valid = false;
                                break;
                            }
                            int height = Integer.parseInt(value.substring(0, value.length()-2));
                            String unit = value.substring(value.length()-2);

                            if(unit.equals("in")) {
                                if(height < 59 || height > 76) {
                                    System.out.println("FALSE: field " + field + " had a value " + value + " which is out of inch range");
                                    valid = false;
                                    break;
                                }
                            } else if(unit.equals("cm")) {
                                if(height < 150 || height > 193) {
                                    System.out.println("FALSE: field " + field + " had a value " + value + " which is out of CM range");
                                    valid = false;
                                    break;
                                }
                            }
                            break;


                        case "hcl":

                            if(!value.substring(0, 1).equals("#")) {
                                System.out.println("FALSE: field " + field + " had a value " + value + " which does not start with #");
                                valid = false;
                                break;
                            }

                            String hexcode = value.substring(1);
                            if(!hexcode.matches("^[0-9a-f]+$")) {
                                System.out.println("FALSE: field " + field + " had a value " + value + " which contains illegal characters");
                                valid = false;
                                break;
                            }
                            break;

                        case "ecl":

                            List<String> eyecolors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
                            if(!eyecolors.contains(value)) {
                                System.out.println("FALSE: field " + field + " had a value " + value + " which is not a valid color code");
                                valid = false;
                                break;
                            }
                            break;

                        case "pid":

                            if(value.length() != 9) {
                                System.out.println("FALSE: field " + field + " had a value " + value + " which doesn't have the length of 9");
                                valid = false;
                                break;
                            }

                            if(!value.matches("^[0-9]+$")) {
                                System.out.println("FALSE: field " + field + " had a value " + value + " which has illegal characters");
                                valid = false;
                                break;
                            }
                            break;

                    }
                }

                if (valid) ++counter;
            }

        }


        return counter;
    }
    public static boolean hasAllImportantFields(String current) {
        return current.contains("byr") && current.contains("iyr") && current.contains("eyr") && current.contains("hgt") &&
                current.contains("hcl") && current.contains("ecl") && current.contains("pid");
    }

    //---------------------------------------
    //--################ 5 ################--
    //---------------------------------------

    public static void exercise5() {

        List<String> values = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ypreb\\Desktop\\aoc\\input5.txt"));
            String line = reader.readLine();
            while (line != null) {

                //Process Line
                values.add(line);
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // First question
        System.out.println("First question: The highest ID is " + getHighestID(values));


        // Second question
        // Is already answered in another function

    }
    public static int findMissingID(List<Integer> ids) {

        for(int i = 0; i < ids.size()-1; i++) {
            int current = ids.get(i);
            int next = ids.get(i+1);
            if(next-current != 1) {
                return current+1;
            }
        }
        return 0;
    }
    public static int getHighestID(List<String> values) {

        List<Integer> ids = new ArrayList<>();
        int best = 0;

        for (String value : values) {
            ids.add(getIDFromInput2(value));
            if (best < getIDFromInput2(value)) best = getIDFromInput2(value);
        }

        Collections.sort(ids);
        System.out.println("Second question: My ID is " + findMissingID(ids));

        return best;
    }
    public static int getIDFromInput(String input) {

        String row = input.substring(0,7);
        String column = input.substring(7);

        int[] rowNumbers = new int[128];
        for(int i = 0; i < 128; i++) {
            rowNumbers[i] = i;
        }
        int[] colNumbers = new int[8];
        for(int i = 0; i < 8; i++) {
            colNumbers[i] = i;
        }

        for(int i = 0; i < column.length(); i++) {
            char pos = column.charAt(i);
            if(pos == 'L') colNumbers = Arrays.copyOfRange(colNumbers, 0, colNumbers.length/2);
            if(pos == 'R') colNumbers = Arrays.copyOfRange(colNumbers, colNumbers.length/2, colNumbers.length);
        }
        for(int i = 0; i < row.length(); i++) {
            char pos = row.charAt(i);
            if(pos == 'F') rowNumbers = Arrays.copyOfRange(rowNumbers, 0, rowNumbers.length/2);
            if(pos == 'B') rowNumbers = Arrays.copyOfRange(rowNumbers, rowNumbers.length/2, rowNumbers.length);
        }

        int res_row = rowNumbers[0];
        int res_col = colNumbers[0];
        System.out.println("For String " + row + ", the row number is " + res_row);
        System.out.println("For String " + column + ", the col number is " + res_col);

        return res_row * 8 + res_col;
    }
    public static int getIDFromInput2(String input) {

        String row = input.substring(0,7).replace("B","1").replace("F","0");
        String column = input.substring(7).replace("R","1").replace("L","0");
        return (Integer.parseInt(row, 2) * 8 + Integer.parseInt(column, 2));

    }

    //---------------------------------------
    //--################ 6 ################--
    //---------------------------------------

    public static void exercise6() {

        List<String> values = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ypreb\\Desktop\\aoc\\input6.txt"));
            String line = reader.readLine();
            while (line != null) {

                //Process Line

                StringBuilder answer = new StringBuilder(line);
                line = reader.readLine();

                while(!line.equals("")) {
                    answer.append(",").append(line);
                    line = reader.readLine();
                    if(line == null) break;
                }

                values.add(answer.toString());
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // First question
        System.out.println("First question: The sum of all answered questions is " + sumOfCounts(values));

        // Second question
        System.out.println("Second question: The sum of all answered questions is " + realSumOfCounts(values));

    }
    public static int sumOfCounts(List<String> values) {

        int sum = 0;
        for(int i = 0; i < values.size(); i++) {
            sum += differentCharacters(values.get(i));
        }
        return sum;
    }
    public static int realSumOfCounts(List<String> values) {
        int sum = 0;
        for(int i = 0; i < values.size(); i++) {
            sum += everyoneAnswered(values.get(i));
        }
        return sum;
    }
    public static int differentCharacters(String answer) {

        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int counter = 0;

        for(int i = 0; i < alphabet.length; i++) {
            if(answer.contains(Character.toString(alphabet[i]))) counter++;
        }

        return counter;
    }
    public static int everyoneAnswered(String answer) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int counter = 0;
        String[] answers = answer.split(",");

        for(int i = 0; i < alphabet.length; i++) {
            if(answer.contains(Character.toString(alphabet[i]))) {
                boolean valid = true;
                for(int j = 0; j < answers.length; j++) {
                    if(!answers[j].contains(Character.toString(alphabet[i]))) {
                        valid = false;
                        break;
                    }
                }
                if(valid) counter++;
            }
        }

        return counter;
    }

    //---------------------------------------
    //--################ 7 ################--
    //---------------------------------------

    public static void exercise7() {

        List<String> values = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ypreb\\Desktop\\aoc\\input7.txt"));
            String line = reader.readLine();
            while (line != null) {

                //Process Line
                values.add(line);
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // First question
        System.out.println("First question: The number of possible gold bag holders is " + numberOfPossibleGoldBagHolders(values));

        // Second question
        System.out.println("Second question: The total number of required bags is " + numberOfRequiredBags(values));
    }
    public static class Bag {

        String name;
        List<Pair<Integer, String>> contents;

        Bag(String aName) {
            this.name = aName;
            this.contents = new ArrayList<>();
        }

        void addContent(int amt, String name) {
            Pair<Integer, String> pair = new Pair<>(amt, name);
            this.contents.add(pair);
        }

        String getName() {
            return this.name;
        }

        List<Pair<Integer, String>> getContents() {
            return this.contents;
        }

    }
    public static int numberOfPossibleGoldBagHolders(List<String> rules) {

        int counter = 0;

        List<Bag> bags = createBagListFromStringList(rules);
        List<String> possibleBagHolder = new ArrayList<>();

        int iter = 0;
        boolean stop = false;
        List<String> holderCopy;

        do {
            System.out.println("--------------");
            int oldSize = possibleBagHolder.size();
            holderCopy = possibleBagHolder;

            System.out.println("old size:"+oldSize);
            iter++;
            for (Bag currentBag : bags) {

                if (iter == 1) {
                    // check direct in first iteration
                    for (int j = 0; j < currentBag.contents.size(); j++) {
                        String currentContent = appendSToBag(currentBag.contents.get(j).getValue());

                        if (currentContent.equals("shiny gold bags") || currentContent.equals("shiny gold bag")) {
                            possibleBagHolder.add(appendSToBag(currentBag.getName()));
                            counter++;
                            break;
                        }
                    }
                } else {
                    // check indirect in all subsequent iterations
                    for (int j = 0; j < currentBag.contents.size(); j++) {
                        String currentContent = appendSToBag(currentBag.contents.get(j).getValue());

                        if (holderCopy.contains(currentContent)) {
                            if(!possibleBagHolder.contains(currentBag.getName())) {
                                possibleBagHolder.add(appendSToBag(currentBag.getName()));
                                counter++;
                                break;
                            }
                        } else if (holderCopy.contains(currentContent.substring(0,currentContent.length()-1))) {
                            if(!possibleBagHolder.contains(currentBag.getName())) {
                                String added = currentBag.getName() + "s";
                                possibleBagHolder.add(added);
                                counter++;
                                break;
                            }
                        }
                    }
                }
            }

            for (String currentlyRemoving : possibleBagHolder) {
                bags.removeIf(n -> n.getName().equals(currentlyRemoving));
            }
            System.out.println("new size: " + possibleBagHolder.size());
            if(oldSize == possibleBagHolder.size()) stop = true;

        } while(!stop);


        return counter-1;
    }
    public static int numberOfRequiredBags(List<String> rules) {

        List<Bag> bags = createBagListFromStringList(rules);
        return amountOfBags(bags, Objects.requireNonNull(getBagObjectFromName(bags, "shiny gold bags"))) - 1;

    }
    public static int amountOfBags(List<Bag> bags, Bag b) {

        int res = 1;
        System.out.println("Now checking Bag Object with the name '" + b.getName() + "', which has " + b.getContents().size() + " more types of bags inside");

        for(int i = 0; i < b.getContents().size(); i++) {

            Bag bag = getBagObjectFromName(bags, appendSToBag(b.getContents().get(i).getValue()));
            res +=  b.getContents().get(i).getKey() * amountOfBags(bags, Objects.requireNonNull(getBagObjectFromName(bags, bag.getName())));
        }

        return res;

    }
    public static String appendSToBag(String s) {
        if(s.charAt(s.length()-1)!='s') {
            s += "s";
        }
        return s;
    }
    public static List<Bag> createBagListFromStringList(List<String> rules) {
        List<Bag> bags = new ArrayList<>();

        for (String rule: rules) {
            String[] words = rule.split(" ");
            String name = words[0] + " " + words[1] + " " + words[2];
            Bag b = new Bag(name);

            String[] contents = rule.replace('.', ' ').split("contain ")[1].split(", ");
            for(String content: contents) {

                if(!content.equals("no other bags ") /*&& !b.getName().equals("shiny gold bags")*/) {
                    b.addContent(Integer.parseInt(String.valueOf(content.charAt(0))), content.trim().substring(2));
                }

            }
            bags.add(b);

        }


        return bags;
    }
    public static Bag getBagObjectFromName(List<Bag> bags, String s) {
        for (Bag bag : bags) {
            if (bag.getName().equals(s)) return bag;
        }
        return null;
    }

    //---------------------------------------
    //--################ 8 ################--
    //---------------------------------------

    public static void exercise8() {

    }

    //---------------------------------------
    //--################ 9 ################--
    //---------------------------------------

    public static void exercise9() {

    }

    //---------------------------------------
    //--################ 10 ################--
    //---------------------------------------

    public static void exercise10() {

    }

    //---------------------------------------
    //--################ 11 ################--
    //---------------------------------------

    public static void exercise11() {

    }

    //---------------------------------------
    //--################ 12 ################--
    //---------------------------------------

    public static void exercise12() {

    }

    //---------------------------------------
    //--################ 13 ################--
    //---------------------------------------

    public static void exercise13() {

    }

    //---------------------------------------
    //--################ 14 ################--
    //---------------------------------------

    public static void exercise14() {

    }

    //---------------------------------------
    //--################ 15 ################--
    //---------------------------------------

    public static void exercise15() {

    }

    //---------------------------------------
    //--################ 16 ################--
    //---------------------------------------

    public static void exercise16() {

    }

    //---------------------------------------
    //--################ 17 ################--
    //---------------------------------------

    public static void exercise17() {

    }

    //---------------------------------------
    //--################ 18 ################--
    //---------------------------------------

    public static void exercise18() {

    }

    //---------------------------------------
    //--################ 19 ################--
    //---------------------------------------

    public static void exercise19() {

    }

    //---------------------------------------
    //--################ 20 ################--
    //---------------------------------------

    public static void exercise20() {

    }

    //---------------------------------------
    //--################ 21 ################--
    //---------------------------------------

    public static void exercise21() {

    }

    //---------------------------------------
    //--################ 22 ################--
    //---------------------------------------

    public static void exercise22() {

    }

    //---------------------------------------
    //--################ 23 ################--
    //---------------------------------------

    public static void exercise23() {

    }

    //---------------------------------------
    //--################ 24 ################--
    //---------------------------------------

    public static void exercise24() {

    }
}
