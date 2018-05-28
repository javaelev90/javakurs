package java1.test;

import java1.main.DateTimeValidator;
import org.junit.Assert;
import org.junit.Test;

public class DateTimeValidatorTest {

    /**
     * Valid date time string
     */
    @Test
    public void testGetValidDateTimeCase1(){
        Assert.assertEquals("2002-11-11T12:00",
                DateTimeValidator.getValidDateTime("2002-11-11 12:00").toString());
    }

    /**
     * Invalid date time string
     */
    @Test
    public void testGetValidDateTimeCase2(){
        //Not a date string
        Assert.assertNull(DateTimeValidator.getValidDateTime("aegaega2ga 23:00"));
        //Not a time string
        Assert.assertNull(DateTimeValidator.getValidDateTime("2018-11-11 awfawf"));
        // Hour 25 does not exist
        Assert.assertNull(DateTimeValidator.getValidDateTime("2002-11-11 25:00"));
        //Month 13 does not exists
        Assert.assertNull(DateTimeValidator.getValidDateTime("2002-13-11 12:00"));
        //Day 32 does not exist
        Assert.assertNull(DateTimeValidator.getValidDateTime("2002-11-32 12:00"));
        //Minute 61 does not exist
        Assert.assertNull(DateTimeValidator.getValidDateTime("2002-13-11 12:61"));

    }

    /**
     * Valid time string
     */
    @Test
    public void testGetValidTimeCase1(){
        Assert.assertEquals("22:30", DateTimeValidator.getValidTime("22:30").toString());
    }

    /**
     * Invalid time string
     */
    @Test
    public void testGetValidTimeCase2(){
        //Hour 25 does no exist
        Assert.assertNull(DateTimeValidator.getValidTime("25:30"));
        //Minute 61 does no exist
        Assert.assertNull(DateTimeValidator.getValidTime("23:61"));
        //Not a time string
        Assert.assertNull(DateTimeValidator.getValidTime("152125fa"));
    }



}
