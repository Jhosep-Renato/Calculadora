package org.japrova.appcalculadora.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

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

        if(!campo.isEmpty()) {
            try {
                String ultimo = String.valueOf(campo.charAt(campo.length() - 1));
                numero = Integer.parseInt(ultimo);

            } catch (NumberFormatException nf) {
                System.out.println(nf.getMessage());
            }
        }

        if(e.getSource() == btnMult && !campo.isEmpty() && numero != -1) {
            inputRespuesta.setText(inputRespuesta.getText() + "*");
        } else if (e.getSource() == btnDivision && !campo.isEmpty() && numero != -1) {
            inputRespuesta.setText(inputRespuesta.getText() + "/");
        } else if (e.getSource() == btnSuma && !campo.isEmpty() && numero != -1) {
            inputRespuesta.setText(inputRespuesta.getText() + "+");
        } else if (e.getSource() == btnResta && !campo.isEmpty() && numero != -1) {
            inputRespuesta.setText(inputRespuesta.getText() + "-");
        } else if (e.getSource() == btnIgual && !campo.isEmpty()) {
        } else {
            if (valor != null) {
                inputRespuesta.setText(inputRespuesta.getText() + valor);
            }
        }
    }

    private void inicializarAcciones() {
        JButton[] botones = {b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, btnDivision, btnMult, btnResta, btnSuma, btnIgual};

        for (JButton botone : botones) {
            botone.addActionListener(this);
        }
    }

    private HashMap<JButton, String> agregarValorBoton(JButton... botones) {

        HashMap<JButton, String> valoresBotones = new HashMap<>();

        for (int i = 0; i < botones.length; i++) {
            valoresBotones.put(botones[i], String.valueOf(i));
        }

        return valoresBotones;
    }

    private void ordenarCampos(String campo) {
        char[] digitos = campo.toCharArray();
        double totalCampo = 0;
        List<Character> operaciones = new ArrayList<>();
        List<Double> campos = new ArrayList<>();

        for (char d: digitos) {
            if (Character.isDigit(d)) {
                totalCampo += d;
                continue;
            }
            campos.add(totalCampo);
            operaciones.add(d);
        }
        ListIterator<Double> listIterator = campos.listIterator();

        int indice = 0;
        while (listIterator.hasNext()) {

            double valor = listIterator.next();

            if(operaciones.get(indice++) == '+') {

                if (listIterator.hasNext()) {

                    listIterator.set(valor + listIterator.next());
                }
            } else if (operaciones.get(indice++) == '-') {

                if (listIterator.hasNext()) {

                    listIterator.set(valor - listIterator.next());
                }
            }
        }


    }

    private void verificar(List<Character> op) {
        op.forEach(System.out::println);
    }
}
