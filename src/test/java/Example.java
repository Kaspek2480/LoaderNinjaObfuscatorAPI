import com.loader.ninja.NativeBridge;
import com.loader.ninja.object.SecureString;
import com.loader.ninja.spy.CallMethodSpy;

public class Example {

    private static final SecureString STRING = NativeBridge.getSecret("xd");

    public static void main(String[] args) {
        System.out.println(CallMethodSpy.INSTANCE.getSpy().fetchCallerMethod());
        if (STRING != null)
            STRING.safeRevealString().ifPresent(System.out::println);
    }
}
