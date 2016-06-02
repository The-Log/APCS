import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Scanner;

/**
 * Created by Ankur on 9/26/2015.
 */
public class Tester {

    public static void main(String[] args) {
        BASE64Decoder decoder = new BASE64Decoder();
        BASE64Encoder encodedBytes = (BASE64Encoder) ("SHZzIGVpd3F5IHBmY2tiIHRjbCB4aWFkZyBjanNmIGh2cyB6b25tIHJjdS4gTWNpZiB0em91OiB0em91e1owaDVfMFRfM0JRMHIxQjlfazBrfQ==");
        byte[] decodedBytes = decoder.decodeBuffer(encodedBytes);
    }
}
