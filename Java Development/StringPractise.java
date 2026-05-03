import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StringPractise {
   public  record User(String name) {

   }
    public void main() {
       User user = new User("name");

        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
    }
}


