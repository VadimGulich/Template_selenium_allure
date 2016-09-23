package autoTests.JavaMail;

/**
 * Created by Privat24 on 22.09.2016.
 */
import javax.mail.*;
import java.util.Properties;

public class TestImap {

    public static void main(String[] args) throws Exception {
        final String user = "igovtestacc@gmail.com"; // имя пользователя
        final String pass = "igovTestAcc123";    // пароль
        final String host = "imap.gmail.com";     // адрес почтового сервера

        // Создание свойств
        Properties props = new Properties();

        //включение debug-режима
//        props.put("mail.debug", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "465");
//        props.put("mail.smtp.user", user);
//        props.put("mail.smtp.password", pass);
//          props.put("mail.smtp.starttls.enable", "true");

        //Указываем протокол - IMAP с SSL
        props.put("mail.store.protocol", "imaps");
        Session session=Session.getDefaultInstance(props, null);

        Store store=session.getStore("imaps");

        store.connect(host,user, pass);
        //получаем папку с входящими сообщениями
        Folder inbox = store.getFolder("INBOX");

        //открываем её только для чтения
        inbox.open(Folder.READ_ONLY);

        //получаем последнее сообщение (самое старое будет под номером 1)
        Message m = inbox.getMessage(inbox.getMessageCount());
        Multipart mp = (Multipart) m.getContent();
        BodyPart bp = mp.getBodyPart(0);

        //Выводим содержимое на экран
        System.out.println(bp.getContent());
    }
}
