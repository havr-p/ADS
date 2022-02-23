import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Storage {
    private Map<String, Integer> storageMap = new HashMap<>();
    public int maxTypes, inputCounter, deltaTypes,
            maxTypesFirstOccurrence, maxAmount;
    String maxAmountItem;
    //currentMaxTypesCounter - counts how many times current maximum of types appeared
    //if currentMaxAppearingCount > 1 ( eg. 1, 2, 3(1), 2, 3(2)) we should remember the position of 1st appearing
    boolean sizeDecreased = false;
    public Storage() {

    }



    void addItem(String item, int amount) {
        inputCounter++;
        int currentItemAmount;
        if (storageMap.containsKey(item)) {
            //System.out.println("before " + storage);
             currentItemAmount = storageMap.get(item) + amount;
            if (currentItemAmount == 0) {
                storageMap.remove(item);
            } else {
                storageMap.replace(item, currentItemAmount);
            }
            //System.out.println("after " + storage);
        } else {
            currentItemAmount = amount;
            storageMap.put(item, amount);
        }


        deltaTypes = storageMap.size() - maxTypes;
        //set new maximum
        if (deltaTypes == 1) {
                maxTypes = storageMap.size();
            maxTypesFirstOccurrence = inputCounter;
        }

            if (currentItemAmount > maxAmount) {
                maxAmount = currentItemAmount;
                maxAmountItem = item;
            }
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        Scanner in = new Scanner(System.in);
        while (true) {
            String item = in.next();
            int amount = in.nextInt();
            if (item.equals("KONIEC")) break;
            storage.addItem(item, amount);
        }
        System.out.println(storage.maxTypes + " " + storage.maxTypesFirstOccurrence);
        System.out.println(storage.maxAmount + " " + storage.maxAmountItem);
        in.close();
    }
}
