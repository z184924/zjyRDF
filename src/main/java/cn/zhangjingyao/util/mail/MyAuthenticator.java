package cn.zhangjingyao.util.mail;
/**   
 *  
 */

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author
 */
public class MyAuthenticator extends Authenticator {
    String userName=null;
    String password=null;
        
    public MyAuthenticator(){   
    }   
    public MyAuthenticator(String username, String password) {
        this.userName = username;    
        this.password = password;    
    }    
    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName, password);
    }   
}   
