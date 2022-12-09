package com.github.zipcodewilmington;

import java.util.Locale;
import java.util.function.Function;

/**
 * @author xtofer
 * @version 1.0.0
 * @date 10/21/19 9:05 AM
 */
public class DashaMap implements HashMapX {
    private int size;
    private Function<String,Integer> hashingFunction;
    private SinglyLinkedList<KVPair>[] hashArray = new SinglyLinkedList[26];

    public DashaMap() {
        this.hashingFunction = (input) -> {
            if (input.length() > 0) {
                return Character.toLowerCase(input.charAt(0)) - 97;
            }
            return null;
        };
        this.size=0;
    }


    @Override
    public void set(String key, String value) {
        int index = (hashingFunction.apply(key)); //find the bucket
        if(hashArray[index] == null){ //empty bucket case
            hashArray[index] = new SinglyLinkedList<>();
            hashArray[index].add(new KVPair(key, value));
        }
        else {
            for(KVPair entry: hashArray[index]){ // if key already exists in bucket set a new value for that key
                if(entry.getKey().equals(key)){
                    entry.setValue(value);
                    return;
                }
            }
            hashArray[index].add(new KVPair(key,value));
        }
        this.size++;
    }

    @Override
    public String delete(String key) {
        int index = (hashingFunction.apply(key)); //find bucket
        if(hashArray[index] == null) return null;
        int counter = 0;
        String removed = null;
        for (KVPair entry: hashArray[index]
             ) {
            if(entry.getKey().equals(key)){
                removed = entry.getKey();
                break;
            }
            else counter++;
        }
        if(removed == null) return null;
        hashArray[index].remove(counter);
        this.size--;
        return (String) removed;
    }

    @Override
    public String get(String key) {
        int index = (hashingFunction.apply(key)); //find bucketint
        if(hashArray[index] == null ) return null;
        for (KVPair entry: hashArray[index]
             ) {
            if(entry.getKey().equals(key)) return (String)(entry.getValue()) ;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public boolean bucketSize(String key) {
        return false;
    }
}
