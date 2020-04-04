import java.util.*;


public class Main {

    public static void main(String[] args) {

        List<String> myList = new ArrayList<>();
        myList.add("dasdasd");
        myList.add("dsad");
        myList.add("vvcxv.com/");
        myList.add("xx");

        Map<String, String> map = new HashMap<String, String>();
        map.put(null, null);
        map.put("nkn", "dfsd");
        map.put("key2", "");


        URL a11 = new URL.Composer("google.com")
                .setAuthority("lll", "mmm")
                .setParam("иири")
                .compose();

        URL a12 = new URL.Composer("vvv.vvv")
                .isSecure(true)
                .setPort(100)
                .setPath(myList)
                .compose();


        System.out.println(a11);
        System.out.println(a12);
    }
}
