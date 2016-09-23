package autoTests.JavaMail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Created by Privat24 on 22.09.2016.
 */



    // Отправка простого сообщения с типом контента "text/plain"
    public class TestEmailSend {

        public static void main(String[] args) {

            // Сюда необходимо подставить адрес получателя сообщения
            String to = "nazarenkod1990@gmail.com";
            String from = "Test Tester <igovtestacc@gmail.com>";
            final String user = "igovtestacc@gmail.com"; // имя пользователя
            final String pass = "igovTestAcc123";    // пароль

            // Сюда необходимо подставить SMTP сервер, используемый для отправки
            String host = "imap.gmail.com";
            // Тут указываем порт SMTP сервера.
            int port = 465;

            // Создание свойств, получение сессии
            Properties props = new Properties();

            // При использовании статического метода Transport.send()
            // необходимо указать через какой хост будет передано сообщение
            props.put("mail.smtp.host", host);
            // Если почтовый сервер использует SSL
            props.put("mail.smtp.ssl.enable", "true");
            // Указываем порт SMTP сервера.
            props.put("mail.smtp.port", port);
            // Большинство SMTP серверов, используют авторизацию.
            props.put("mail.smtp.auth", "true");
            // Включение debug-режима
            props.put("mail.debug", "true");
            // Авторизируемся.
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                // Указываем логин пароль, от почты, с которой будем отправлять сообщение.
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });

            try {
                // Создание объекта сообщения
                Message msg = new MimeMessage(session);

                // Установка атрибутов сообщения
                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject("Test E-Mail through Java");
                msg.setSentDate(new Date());

                // Установка тела сообщения
                msg.setText("This is a test of sending a " +
                        "plain text e-mail through Java.\n" +
                        "Here is line 2.");

                // Отправка сообщения
                Transport.send(msg);
                System.out.println("Email send");
            }
            catch (MessagingException mex) {
                // Печать информации об исключении в случае его возникновения
                mex.printStackTrace();
            }
        }
    }
