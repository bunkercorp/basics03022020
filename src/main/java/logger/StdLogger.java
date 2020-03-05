package logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class StdLogger extends Logger {

    private static StdLogger instance;
    private int counter =1;
    private StdLogger(){
        super();
    }
    public static StdLogger getInstance(){
        if(instance==null)
            instance = new StdLogger();
        return instance;
    }

    @Override
    public void log(String msg){
        String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
        String now =new SimpleDateFormat("HH:MM:ss:SSS").format(new Date());
        System.out.printf("%d) %s [%s]: %s\n",counter++,now.toString() ,caller, msg);

    }
}
