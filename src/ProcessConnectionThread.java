import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.StreamConnection;

public class ProcessConnectionThread implements Runnable  {
    private StreamConnection mConnection;
    private static final int EXIT_CMD = -1;
    public ProcessConnectionThread(StreamConnection connection)
    {
        mConnection = connection;
    }


    @Override
    public void run() {
        try{
            InputStream inputStream = mConnection.openInputStream();
            System.out.println("waiting for input");

            while (true) {
                //writeItem(outputStream, s);
                int command = inputStream.read();

                if (command == EXIT_CMD) {
                    System.out.println("finish process");
                    break;
                }
                processCommand(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void processCommand(int command){
        System.out.println(command);
        try {
            Robot robot = new Robot();
            switch (command){
                case 8:
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    robot.keyPress(KeyEvent.VK_UP);
                    break;
                case 6:
                    robot.keyRelease(KeyEvent.VK_LEFT);
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    break;
                case 4:
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    robot.keyPress(KeyEvent.VK_LEFT);
                    break;
                case 2:
                    robot.keyRelease(KeyEvent.VK_UP);
                    robot.keyPress(KeyEvent.VK_DOWN);
                    break;
                case 1:
                    robot.keyPress(KeyEvent.VK_ESCAPE);
                    robot.keyRelease(KeyEvent.VK_ESCAPE);
                    break;
                case 3:
                    robot.keyPress(KeyEvent.VK_SPACE);
                    robot .keyRelease(KeyEvent.VK_SPACE);
                    break;
                default:
                    robot .keyRelease(KeyEvent.VK_SPACE);
                    robot. keyRelease(KeyEvent.VK_ESCAPE);
                    robot.keyRelease(KeyEvent.VK_LEFT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_UP);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    break;
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }

     }
}
