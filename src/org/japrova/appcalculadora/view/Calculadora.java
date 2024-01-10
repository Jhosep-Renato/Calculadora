package org.japrova.appcalculadora.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import static org.japrova.appcalculadora.model.Operacion.*;
public class Calculadora extends JFrame implements ActionListener {
    private JTextField inputRespuesta;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton b0;
    private JButton btnMult;
    private JButton btnResta;
    private JButton btnDivision;
    private JPanel panel;
    private JButton btnSuma;
    private JPanel panel2;
    private JButton btnIgual;
    private boolean primerResultado = false;

    public Calculadora() {
        getContentPane().add(panel);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300, 300);
        inicializarAcciones();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        HashMap<JButton, String> claveValor = agregarValorBoton(b0, b1, b2, b3, b4, b5, b6, b7, b8, b9);

        String valor = claveValor.get(e.getSource());
        String campo = inputRespuesta.getText();
        int numero = -1;

        if (!campo.isEmpty()) {
            try {
                String ultimo = String.valueOf(campo.charAt(campo.length() - 1));
                numero = Integer.parseInt(ultimo);

            } catch (NumberFormatException nf) {
                System.out.println(nf.getMessage());
            }
        }

        if (e.getSource() == btnMult && !campo.isEmpty() && numero != -1) {
            inputRespuesta.setText(inputRespuesta.getText() + "*");
        } else if (e.getSource() == btnDivision && !campo.isEmpty() && numero != -1) {
            inputRespuesta.setText(inputRespuesta.getText() + "/");
        } else if (e.getSource() == btnSuma && !campo.isEmpty() && numero != -1) {
            inputRespuesta.setText(inputRespuesta.getText() + "+");
        } else if (e.getSource() == btnResta && !campo.isEmpty() && numero != -1) {
            inputRespuesta.setText(inputRespuesta.getText() + "-");
        } else if (e.getSource() == btnIgual && !campo.isEmpty() && validarCampos(campo)) {
            inputRespuesta.setText(String.valueOf(resolverOperaciones(campo)));
        } else {
            if (valor != null) {
                inputRespuesta.setText(inputRespuesta.getText() + valor);
            }
        }
    }

    private void inicializarAcciones() { // MÉTODO PARA INICIALIZAR EL LISTENER A CADA BOTÓN
        JButton[] botones = {b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, btnDivision, btnMult, btnResta, btnSuma, btnIgual};

        for (JButton botone : botones) {
            botone.addActionListener(this);
        }
    }

    private HashMap<JButton, String> agregarValorBoton(JButton... botones) { // LOS BOTONES DE NÚMEROS TENDRÁN SU PROPIOS VALORES

        HashMap<JButton, String> valoresBotones = new HashMap<>();

        for (int i = 0; i < botones.length; i++) {
            valoresBotones.put(botones[i], String.valueOf(i));
        }

        return valoresBotones;
    }

    private double resolverOperaciones(String campo) {

        String op = tipoOperacion(campo);

        String[] campos =  campo.split("[" + op + "]");
        double resultado = 0;

        switch (op) {
            case "+" -> resultado = Double.parseDouble(campos[0]) + Double.parseDouble(campos[1]);
            case "-" -> resultado = Double.parseDouble(campos[0]) - Double.parseDouble(campos[1]);
            case "*" -> resultado = Double.parseDouble(campos[0]) * Double.parseDouble(campos[1]);
            case "/" -> resultado = Double.parseDouble(campos[0]) / Double.parseDouble(campos[1]);
        }
        primerResultado = true;
        return resultado;
    }


    private boolean validarCampos(String campo) {

        String[] campos = campo.split("[" + tipoOperacion(campo) + "]");

        if (campos.length > 1) {
               return true;
        }
        return false;
    }
    private static String tipoOperacion(String campo) {
        String[] operaciones = { SUM.getOperacion(), RES.getOperacion(), DIV.getOperacion(), MULT.getOperacion()};
        String op = null; // Hace referencia a operación

        for (int i = 0; i < operaciones.length; i++) {
            if (campo.contains(operaciones[i])) {
                op = operaciones[i];
                break;
            }
        }
        return op;
    }
}
