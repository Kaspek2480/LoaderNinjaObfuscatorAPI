import com.loader.ninja.NativeBridge;
import com.loader.ninja.object.SecureString;
import com.loader.ninja.spy.CallMethodSpy;

public class Example {

//    private static final SecureString STRING = NativeBridge.getSecret("xd");

    public static void main2(String[] args) {
        System.out.println(CallMethodSpy.INSTANCE.getSpy().fetchCallerMethod());
//        if (STRING != null)
//            STRING.safeRevealString().ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println(NativeBridge.encryptInt(5000, "static_secure_element", 8));
        System.out.println(NativeBridge.decryptInt(7842, 8));
//        System.out.println(decryptInt(1, 5));
    }
}
