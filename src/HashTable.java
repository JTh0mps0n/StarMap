import java.sql.SQLRecoverableException;
import java.sql.SQLSyntaxErrorException;
import java.util.LinkedList;
import java.util.Random;

public class HashTable {


    private LinkedList<Entry>[] arr;
    private int CAPACITY;
    private int size;
    public int collision;



    public HashTable(){
        CAPACITY = 101;
        arr = new LinkedList[CAPACITY];
        size = 0;
        collision = 0;
    }

    public HashTable(int initCap){
        CAPACITY = initCap;
        arr = new LinkedList[initCap];
        size = 0;
        collision = 0;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    public Object put(Object key, Object value){
        if(size >= CAPACITY){//                                Checks if arr is full
            throw new IllegalStateException();
        }
        size++;
        int hc = key.hashCode();
        int index = Math.abs(hc % CAPACITY);

        if(arr[index] == null){
            arr[index] = new LinkedList<Entry>();
            arr[index].add(new Entry(key, value));
            return null;
        }
        else{

            for (int i = 0; i < arr[index].size(); i++) {
                collision++;
                if(arr[index].get(i).key.equals(key)){
                    Object temp = arr[index].get(i).value;
                    arr[index].set(i, new Entry(key, value));
                    return temp;
                }
            }
            arr[index].addLast(new Entry(key, value));
            return null;
        }
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------



    public Object get(Object key) {
        int hc = key.hashCode();
        int index = Math.abs(hc % CAPACITY);
        if (arr[index] == null) {
            return null;
        }
        for (int i = 0; i < arr[index].size(); i++) {
            collision++;
            if(arr[index].get(i).key.equals(key)){
                return arr[index].get(i).value;
            }
        }
        return null;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        //System.out.println("running");
        String s = "";

        for (int i = 0; i < arr.length; i++) {
            //System.out.println("running loop " + i);
            s += "INDEX: " + i + "    -    " + arr[i] + "\n";
        }
        return s;
    }


//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public double averageListLength(){
        double totalLength = 0;
        double num = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != null){
                num++;
                totalLength += arr[i].size();
            }
        }
        return totalLength / num;
    }


//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public int longestListLength(){
        int longestLength = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != null){
                if(arr[i].size() > longestLength){
                    longestLength = arr[i].size();
                }
            }
        }
        return longestLength;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//    public Object remove(Object key){
//        int hc = key.hashCode();
//        int index = Math.abs(hc % CAPACITY);
//        if(arr[index] == null){
//            return null;
//        }
//        if(arr[index].key.equals(key)){
//            size--;
//            arr[index].removed = true;
//            return arr[index].value;
//        }
//        Random random = new Random( index * (index - 1));
//
//
//        for (int i = Math.abs(random.nextInt() % CAPACITY); i < arr.length; i = Math.abs(random.nextInt() % CAPACITY)) {
//            Entry currentEntry = arr[i];
//            if(currentEntry == null){
//                return null;
//            }
//            else{
//                if(!currentEntry.removed){
//                    if(currentEntry.key.equals(key)){
//                        size--;
//                        currentEntry.removed = true;
//                        return currentEntry.value;
//                    }
//                }
//
//            }
//            collision++;
//        }
//        return null;
//    }







//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    private class Entry{
        Object key;
        Object value;
        //boolean removed;

        public Entry(){
            key = null;
            value = null;
            //removed = false;
        }
        public Entry(Object key, Object value){
            this.key = key;
            this.value = value;
            //removed = false;
        }

        @Override
        public String toString() {
//            if(removed){
//                return "dummy";
//            }
            return "key: " + key + ", value: " + value;
        }
    }
}
