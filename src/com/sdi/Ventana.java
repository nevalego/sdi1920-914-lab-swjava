package com.sdi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.node.ObjectNode;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Ventana extends JFrame {

    private JPanel contentPane;
    private JPanel panel;
    private JButton btnActualizar;
    private JButton btnApagar;
    private JLabel lblMemoriaLibreActual;
    private int peticiones = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Ventana frame = new Ventana();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public Ventana() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	setTitle("Aplicación Monitorización");
	setSize(500, 200);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0, 0));
	contentPane.add(getPanel(), BorderLayout.CENTER);
	setContentPane(contentPane);
    }

    private JPanel getPanel() {
	if (panel == null) {
	    panel = new JPanel();
	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.add(getBtnActualizar());
	    panel.add(getBtnApagar());
	    panel.add(getLblMemoriaLibreActual());
	}
	return panel;
    }

    private JButton getBtnActualizar() {
	if (btnActualizar == null) {
	    btnActualizar = new JButton("Actualizar Memoria");
	    btnActualizar.setBorder(new EmptyBorder(10, 10, 10, 10));
	    btnActualizar.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		    peticiones++;
		    ActualizarMemoriaThread hilo = new ActualizarMemoriaThread(getVentana());
		    hilo.start();
		}
	    });
	}
	return btnActualizar;
    }
    
    public void actualizarMemoria(String memoria) {
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		lblMemoriaLibreActual.setText("Memoria libre: "+ memoria+" ("+peticiones+")");
	    }
	});
    }

    private JButton getBtnApagar() {
	if (btnApagar == null) {
	    btnApagar = new JButton("Apagar Equipo");
	    btnApagar.setBorder(new EmptyBorder(10, 10, 10, 10));
	    btnApagar.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		    JOptionPane.showMessageDialog(getVentana(),
			    "Enviado apagar!");
		}
	    });
	}
	return btnApagar;
    }

    private Ventana getVentana() {
	return this;
    }

    private JLabel getLblMemoriaLibreActual() {
	if (lblMemoriaLibreActual == null) {
	    lblMemoriaLibreActual = new JLabel("Memoria libre:");
	    lblMemoriaLibreActual.setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	return lblMemoriaLibreActual;
    }
}
