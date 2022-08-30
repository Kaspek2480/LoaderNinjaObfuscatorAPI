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
        int original = 5000;
        System.out.println("Original: " + original);

        int obf = NativeBridge.encryptInt(original, "static_secure_element", 8);
        System.out.println("Obfuscated: " + obf);

        int deobf = NativeBridge.decryptInt(obf, 8);
        System.out.println("Deobfuscated: " + deobf);
        System.out.println("Original == Deobfuscated: " + (original == deobf));
    }
}
