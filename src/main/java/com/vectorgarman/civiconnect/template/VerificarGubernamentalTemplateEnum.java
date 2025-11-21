package com.vectorgarman.civiconnect.template;

public enum VerificarGubernamentalTemplateEnum {
    VERIFICACION_CAMBIO_CONTRASENA(
            """
            <!DOCTYPE html>
            <html lang='es'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Código de Verificación</title>
            </head>
            <body style='margin: 0; padding: 0; font-family: Arial, sans-serif; background-color: #f4f4f4;'>
                <table width='100%' cellpadding='0' cellspacing='0' style='background-color: #f4f4f4; padding: 20px;'>
                    <tr>
                        <td align='center'>
                            <table width='600' cellpadding='0' cellspacing='0' style='background-color: #ffffff; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);'>
                                <tr>
                                    <td style='padding: 40px 30px; text-align: center;'>
                                        <h1 style='color: #333333; margin: 0 0 20px 0; font-size: 24px;'>Cambio de Contraseña</h1>
                                        <p style='color: #666666; font-size: 16px; line-height: 1.5; margin: 0 0 20px 0;'>
                                            Has solicitado cambiar tu contraseña:
                                        </p>
                                        <p style='color: #0066cc; font-size: 18px; font-weight: bold; margin: 0 0 30px 0;'>
                                            {EMAIL}
                                        </p>
                                        <p style='color: #666666; font-size: 16px; line-height: 1.5; margin: 0 0 15px 0;'>
                                            Copia el siguiente código y pégalo en la aplicación:
                                        </p>
                                        <p style='color: #ff6b6b; font-size: 14px; font-weight: bold; margin: 20px 0 0 0;'>
                                            ⏰ Este código expirará en <b>15 minutos</b>
                                        </p>
                                        <div style='background-color: #f8f9fa; padding: 20px; border-radius: 8px; border: 2px dashed #0066cc; margin: 20px 0;'>
                                            <p style='color: #333333; font-size: 24px; font-weight: bold; letter-spacing: 2px; margin: 0; font-family: monospace;'>
                                                {TOKEN}
                                            </p>
                                        </div>
                                        <p style='color: #999999; font-size: 14px; margin: 30px 0 0 0; line-height: 1.5;'>
                                            Si no solicitaste cambiar tu contraseña, puedes ignorar este correo.
                                        </p>
                                    </td>
                                </tr>
                                <tr>
                                    <td style='background-color: #f8f8f8; padding: 20px 30px; text-align: center; border-radius: 0 0 8px 8px;'>
                                        <p style='color: #999999; font-size: 12px; margin: 0;'>
                                            © 2025 CiviConnect. Todos los derechos reservados.
                                        </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </body>
            </html>
            """
    ),
    VERIFICACION_GUBERNAMENTAL(
            """
            <!DOCTYPE html>
            <html lang='es'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Verificación de Correo</title>
            </head>
            <body style='margin: 0; padding: 0; font-family: Arial, sans-serif; background-color: #f4f4f4;'>
                <table width='100%' cellpadding='0' cellspacing='0' style='background-color: #f4f4f4; padding: 20px;'>
                    <tr>
                        <td align='center'>
                            <table width='600' cellpadding='0' cellspacing='0' style='background-color: #ffffff; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);'>
                                <tr>
                                    <td style='padding: 40px 30px; text-align: center;'>
                                        <h1 style='color: #333333; margin: 0 0 20px 0; font-size: 24px;'>Verificación de Correo Electrónico</h1>
                                        <p style='color: #666666; font-size: 16px; line-height: 1.5; margin: 0 0 30px 0;'>
                                            Haz clic en el siguiente enlace para verificar el correo, <br/>
                                        </p>
                                        <p style='color: #0066cc; font-size: 18px; font-weight: bold; margin: 0 0 30px 0;'>
                                            {EMAIL}
                                        </p>
                                        <p style='color: #ff6b6b; font-size: 14px; font-weight: bold; margin: 20px 0 0 0;'>
                                            ⏰ Este link expirará en <b>48 horas</b></p>
                                        <a href='{URL_VERIFICACION}'
                                           style='display: inline-block; padding: 15px 40px; background-color: #0066cc; color: #ffffff;
                                           text-decoration: none; border-radius: 5px; font-size: 16px; font-weight: bold;'>
                                            Verificar Correo
                                        </a>
                                        <p style='color: #999999; font-size: 14px; margin: 30px 0 0 0; line-height: 1.5;'>
                                            Si no solicitaste esta verificación, puedes ignorar este correo.
                                        </p>
                                    </td>
                                </tr>
                                <tr>
                                    <td style='background-color: #f8f8f8; padding: 20px 30px; text-align: center; border-radius: 0 0 8px 8px;'>
                                        <p style='color: #999999; font-size: 12px; margin: 0;'>
                                            © 2025 CiviConnect. Todos los derechos reservados.
                                        </p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </body>
            </html>
            """
    ),
    VERIFICACION_EXITOSA(
            """
            <!DOCTYPE html>
            <html lang='es'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Verificación Exitosa</title>
                <style>
                    * {
                        margin: 0;
                        padding: 0;
                        box-sizing: border-box;
                    }
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        min-height: 100vh;
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    }
                    .container {
                        text-align: center;
                        background: white;
                        padding: 60px 40px;
                        border-radius: 20px;
                        box-shadow: 0 20px 60px rgba(0,0,0,0.3);
                        max-width: 500px;
                        animation: slideIn 0.5s ease-out;
                    }
                    @keyframes slideIn {
                        from {
                            opacity: 0;
                            transform: translateY(-30px);
                        }
                        to {
                            opacity: 1;
                            transform: translateY(0);
                        }
                    }
                    .icon {
                        font-size: 80px;
                        margin-bottom: 20px;
                        color: #4CAF50;
                    }
                    h1 {
                        color: #333;
                        font-size: 2.5em;
                        margin-bottom: 20px;
                        font-weight: 600;
                    }
                    p {
                        color: #666;
                        font-size: 1.1em;
                        line-height: 1.6;
                        margin-bottom: 15px;
                    }
                    .email {
                        color: #667eea;
                        font-weight: 600;
                    }
                    .button {
                        display: inline-block;
                        padding: 15px 40px;
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        color: white;
                        text-decoration: none;
                        border-radius: 50px;
                        font-weight: 600;
                        margin-top: 20px;
                        transition: transform 0.3s, box-shadow 0.3s;
                    }
                    .button:hover {
                        transform: translateY(-2px);
                        box-shadow: 0 10px 20px rgba(102, 126, 234, 0.4);
                    }
                </style>
            </head>
            <body>
                <div class='container'>
                    <div class='icon'>✓</div>
                    <h1>¡Verificación Exitosa!</h1>
                    <p>La cuenta gubernamental con el correo:</p>
                    <p class='email'>{EMAIL}</p>
                    <p>ha sido verificada exitosamente.</p>
                    <p>Ya puedes acceder a todas las funcionalidades de la plataforma.</p>
                    <p><br/><b>Ya puedes cerrar esta pestaña.<br/><b></p>
                </div>
            </body>
            </html>
            """
    ),
    VERIFICACION_ERROR(
            """
            <!DOCTYPE html>
            <html lang='es'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Error de Verificación</title>
                <style>
                    * {
                        margin: 0;
                        padding: 0;
                        box-sizing: border-box;
                    }
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        min-height: 100vh;
                        background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
                    }
                    .container {
                        text-align: center;
                        background: white;
                        padding: 60px 40px;
                        border-radius: 20px;
                        box-shadow: 0 20px 60px rgba(0,0,0,0.3);
                        max-width: 500px;
                        animation: slideIn 0.5s ease-out;
                    }
                    @keyframes slideIn {
                        from {
                            opacity: 0;
                            transform: translateY(-30px);
                        }
                        to {
                            opacity: 1;
                            transform: translateY(0);
                        }
                    }
                    .icon {
                        font-size: 80px;
                        margin-bottom: 20px;
                        color: #f5576c;
                    }
                    h1 {
                        color: #333;
                        font-size: 2.5em;
                        margin-bottom: 20px;
                        font-weight: 600;
                    }
                    p {
                        color: #666;
                        font-size: 1.1em;
                        line-height: 1.6;
                        margin-bottom: 15px;
                    }
                    .error-message {
                        color: #f5576c;
                        font-weight: 600;
                    }
                    .button {
                        display: inline-block;
                        padding: 15px 40px;
                        background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
                        color: white;
                        text-decoration: none;
                        border-radius: 50px;
                        font-weight: 600;
                        margin-top: 20px;
                        transition: transform 0.3s, box-shadow 0.3s;
                    }
                    .button:hover {
                        transform: translateY(-2px);
                        box-shadow: 0 10px 20px rgba(245, 87, 108, 0.4);
                    }
                </style>
            </head>
            <body>
                <div class='container'>
                    <div class='icon'>✗</div>
                    <h1>Error de Verificación</h1>
                    <p class='error-message'>{MENSAJE_ERROR}</p>
                    <p>El enlace de verificación puede haber expirado o ser inválido.</p>
                    <p>Por favor, solicita un nuevo enlace de verificación o contacta al administrador.</p>
                    <p><br/><b>Ya puedes cerrar esta pestaña.<br/><b></p>
                </div>
            </body>
            </html>
            """
    );

    private final String template;

    VerificarGubernamentalTemplateEnum(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    /**
     * Reemplaza las variables en el template
     * @param variables pares de clave-valor para reemplazar (ej: "EMAIL", "usuario@ejemplo.com")
     * @return template con variables reemplazadas
     */
    public String build(String... variables) {
        String result = template;
        for (int i = 0; i < variables.length - 1; i += 2) {
            result = result.replace("{" + variables[i] + "}", variables[i + 1]);
        }
        return result;
    }
}
