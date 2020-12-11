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
        //exercise7();
        //exercise8();
        //exercise9();
        //exercise10();
        exercise11();

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
        List<String> values = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ypreb\\Desktop\\aoc\\input8.txt"));
            String line = reader.readLine();
            while (line != null) {
                values.add(line);
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("First question: The accumulator reaches the value " + getAccumulatorAfterOneLoop(values));
        System.out.println("Second question: The accumulator in the fixed code reaches the value " + getCorrectAccumulator(values));
    }
    public static class Instruction {
        String name;
        int argument;

        public Instruction(String aName, int anArgument) {
            this.name = aName;
            this.argument = anArgument;
        }

        public String getName() {
            return this.name;
        }

        public int getArgument() {
            return this.argument;
        }
    }
    public static int getCorrectAccumulator(List<String> values) {
        List<Instruction> instructions = convertInputToInstructions(values);
        return fixCodeAndGetAccumulator(instructions);
    }
    private static int fixCodeAndGetAccumulator(List<Instruction> instructions) {
        int acc = 0;
        List<Instruction> original = new ArrayList<>(instructions);
        List<Instruction> modified = new ArrayList<>(instructions);

        for (int i = 0; i < original.size(); i++) {
            Instruction current = original.get(i);
            String oldName = "";
            if (current.getName().equals("acc")) continue;
            if (current.getName().equals("nop")) {
                current.name = "jmp";
                oldName = "nop";
            } else if (current.getName().equals("jmp")) {
                current.name = "nop";
                oldName = "jmp";
            }
            modified.set(i, current);
            if (isCorrectCode(modified)) {
                return getAccumulatorFromCorrectCode(modified);
            } else {
                current.name = oldName;
                modified.set(i, current);
                continue;
            }
        }
        return acc;
    }
    public static int getAccumulatorFromCorrectCode(List<Instruction> instructions){
        boolean end = false;
        int acc = 0;
        int index = 0;
        while (!end) {
            Instruction current = instructions.get(index);
            if (index == instructions.size() - 1) {
                if (current.getName().equals("jmp") && current.getArgument() != 1) {
                    end = false;
                } else {
                    end = true;
                    return acc;
                }
            }
            if (current.getName().equals("acc")) {
                acc += current.getArgument();
                ++index;
            } else if (current.getName().equals("jmp")) {
                index += current.getArgument();
            } else if (current.getName().equals("nop")) {
                ++index;
            }
        }
        return acc;
    }
    public static boolean isCorrectCode(List<Instruction> instructions) {
        boolean[] indexlist = new boolean[instructions.size()];
        for (int i = 0; i < indexlist.length; i++) {
            indexlist[i] = false;
        }
        boolean looped = false;
        int acc = 0;
        int index = 0;
        while (!looped) {
            Instruction current = instructions.get(index);
            if (indexlist[index]) {
                System.out.println("WRONG: reached a loop");
                return false;
            }
            if (index == instructions.size() - 1) {
                if (current.getName().equals("jmp") && current.getArgument() != 1) {
                    System.out.println("WRONG: Last argument does not go to the immediate next one");
                    return false;
                } else {
                    System.out.println("CORRECT!!! Accumulator is " + acc);
                    return true;
                }
            }
            indexlist[index] = true;
            if (current.getName().equals("acc")) {
                acc += current.getArgument();
                ++index;
            } else if (current.getName().equals("jmp")) {
                index += current.getArgument();
            } else if (current.getName().equals("nop")) {
                ++index;
            }
        }
        return false;
    }
    public static int getAccumulatorAfterOneLoop(List<String> values) {
        List<Instruction> instructions = convertInputToInstructions(values);
        boolean[] indexlist = new boolean[instructions.size()];
        for (int i = 0; i < indexlist.length; i++) {
            indexlist[i] = false;
        }
        boolean looped = false;
        int acc = 0;
        int index = 0;
        while (!looped) {
            if (indexlist[index]) {
                looped = true;
                continue;
            }
            indexlist[index] = true;
            Instruction current = instructions.get(index);
            if (current.getName().equals("acc")) {
                acc += current.getArgument();
                ++index;
            } else if (current.getName().equals("jmp")) {
                index += current.getArgument();
            } else if (current.getName().equals("nop")) {
                ++index;
            }
        }
        return acc;
    }
    public static List<Instruction> convertInputToInstructions(List<String> values) {
        List<Instruction> instructions = new ArrayList<>();
        for (String current : values) {
            String name = current.split(" ")[0];
            String sign = current.split(" ")[1].substring(0, 1);
            String argumentStr = current.split(" ")[1].substring(1);
            int argument = Integer.parseInt(argumentStr);
            if (sign.equals("-")) {
                argument *= -1;
            }
            Instruction in = new Instruction(name, argument);
            instructions.add(in);
        }
        return instructions;
    }

    //---------------------------------------
    //--################ 9 ################--
    //---------------------------------------

    public static void exercise9() {

        List<String> values = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ypreb\\Desktop\\aoc\\input9.txt"));
            String line = reader.readLine();
            while (line != null) {
                values.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // First question
        System.out.println("First question: the first number to violate rule 1 is " + firstNumberToViolateRule(values));

        // Second question
        System.out.println("Second question: the encryption weakness is the number " + getEncryptionWeakness(values, firstNumberToViolateRule(values)));
    }
    public static long firstNumberToViolateRule(List<String> values) {

        List<Long> nums = new ArrayList<>();
        for(String s : values) nums.add(Long.valueOf(s));

        for(int i = 25; i < nums.size(); i++) {

            List<Long> selection = nums.subList(i-25,i);
            long sumToCheck = nums.get(i);
            if(!isPossibleSum(selection, sumToCheck)) return sumToCheck;
        }
        return 0;
    }
    public static boolean isPossibleSum(List<Long> selection, long sumToCheck) {

        for(int i = 0; i < selection.size(); i++) {
            long required = sumToCheck - selection.get(i);
            if(selection.contains(required) && required != sumToCheck) return true;
        }
        return false;

    }
    public static long getEncryptionWeakness(List<String> values,  long sum) {
        List<Long> nums = new ArrayList<>();
        for(String s : values) nums.add(Long.valueOf(s));

        for(int i = 0; i < nums.size(); i++) {
            long first = nums.get(i);
            long current = first;
            long smallest = first;
            long largest = 0;
            int j = i+1;

            while(current < sum) {
                current += nums.get(j);
                if(nums.get(j) < smallest) smallest = nums.get(j);
                if(nums.get(j) > largest) largest = nums.get(j);
                j++;
            }
            if(sum == current) return smallest + largest;

        }
        return 0;
    }

    //---------------------------------------
    //--################ 10 ################--
    //---------------------------------------

    public static void exercise10() {
        List<Long> values = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ypreb\\Desktop\\aoc\\input10.txt"));
            String line = reader.readLine();
            while (line != null) {
                values.add(Long.parseLong(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(values);
        int diff1 = 0;
        int diff3 = 0;
        if (values.contains(1L)) diff1++;
        if (values.contains(3L)) diff3++;
        for (int i = 0; i < values.size(); i++) {
            long current = values.get(i);
            if (values.contains(current + 1)) diff1++;
            else if (values.contains(current + 3)) diff3++;
        }

        System.out.println("First question: The product of 1diff and 3diff is " + diff1 * diff3);
        System.out.println("Second question: The number of all possible arrangements is " + numberOfArrangements(values));
        //System.out.println("Second question: The number of all possible arrangements is " + numberOfArrangements(values, 0));
    }
    public static long numberOfArrangements(List<Long> values) {
        /*
         * Idea: HashMap, where Key = numberFromInput and Value = possible arrangements up to the end
         *
         * So you start by sorting the joltage array in descending order and adding the pair (highestNumber, 1) to the HashMap
         *  since there is only one way to get to the required highest joltage (highest number + 3)
         *
         * Then, in descending order, you iterate through the input (now ignoring the highest number) and each iteration, you add
         *  (currentNumber, possibleArrangements) to the HashMap.
         *
         * The number of possible arrangements for one number is equal to the sum of the numbers of arrangements from its three following whole numbers
         *  (assuming they exist). For example if we are at number 144, his possible arrangements are equal to the possibilities of the numbers 145,146,147
         *  while always checking if the number is actually already a key in the Map. Otherwise, you skip.
         *
         * Now since the starting point 0 is not contained inside the input, we add one more pair (0, arrangements) by hand.
         *
         * When the pair (0, arrangements) exists, we found our solution, which is "arrangements". So this function returns the value for key 0.
         */

        HashMap<Long, Long> arrangements = new HashMap<>();
        Collections.reverse(values);
        arrangements.put(values.get(0), 1L);
        for (int i = 1; i < values.size(); i++) {
            long current = values.get(i);
            long res = 0;
            if (arrangements.containsKey(current + 1)) res += arrangements.get(current + 1);
            if (arrangements.containsKey(current + 2)) res += arrangements.get(current + 2);
            if (arrangements.containsKey(current + 3)) res += arrangements.get(current + 3);
            arrangements.put(current, res);
        }
        long res = 0;
        if (arrangements.containsKey(1L)) res += arrangements.get(1L);
        if (arrangements.containsKey(2L)) res += arrangements.get(2L);
        if (arrangements.containsKey(3L)) res += arrangements.get(3L);
        arrangements.put(0L, res);
        return arrangements.get(0L);
    }
    // For the love of god, don't use this recursive method. It works though!
    public static long numberOfArrangements(List<Long> values, long current) {
        System.out.println("Currently checking the number " + current);
        if (current == values.get(values.size() - 1)) {
            return 1;
        }
        long res = 0;
        if (values.contains(current + 1)) res += numberOfArrangements(values, current + 1);
        if (values.contains(current + 2)) res += numberOfArrangements(values, current + 2);
        if (values.contains(current + 3)) res += numberOfArrangements(values, current + 3);
        return res;
    }

    //---------------------------------------
    //--################ 11 ################--
    //---------------------------------------

    public static void exercise11() {

        List<String> values = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ypreb\\Desktop\\aoc\\input11.txt"));
            String line = reader.readLine();
            while (line != null) {
                values.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // First question

        int ROWS = values.size();
        int COLS = values.get(0).length();

        char[][] seatsOrig = new char[ROWS][COLS];

        for(int i = 0; i < ROWS; i++) {
            String current = values.get(i);
            for(int j = 0; j < COLS; j++) {
                seatsOrig[i][j] = current.charAt(j);
            }
        }

        boolean stop = false;
        char[][] seatsOld = new char[ROWS][COLS];
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                seatsOld[i][j] = seatsOrig[i][j];
            }
        }

        while(!stop) {

            stop = true;
            for(int i = 0; i < ROWS; i++) {
                for(int j = 0; j < COLS; j++) {
                    char current = seatsOld[i][j];

                    // if-else for the first part
                    /*if(current == 'L') {
                        if(numberOfAdjacentOccupiedSeats(seatsOld, i, j) == 0) {
                            stop = false;
                            seatsOrig[i][j] = '#';
                        }
                    } else if(current == '#') {
                        if(numberOfAdjacentOccupiedSeats(seatsOld, i, j) >= 4) {
                            stop = false;
                            seatsOrig[i][j] = 'L';
                        }
                    }*/


                    // if-else for the second part
                    if(current == 'L') {
                        if(numberOfVisibleOccupiedSeats(seatsOld, i, j) == 0) {
                            stop = false;
                            seatsOrig[i][j] = '#';
                        }
                    } else if(current == '#') {
                        if(numberOfVisibleOccupiedSeats(seatsOld, i, j) >= 5) {
                            stop = false;
                            seatsOrig[i][j] = 'L';
                        }
                    }

                }
            }
            for(int i = 0; i < ROWS; i++) {
                System.arraycopy(seatsOrig[i], 0, seatsOld[i], 0, COLS);
            }


        }

        System.out.println("Number of occupied seats after nothing changes any more is " + numberOfOccupiedSeatsInArray(seatsOrig));

    }
    public static int numberOfOccupiedSeatsInArray(char[][] seats) {
        int res = 0;
        for(int i = 0; i < seats.length; i++) {
            for(int j = 0; j < seats[0].length; j++) {
                if(seats[i][j] == '#') res++;
            }
        }
        return res;
    }
    public static int numberOfAdjacentOccupiedSeats(char[][] seats, int x, int y) {
        int res = 0;

        if(x-1 >= 0 && y-1 >= 0) if(seats[x-1][y-1] == '#') res++;
        if(x-1 >= 0) if(seats[x-1][y] == '#') res++;
        if(x-1 >= 0 && y+1 < seats[0].length) if(seats[x-1][y+1] == '#') res++;

        if(y-1 >= 0) if(seats[x][y-1] == '#') res++;
        if(y+1 < seats[0].length) if(seats[x][y+1] == '#') res++;

        if(x+1 < seats[0].length-1 && y-1 >= 0) if(seats[x+1][y-1] == '#') res++;
        if(x+1 < seats[0].length-1) if(seats[x+1][y] == '#') res++;
        if(x+1 < seats[0].length-1 && y+1 < seats[0].length) if(seats[x+1][y+1] == '#') res++;

        return res;
    }
    public static int numberOfVisibleOccupiedSeats(char[][] seats, int x, int y) {

        int res = 0;
        String row = String.valueOf(seats[x]);

        // left
        int dy = y-1;
        while(dy >= 0) {
            if(seats[x][dy] == '#') {
                res++;
                break;
            }
            if(seats[x][dy] == 'L') {
                break;
            }
            dy--;
        }

        // right
        dy = y+1;
        while(dy <= seats[0].length-1) {
            if(seats[x][dy] == '#') {
                res++;
                break;
            }
            if(seats[x][dy] == 'L') {
                break;
            }
            dy++;
        }

        // top
        int dx = x-1;
        while(dx >= 0) {
            if(seats[dx][y] == '#') {
                res++;
                break;
            }
            if(seats[dx][y] == 'L') {
                break;
            }
            dx--;
        }

        //bottom
        dx = x+1;
        while(dx <= (seats.length-1)) {
            if(seats[dx][y] == '#') {
                res++;
                break;
            }
            if(seats[dx][y] == 'L') {
                break;
            }
            dx++;
        }

        //diagonal top left
        dx = x-1;
        dy = y-1;
        while (true) {
            if(dx < 0) break;
            if(dy < 0) break;
            if(seats[dx][dy] == '#') {
                res++;
                break;
            }
            if(seats[dx][dy] == 'L') {
                break;
            }
            dx--;
            dy--;
        }

        //diagonal top right
        dx = x-1;
        dy = y+1;
        while (true) {
            if(dx < 0) break;
            if(dy > (seats[0].length-1)) break;
            if(seats[dx][dy] == '#') {
                res++;
                break;
            }
            if(seats[dx][dy] == 'L') {
                break;
            }
            dx--;
            dy++;
        }

        //diagonal bot left
        dx = x+1;
        dy = y-1;
        while (true) {
            if(dx > (seats.length-1)) break;
            if(dy < 0) break;
            if(seats[dx][dy] == '#') {
                res++;
                break;
            }
            if(seats[dx][dy] == 'L') {
                break;
            }
            dx++;
            dy--;
        }

        //diagonal bot right
        dx = x+1;
        dy = y+1;
        while (true) {
            if(dx > (seats.length-1)) break;
            if(dy > (seats[0].length-1)) break;
            if(seats[dx][dy] == '#') {
                res++;
                break;
            }
            if(seats[dx][dy] == 'L') {
                break;
            }
            dx++;
            dy++;
        }

        return res;
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
