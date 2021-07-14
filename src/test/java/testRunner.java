import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
public class testRunner {
    public static void main(String args[]){
        Result result=JUnitCore.runClasses(JunitTest.class);
        for(Failure failure:result.getFailures())
        {
            System.out.println("fail message is - "+failure.toString());
        }

        System.out.println("Result = "+result.wasSuccessful());
    }
}
