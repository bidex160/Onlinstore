package com.example.store;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class sendmail extends AsyncTask<Void, Void, Void> {
    boolean passtry = false;
    boolean passpsot = false;
    interface PassData{
        void pass(boolean x);

        void passpost(boolean y);
    }
    private Context context;
   private Session session;
    private String email;
    private String subject;
    private String message;
    private ProgressDialog progressDialog;
    PassData passData;

    public sendmail(Context context, String email, String subject, String message) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.e(TAG, "onPostExecute: mail" );
       // passpsot = true;
    //    Toast.makeText(context, "Order Placed Succcesfully", Toast.LENGTH_LONG).show();
    //   passData = (PassData)context;
     //   passData.passpost(passpsot);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

       //progressDialog = ProgressDialog.show(context, "Placing Order", "Please wait", false,false);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties props = new Properties();


        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(gmailconfig.EMAIL, gmailconfig.PASSWORD);
                    }
                });



          try {

              MimeMessage mimeMessage = new MimeMessage(session);
              mimeMessage.setFrom(new InternetAddress(gmailconfig.EMAIL));
              mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
              mimeMessage.setSubject(subject);
              mimeMessage.setText(message);
              Transport.send(mimeMessage);


              Log.e(TAG, "doInBackground: ee" );

          }catch (Exception e){
              Log.e(TAG, "doInBackground: " + e );
       //    passtry = true;
       //    passData = (PassData)context;
        //   passData.pass(passtry);

          }


        return null;
    }
}
