import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
 
import javax.swing.JFrame;
import javax.swing.JTextField;
 
public class TestChess {

  public static void main(String args[]) {
    JFrame frame = new JFrame("Key Listener"); //initiates window
    Container contentPane = frame.getContentPane(); //used to contain text field
    KeyListener listener = new KeyListener() {
   
      @Override 
      public void keyPressed(KeyEvent event) {
          printEventInfo("Key Pressed", event);
      }

      @Override
      public void keyReleased(KeyEvent event) {
          //printEventInfo("Key Released", event);
      }

      @Override
      public void keyTyped(KeyEvent event) {
          printEventInfo("Key Typed", event);
      }

      private void printEventInfo(String str, KeyEvent e) {
          System.out.println("   Code: " + KeyEvent.getKeyText(e.getKeyCode())); //This is what is important for Arrow Keys!!!
      }


      private String keyboardLocation(int keybrd) {
        switch (keybrd) {
          case KeyEvent.KEY_LOCATION_RIGHT:
            return "Right";
          case KeyEvent.KEY_LOCATION_LEFT:
            return "Left";
          case KeyEvent.KEY_LOCATION_NUMPAD:
            return "NumPad";
          case KeyEvent.KEY_LOCATION_STANDARD:
            return "Standard";
          case KeyEvent.KEY_LOCATION_UNKNOWN:
          default:
          return "Unknown";
        }
      }
    };
 
    JTextField textField = new JTextField(); //create text field
    textField.addKeyListener(listener); //add newly adopted key listener to text field
    contentPane.add(textField, BorderLayout.NORTH); //add text field to pane
    frame.pack(); //help ensure size of frame
    frame.setVisible(true); //display frame
  }
}