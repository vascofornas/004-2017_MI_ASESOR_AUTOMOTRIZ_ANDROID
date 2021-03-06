package com.webjuarez.webjuarez;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.widget.Toast.*;

/**
 * Created by modestovascofornas on 11/15/15.
 */
public class CostoServicio_NoLogin_1 extends Activity {
    private String name;
    private String codigo_agencia;
    private String nombre_agencia;
    private String id_agencia;
    private String tel_asesor;
    private String email_asesor;
    private String nombre_asesor;
    private String apellidos_asesor;
    private String google_play_agencia;

    private String mi_nombre;

    private String mi_email;




    private TextView nombreAsesor;

    private String mi_celular;


    Button continuarButton, cancelarButton;
    String nombre, email, cel, tel;
    String recipient, subject, body;
    ImageButton homeButton, llamarButton, emailButton, citaButton,userButton;

    EditText nombreTV, emailTV, celTV, editnombre,editemail,editcelular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costo_no_login_1);





        addListenerHomeButton();
        addListenerLlamarButton();
        addListenerEmailButton();
        addListenerSMSButton();
        addListenerCompartirButton();

        addListenerContinuarButton();
        addListenerCancelerButton();
        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
         apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        tel_asesor = prefs.getString("tel_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        email_asesor = prefs.getString("email_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        google_play_agencia = prefs.getString("google_play_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        nombreAsesor = (TextView) findViewById(R.id.nombreAsesor);
        nombreAsesor.setText("Asesor: "+nombre_asesor+' '+apellidos_asesor);

        String mi_nombre = prefs.getString("mi_nombre", "");
        editnombre = (EditText) findViewById(R.id.editnombre);

        editnombre.setText(mi_nombre);

        String mi_email = prefs.getString("mi_email", "");
        editemail = (EditText) findViewById(R.id.editemail);

        editemail.setText(mi_email);

        String mi_celular = prefs.getString("mi_celular", "");
        editcelular = (EditText) findViewById(R.id.editcelular);

        editcelular.setText(mi_celular);


    }

    public void addListenerCompartirButton() {

        userButton = (ImageButton) findViewById(R.id.userButton);

        userButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {





                subject = "Android App Mi Asesor Automotriz";
                body = "Te recomiendo que descargues la Android App Mi Asesor Automotriz. Disponible en :" + google_play_agencia;
                recipient = google_play_agencia;
                Intent enviar = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                // prompts email clients only
                enviar.setType("message/rfc822");

                //enviar.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                enviar.putExtra(Intent.EXTRA_SUBJECT, "Android App Mi Asesor Automotriz");
                enviar.putExtra(Intent.EXTRA_TEXT, "Te recomiendo que descargues la Android App Mi Asesor Automotriz. Disponible en: " + google_play_agencia);

                try {
                    // the user can choose the email client
                    startActivity(Intent.createChooser(enviar, "Seleccione una aplicación para enviar el email..."));

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(CostoServicio_NoLogin_1.this, "No dispone de aplicaciones email.",
                            Toast.LENGTH_LONG).show();
                }


            }







        });

    }

    public void addListenerHomeButton() {

        homeButton = (ImageButton) findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CostoServicio_NoLogin_1.this, WelcomeNoLogin.class);

                CostoServicio_NoLogin_1.this.startActivity(myIntent);

            }

        });

    }

    public void addListenerLlamarButton() {

        llamarButton = (ImageButton) findViewById(R.id.llamarButton);

        llamarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                new AlertDialog.Builder(CostoServicio_NoLogin_1.this)
                        .setTitle("Marcar a tu Asesor")
                        .setMessage("Estas seguro de que quieres marcar a "+nombre_asesor+" "+apellidos_asesor+"?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                Log.d("score", "Celular: " + tel_asesor);

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel_asesor));


                                startActivity(intent);



                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();






            }

        });

    }
    public void addListenerEmailButton() {

        emailButton = (ImageButton) findViewById(R.id.emailButton);

        emailButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                Log.d("EMAIL FINAL", "EMAIL: " + email_asesor);


                Intent enviar = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                // prompts email clients only
                enviar.setType("message/rfc822");

                enviar.putExtra(Intent.EXTRA_EMAIL, new String[]{email_asesor});
                enviar.putExtra(Intent.EXTRA_SUBJECT, "Enviado desde la Android App Mi Asesor Automotriz");
                enviar.putExtra(Intent.EXTRA_TEXT, " ");

                try {
                    // the user can choose the email client
                    startActivity(Intent.createChooser(enviar, "Seleccione una aplicación para enviar el email..."));

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(CostoServicio_NoLogin_1.this, "No dispone de aplicaciones email.",
                            Toast.LENGTH_LONG).show();
                }


            }



        });

    }
    public void addListenerSMSButton() {

        citaButton = (ImageButton) findViewById(R.id.citaButton);

        citaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                Log.d("CELULAR", "CELULAR: " + tel_asesor);

// Initialize SmsManager Object// add the phone number in the data

                Uri uri = Uri.parse("smsto:" + tel_asesor);


                Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);

                // add the message at the sms_body extra field

                smsSIntent.putExtra("sms_body", " ");

                try {

                    startActivity(smsSIntent);

                } catch (Exception ex) {

                    Toast.makeText(CostoServicio_NoLogin_1.this, "ERROR - SMS no enviado...",

                            Toast.LENGTH_LONG).show();

                    ex.printStackTrace();

                }

            }







        });

    }

    public void addListenerContinuarButton() {

        continuarButton = (Button) findViewById(R.id.seguir);





        continuarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                EditText e = (EditText) findViewById(R.id.editnombre);
                EditText n = (EditText) findViewById(R.id.editemail);
                String email = n.getText().toString();

                if (n.getText().length() == 0) {

                    Context context = getApplicationContext();
                    CharSequence text = "Su nombre es obligatorio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = makeText(context, text, duration);
                    toast.show();
                    //Show Toast
                } else if (!isValid(email)) {

                    Context context = getApplicationContext();
                    CharSequence text = "Su email no es válido";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = makeText(context, text, duration);
                    toast.show();
                } else

                {


                nombreTV = (EditText) findViewById(R.id.editnombre);
                emailTV = (EditText) findViewById(R.id.editemail);
                celTV = (EditText) findViewById(R.id.editcelular);



                nombre = nombreTV.getText().toString();
                email = emailTV.getText().toString();
                cel = celTV.getText().toString();



                Log.d("CITA A SERVICIO", "NOMBRE: " + nombre);
                Log.d("CITA A SERVICIO", "EMAIL: " + email);
                Log.d("CITA A SERVICIO", "CEL: " + cel);

                Intent myIntent = new Intent(CostoServicio_NoLogin_1.this, CostoServicio_NoLogin_2.class);


                myIntent.putExtra("nombre", nombre);
                myIntent.putExtra("email", email);
                myIntent.putExtra("celular", cel);



                CostoServicio_NoLogin_1.this.startActivity(myIntent);

            }
            }

        });

    }

    public static boolean isValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    public void addListenerCancelerButton() {

        cancelarButton = (Button) findViewById(R.id.cancelar);

        cancelarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CostoServicio_NoLogin_1.this, WelcomeNoLogin.class);


                CostoServicio_NoLogin_1.this.startActivity(myIntent);

            }

        });
    }
}
