package test.java;

import exception.WaterPoolOutOfIndexException;
import impl.WaterPool;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

public class WaterPoolTest {

    private WaterPool waterPool;

    @Before
    public void beforeTest(){
        waterPool = Mockito.spy(new WaterPool());
    }

    @Test
    public void watterPoolHappyTest(){
        int [] landscape = {5,2,3,4,5,4,0,3,1};
        long count = waterPool.calculateWaterAmount(landscape);

        Assert.assertEquals(9, count);

    }

    @Test(expected = WaterPoolOutOfIndexException.class)
    public void watterPoolUnHappyTest(){
        int [] landscape = new int[32001];
        Random rand = new Random(); //instance of random class

        for (int i = 0; i < 32001; i++) {
            landscape[i] = rand.nextInt(10);
        }
        waterPool.calculateWaterAmount(landscape);

    }

}
