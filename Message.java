
package za.ac.tut.Message;

/**
 *
 * @author Thapelo Mkhwanazi
 */
public class Message 
{
    String plainMessage;
    
    public Message(String plainMessage)
    {
        this.plainMessage = plainMessage;
    }

    public String getPlainMessage() {
        return plainMessage;
    }

    public void setPlainMessage(String plainMessage) {
        this.plainMessage = plainMessage;
    }

    @Override
    public String toString() {
        return "Message{" + "plainMessage=" + plainMessage + '}';
    }
    
}
