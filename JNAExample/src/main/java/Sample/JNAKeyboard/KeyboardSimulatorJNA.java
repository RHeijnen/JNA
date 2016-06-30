package Sample.JNAKeyboard;

/**
 * Created by Indi on 6/30/2016.
 */
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

public class KeyboardSimulatorJNA {
    WinUser.INPUT input;

    public KeyboardSimulatorJNA(){
        input = new WinUser.INPUT(  ); // new WindowUserInput JNA object
        input.type = new WinDef.DWORD( WinUser.INPUT.INPUT_KEYBOARD ); // sets the input type to windows keyboards
        // Because setting INPUT_INPUT_KEYBOARD is not enough: https://groups.google.com/d/msg/jna-users/NDBGwC1VZbU/cjYCQ1CjBwAJ
        // thanks vellotis@StackOverflow
        input.input.setType("ki"); // sets the input to keyboard input (C++ input.ki)
    }
    public void sendMessage(String message) {
        char[] splitMessage = message.toCharArray();
        for (char i : splitMessage) {
            int unicodeKey = i; // get int value of the char
            sendKey(unicodeKey); // sends that value to the sendKey function
        }

    }

    public void sendKey(int key){
        // Press "a"
        input.input.ki.time = new WinDef.DWORD( 0 );
        input.input.ki.dwFlags = new WinDef.DWORD(WinUser.KEYBDINPUT.KEYEVENTF_UNICODE); //I am handing you a unicode character
        input.input.ki.wScan = new WinDef.WORD(key); //The unicode code in decimal (right?)
        input.input.ki.wVk = new WinDef.WORD(0); //Virtual keyboard, empty
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0); //  gets extra button information ? Language stuff ?
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), new WinUser.INPUT[]{input}, input.size()); //
    }
    public void setWindowFocus(String windowName){


    }
}
/*
    I assume this does the same, but uses the virtual keyboard instead

        public void sendKey(long key){

        input.input.ki.wVk = new WinDef.WORD( key ); // virtual keyboard

         // Press key
        input.input.ki.dwFlags = new WinDef.DWORD( 0 );  // key down
        User32.INSTANCE.SendInput( new WinDef.DWORD( 1 ), ( WinUser.INPUT[] ) input.toArray( 1 ), input.size() );
        // Release key
        input.input.ki.dwFlags = new WinDef.DWORD( 2 );  // key up
        User32.INSTANCE.SendInput( new WinDef.DWORD( 1 ), ( WinUser.INPUT[] ) input.toArray( 1 ), input.size() );
    }

 */