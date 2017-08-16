<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){




    


 $nombre_cliente = $_POST['nombre_cliente'];
 $email_cliente = $_POST['email_cliente'];
 $cel_cliente = $_POST['cel_cliente'];
 $modelo = $_POST['modelo'];
 $tipo = $_POST['tipo'];
 $kilometros = $_POST['kilometros'];
 $ano = $_POST['ano'];
 $fecha = $_POST['fecha'];
 $hora = $_POST['hora'];
 $comentarios = $_POST['comentarios'];
 $codigo = $_POST['codigo'];
 $agencia_cita = $_POST['agencia_cita'];
 $email_taller = $_POST['email_taller'];
  $email_asesor = $_POST['email_asesor'];
 echo $agencia_cita;





 
 
 require_once('dbConnect.php');
 
 $sql = "INSERT INTO tb_citas_servicio (nombre_cliente,email_cliente,cel_cliente,
 		modelo, ano, kilometros, tipo,fecha,hora,comentarios,codigo,agencia_cita) VALUES ('$nombre_cliente','$email_cliente','$cel_cliente',
 		'$modelo','$ano','$kilometros','$tipo','$fecha','$hora','$comentarios','$codigo','$agencia_cita')
 		";
 
  if(mysqli_query($con,$sql)){
echo "AGENCIA CITA=".$agencia_cita;




 }else{
 echo "Could not register";
 
 }
 }else{
echo 'error';
 }



$to = $email_taller;
$subject = '(EMAIL AL TALLER) Cita a Servicio '.$codigo.'  desde la app Mi Asesor Automotriz (Android)';
$from = 'Mi Asesor Automotriz';
 
// To send HTML mail, the Content-type header must be set
$headers  = 'MIME-Version: 1.0' . "\r\n";
$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
$headers .= 'From: webmaster@example.com' . "\r\n" .
    'Reply-To: webmaster@example.com' . "\r\n" .
    'X-Mailer: PHP/' . phpversion();
// Compose a simple HTML email message
$message = '<html><body>';
$message .= '<h1 style="color:#f40;">Hola '.$nombre_cliente.'!</h1>';
$message .= '<p style="color:#080;font-size:18px;">Esto es una CITA A SERVICIO creada desde la App MI ASESOR AUTOMOTRIZ</p>';
$message .= '<p>Cliente: '.$nombre_cliente.'</p>';
$message .= '<p>Email del Cliente: '.$email_usuario.'</p>';
$message .= '<p>Teléfono del Cliente: '.$cel_cliente.'</p>';
$message .= '<p>Auto: '.$modelo.'</p>';
$message .= '<p>Año: '.$ano.'</p>';
$message .= '<p>Kilómetros: '.$km.'</p>';
$message .= '<p>Tipo de cita: '.$tipo.'</p>';
$message .= '<p>Fecha de la cita: '.$fecha.'</p>';
$message .= '<p>Hora de la cita: '.$hora.'</p>';
$message .= '<p>Comentarios del cliente: '.$comentarios.'</p>';
$message .= '<p>Código de la cita: '.$codigo.'</p>';
$message .= '<p>Gracias por su confianza. En breve nos pondremos en contacto con usted</p>';


$message .= '</body></html>';
 
// Sending email
if(mail($to, $subject, $message, $headers)){
    echo 'Your mail has been sent successfully.';
} else{
    echo 'Unable to send email. Please try again.';
}

$to = $email_cliente;
$subject = '(EMAIL AL CLIENTE) Cita a Servicio '.$codigo.'  desde la app Mi Asesor Automotriz (Android)';
$from = 'Mi Asesor Automotriz';
 
// To send HTML mail, the Content-type header must be set
$headers  = 'MIME-Version: 1.0' . "\r\n";
$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
$headers .= 'From: webmaster@example.com' . "\r\n" .
    'Reply-To: webmaster@example.com' . "\r\n" .
    'X-Mailer: PHP/' . phpversion();
 
// Compose a simple HTML email message
$message = '<html><body>';
$message .= '<h1 style="color:#f40;">Hola '.$nombre_cliente.'!</h1>';
$message .= '<p style="color:#080;font-size:18px;">Esto es una CITA A SERVICIO creada desde la App MI ASESOR AUTOMOTRIZ</p>';
$message .= '<p>Cliente: '.$nombre_cliente.'</p>';
$message .= '<p>Email del Cliente: '.$email_cliente.'</p>';
$message .= '<p>Teléfono del Cliente: '.$cel_cliente.'</p>';
$message .= '<p>Auto: '.$modelo.'</p>';
$message .= '<p>Año: '.$ano.'</p>';
$message .= '<p>Kilómetros: '.$km.'</p>';
$message .= '<p>Tipo de cita: '.$tipo.'</p>';
$message .= '<p>Fecha de la cita: '.$fecha.'</p>';
$message .= '<p>Hora de la cita: '.$hora.'</p>';
$message .= '<p>Comentarios del cliente: '.$comentarios.'</p>';
$message .= '<p>Código de la cita: '.$codigo.'</p>';
$message .= '<p>Gracias por su confianza. En breve nos pondremos en contacto con usted</p>';


$message .= '</body></html>';
 
// Sending email
if(mail($to, $subject, $message, $headers)){
    echo 'Your mail has been sent successfully.';
} else{
    echo 'Unable to send email. Please try again.';
}

$to = $email_asesor;
$subject = '(EMAIL AL ASESOR) Cita a Servicio '.$codigo.' desde la app Mi Asesor Automotriz (Android)';
$from = 'Mi Asesor Automotriz';
 
// To send HTML mail, the Content-type header must be set
$headers  = 'MIME-Version: 1.0' . "\r\n";
$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
$headers .= 'From: webmaster@example.com' . "\r\n" .
    'Reply-To: webmaster@example.com' . "\r\n" .
    'X-Mailer: PHP/' . phpversion();
// Compose a simple HTML email message
$message = '<html><body>';
$message .= '<h1 style="color:#f40;">Hola '.$nombre_cliente.'!</h1>';
$message .= '<p style="color:#080;font-size:18px;">Esto es una CITA A SERVICIO creada desde la App MI ASESOR AUTOMOTRIZ</p>';
$message .= '<p>Cliente: '.$nombre_cliente.'</p>';
$message .= '<p>Email del Cliente: '.$email_cliente.'</p>';
$message .= '<p>Teléfono del Cliente: '.$cel_cliente.'</p>';
$message .= '<p>Auto: '.$modelo.'</p>';
$message .= '<p>Año: '.$ano.'</p>';
$message .= '<p>Kilómetros: '.$km.'</p>';
$message .= '<p>Tipo de cita: '.$tipo.'</p>';
$message .= '<p>Fecha de la cita: '.$fecha.'</p>';
$message .= '<p>Hora de la cita: '.$hora.'</p>';
$message .= '<p>Comentarios del cliente: '.$comentarios.'</p>';
$message .= '<p>Código de la cita: '.$codigo.'</p>';
$message .= '<p>Gracias por su confianza. En breve nos pondremos en contacto con usted</p>';


$message .= '</body></html>';
 
// Sending email
if(mail($to, $subject, $message, $headers)){
    echo 'Your mail has been sent successfully.';
} else{
    echo 'Unable to send email. Please try again.';
}






