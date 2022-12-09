package com.github.zipcodewilmington;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author xtofer
 * @version 1.0.0
 * @date 10/21/19 9:05 AM
 */
public class DashaMapTest {
    @Test
    public void testSet(){
        //given
        DashaMap test = new DashaMap();
        //when
        String expected = "8";
        test.set("Simon",expected);
        String actual = test.get("Simon");
        //then
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void testDelete(){
        //given
        DashaMap test = new DashaMap();
        test.set("Simon","?");
        test.set("Kris","Simple");
        //when
        test.delete("Simon");
        //then
        Assert.assertNull(test.get("Simon"));
        Assert.assertEquals(test.size(),1);
    }
    @Test
    public void testDelete_neverAdded(){
        //given
        DashaMap test = new DashaMap();
        //then
        Assert.assertNull(test.delete("Simon"));
    }
    @Test
    public void testGet(){
        //given
        String expected = "8";
        DashaMap test = new DashaMap();
        test.set("Simon",expected);
        test.set("Kyle", "?");
        //when
        String actual = test.get("Simon");
        //then
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void testGet_notInside(){
        //given
        DashaMap test = new DashaMap();
        //when
        String expected = "8";
        test.set("Kris",expected);
        String actual = test.get("Kyle");
        //then
        Assert.assertNull(actual);
    }
    @Test
    public void testIsEmpty_true(){
        //given
        DashaMap test = new DashaMap();
        //then
        Assert.assertTrue(test.isEmpty());
    }
    @Test
    public void testIsEmpty_false(){
        //given
        DashaMap test = new DashaMap();
        test.set("Mr.","Cool");
        //then
        Assert.assertFalse(test.isEmpty());
    }
    @Test
    public void testSize_0(){
        //given
        DashaMap test = new DashaMap();

    }
    @Test
    public void testSize_nonZero(){
        //given
        DashaMap test = new DashaMap();
        test.set("Colby","son");
        test.set("Wally","G");
        //then
        Assert.assertEquals(2,test.size());

    }


}
