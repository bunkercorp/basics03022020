import infra.BrowserSupplier;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Victim {

    @Test
    public  void test(){

        BrowserSupplier.getDriver().get("https://google.com.ua");
    }

}
