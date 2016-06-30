package Sample;
import Sample.JNAKeyboard.KeyboardSimulatorJNA;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

public class Main {

    public static void main( String[] args ) {
        KeyboardSimulatorJNA KS = new KeyboardSimulatorJNA();
        KS.sendKey('a');
        KS.sendMessage("hello");


    }
}