package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class main {
	public static void main(String [] args) {
		JFrame frame = initializateFrame();
		initializateLabels(frame);		
		initializeButtons(frame);		
	}
	
	public static JFrame initializateFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("Men\u00FA Principal");
		frame.getContentPane().setLayout(null);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		return frame;
	}
	
	public static void initializateLabels(JFrame frame) {
		JLabel TextoMenu = new JLabel("Bienvenido al sistema de gestion de informes");
		TextoMenu.setBounds(110, 11, 258, 14);
		frame.getContentPane().add(TextoMenu);
		
		JLabel Pregunta = new JLabel("Por favor selecciones una de las siguientes opciones");
		Pregunta.setBounds(87, 81, 312, 14);
		frame.getContentPane().add(Pregunta);
	}
	
	public static void initializeButtons(JFrame frame) {
		JButton btnInforme1 = new JButton("Informe 1"); //Creas el boton con su nombre
		btnInforme1.setBounds(187, 141, 89, 23);	
		btnInforme1.addActionListener(new ActionListener(){	//Creas el listener para cuando se clica en el boton  
		    public void actionPerformed(ActionEvent e){  
		            System.out.println("Boton 1 pulsado");  
		    }  
		});
		
		frame.getContentPane().add(btnInforme1); //Metes el boton en el frame
		
		JButton btnInforme2 = new JButton("Informe 2"); 
		btnInforme2.setBounds(187, 200, 89, 23);	
		btnInforme2.addActionListener(new ActionListener(){	  
		    public void actionPerformed(ActionEvent e){  
		            System.out.println("Boton 2 pulsado");  
		    }  
		});
		
		frame.getContentPane().add(btnInforme2);
		
		JButton btnInforme3 = new JButton("Informe 3"); 
		btnInforme3.setBounds(187, 259, 89, 23);	
		btnInforme3.addActionListener(new ActionListener(){	  
		    public void actionPerformed(ActionEvent e){  
		            System.out.println("Boton 3 pulsado");  
		    }  
		});
		
		frame.getContentPane().add(btnInforme3);
		
		JButton btnSalir = new JButton("Salir"); 
		btnSalir.setBounds(187, 318, 89, 23);	
		btnSalir.addActionListener(new ActionListener(){	  
		    public void actionPerformed(ActionEvent e){  
		    	frame.dispose();
		    }  
		});
		
		frame.getContentPane().add(btnSalir);
	}
}