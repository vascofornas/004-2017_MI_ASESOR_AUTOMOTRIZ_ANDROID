package com.webjuarez.webjuarez;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.makeText;


/**
 * Created by modestovascofornas on 11/15/15.
 */
public class CitaServicio_NoLogin_4 extends Activity {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Button continuarButton, cancelarButton;
    String recipient, subject, body,random;
    String nombre, email, cel, tel, fecha, hora, vehiculo, tipo, ano, comentarios, email_envio_cita, email_emisor, kilometros;
    TextView tvNombre, tvEmail, tvCel, tvTel, tvFecha, tvHora, tvVehiculo, tvTipo, tvAno, tvKilometros;
    EditText tvComentarios;
    ImageButton homeButton, llamarButton, emailButton, citaButton,userButton;

    ProgressDialog progress;
    private String name;
    private String codigo_agencia;
    private String nombre_agencia;
    private String id_agencia;
    private String tel_asesor;
    private String email_asesor;
    private String nombre_asesor;
    private String apellidos_asesor;
    private String google_play_agencia;
    private String email_taller;
    private TextView nombreAsesor;

    private static final String REGISTER_URL = "http://miasesorautomotriz.com/administrar/phpfiles/crear_cita.php";

    public static final String KEY_NOMBRE_CLIENTE = "nombre_cliente";
    public static final String KEY_EMAIL_CLIENTE = "email_cliente";
    public static final String KEY_CEL_CLIENTE = "cel_cliente";

    public static final String KEY_MODELO = "modelo";
    public static final String KEY_ANO = "ano";
    public static final String KEY_TIPO = "tipo";

    public static final String KEY_KILOMETROS = "kilometros";
    public static final String KEY_FECHA = "fecha";
    public static final String KEY_HORA = "hora";
    public static final String KEY_COMENTARIOS = "comentarios";
    public static final String KEY_CODIGO = "codigo";
    public static final String KEY_AGENCIA = "agencia_cita";
    public static final String KEY_TALLER = "email_taller";
    public static final String KEY_ASESOR = "email_asesor";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita_no_login_4);



        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        tel_asesor = prefs.getString("tel_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        email_asesor = prefs.getString("email_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        codigo_agencia = prefs.getString("codigo_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        google_play_agencia = prefs.getString("google_play_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        google_play_agencia = prefs.getString("google_play_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        email_taller = prefs.getString("email_taller", "NO HA SELECCIONADO NINGUNA AGENCIA");
        Log.d("CELULAR", "EMAIL_TALLER: " + email_taller);


        addListenerHomeButton();
        addListenerLlamarButton();
        addListenerEmailButton();
        addListenerSMSButton();
        addListenerCompartirButton();
        addListenerCancelerButton();
        addListenerContinuarButton();



        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        email = intent.getStringExtra("email");
        cel = intent.getStringExtra("celular");
        tel = intent.getStringExtra("tel");
        fecha = intent.getStringExtra("fecha");
        hora = intent.getStringExtra("hora");
        vehiculo = intent.getStringExtra("vehiculo");
        tipo = intent.getStringExtra("tipo");
        ano = intent.getStringExtra("ano");
        kilometros = intent.getStringExtra("kilometros");

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvCel = (TextView) findViewById(R.id.tvCelular);

        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvHora = (TextView) findViewById(R.id.tvHora);
        tvVehiculo = (TextView) findViewById(R.id.tvVehiculo);
        tvKilometros = (TextView) findViewById(R.id.tvKilometros);
        tvTipo = (TextView) findViewById(R.id.tvTipo);
        tvAno = (TextView) findViewById(R.id.tvAno);
        tvComentarios = (EditText) findViewById(R.id.tvComentarios);

        tvNombre.setText(nombre);
        tvEmail.setText(email);
        tvCel.setText(cel);

        tvFecha.setText(fecha);
        tvHora.setText(hora);
        tvVehiculo.setText(vehiculo);
        tvTipo.setText(tipo);
        tvAno.setText(ano);
        tvKilometros.setText(kilometros);







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

                    Toast.makeText(CitaServicio_NoLogin_4.this, "ERROR - SMS no enviado...",

                            Toast.LENGTH_LONG).show();

                    ex.printStackTrace();

                }

            }







        });

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
                    Toast.makeText(CitaServicio_NoLogin_4.this, "No dispone de aplicaciones email.",
                            Toast.LENGTH_LONG).show();
                }


            }







        });

    }

    public void addListenerLlamarButton() {

        llamarButton = (ImageButton) findViewById(R.id.llamarButton);

        llamarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                new AlertDialog.Builder(CitaServicio_NoLogin_4.this)
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



    public void addListenerHomeButton() {

        homeButton = (ImageButton) findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CitaServicio_NoLogin_4.this, WelcomeNoLogin.class);

                CitaServicio_NoLogin_4.this.startActivity(myIntent);

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
                    Toast.makeText(CitaServicio_NoLogin_4.this, "No dispone de aplicaciones email.",
                            Toast.LENGTH_LONG).show();
                }


            }



        });

    }


    public void addListenerContinuarButton() {


        continuarButton = (Button) findViewById(R.id.seguir);

        continuarButton.setOnClickListener(new View.OnClickListener() {






            @Override
            public void onClick(View arg0) {


                comentarios = tvComentarios.getText().toString();
                Log.d("CITA A SERVICIO3", "COMENTARIOS: " + comentarios);


                random = randomAlphaNumeric(6);





                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("CITA A SERVICIO3", "ESTOY AQUI: " +response);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                  }
                        }){
                    @Override

                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put(KEY_NOMBRE_CLIENTE,nombre);
                        params.put(KEY_EMAIL_CLIENTE,email);
                        params.put(KEY_CEL_CLIENTE,cel);
                        params.put(KEY_MODELO,vehiculo);
                        params.put(KEY_ANO,ano);
                        params.put(KEY_TIPO,tipo);
                        params.put(KEY_KILOMETROS,kilometros);
                        params.put(KEY_FECHA,fecha);
                        params.put(KEY_HORA,hora);
                        params.put(KEY_COMENTARIOS,comentarios);
                        params.put(KEY_CODIGO,random);
                        params.put(KEY_AGENCIA,codigo_agencia);
                        params.put(KEY_TALLER,email_taller);
                        params.put(KEY_ASESOR,email_asesor);




                        return params;
                    }

                };
                Log.d("CELULAR", "CODIGO-AGENCIA: " + codigo_agencia);
                RequestQueue requestQueue = Volley.newRequestQueue(CitaServicio_NoLogin_4.this);
                requestQueue.add(stringRequest);


                progress = ProgressDialog.show(CitaServicio_NoLogin_4.this, "Cita a Servicio "+random,
                        "Enviando datos...", true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //ENVIO DEL EMAIL



                        try {
                            //m.addAttachment("/sdcard/filelocation");

                        } catch (Exception e) {
                            //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                            Log.e("PartyPlannerActivity", "Could not send email.", e);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.dismiss();
                                Intent myIntent = new Intent(CitaServicio_NoLogin_4.this, WelcomeNoLogin.class);

                                CitaServicio_NoLogin_4.this.startActivity(myIntent);
                            }
                        });
                    }
                }).start();


                //fin captura datos enviar


            }

        });


    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }




    public void addListenerCancelerButton() {

        cancelarButton = (Button) findViewById(R.id.cancelar);

        cancelarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CitaServicio_NoLogin_4.this, WelcomeNoLogin.class);


                CitaServicio_NoLogin_4.this.startActivity(myIntent);

            }

        });
    }
}