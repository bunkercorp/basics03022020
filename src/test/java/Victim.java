import infra.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class Victim{

    @Test
    public static void test1() {
        System.out.println("Test 1");
    }

//    @Test
//    public static void test2() throws MalformedURLException, InterruptedException {
//        System.out.println("Test 2");
//        BrowserFactory
//                .getDriver();
////                .get("http://google.com.ua");
////        Thread.sleep(2500);
//        Assert.assertTrue(false);
//    }
}