
package za.ac.tut.MessageEncrypto;

import za.ac.tut.Message.Message;

/**
 *
 * @author Thapelo Mkhwanazi
 */
public class MessageEncryptor 
{

    public MessageEncryptor() 
    {
        
    }
    
    public Message Encrytor(Message plainMessage)
    {
        String plainMsg; 
        char textMessage, replace;
        String encryptedMsg = "";
        
        plainMsg = plainMessage.getPlainMessage();  //mo ke kereya that plainMessage I have to encrypt
        
        for (int i = 0; i < plainMsg.length(); i++) 
        {
            textMessage = plainMsg.charAt(i); 
            
            if(Character.isLetter(textMessage))
            {
                textMessage = Character.toLowerCase(textMessage);
                
                switch(textMessage)
                {
                    case 'a':
                        replace = '1';
                        break;
                    case 'e':
                        replace = '2';
                        break;
                    case 'i':
                        replace = '3';
                        break;
                    case 'o':
                        replace = '4';
                        break;
                    case 'u':
                        replace = '5';
                        break;
                    default:
                        replace = textMessage;
                }
                
                encryptedMsg += Character.toString(replace);
            }

        }
         Message message = new Message(encryptedMsg);
        
        return message;
    }
    
}
