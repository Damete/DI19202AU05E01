package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import java.util.Scanner;

public class main {

	protected static ArrayList<String> Empleados = new ArrayList<String>();
	protected static int opcion;
	protected static String rutaExportacion;
	protected static String rutaImportacion;
	protected static String fechaInicio;
	protected static String fechaFinal;
	
    public static JFrame initializateFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Men\u00FA Principal");
        frame.getContentPane().setLayout(null);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
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
        JButton btnInforme1 = new JButton("Informe 1"); // Creas el boton con su nombre
        btnInforme1.setBounds(187, 141, 89, 23);
        btnInforme1.addActionListener(new ActionListener() { // Creas el listener para cuando se clica en el boton
            public void actionPerformed(ActionEvent e) {
            	opcion = 1;
            	getRutaImportar();
		    }  
		});
		
		frame.getContentPane().add(btnInforme1); //Metes el boton en el frame
		
		JButton btnInforme2 = new JButton("Informe 2"); 
		btnInforme2.setBounds(187, 200, 89, 23);	
		btnInforme2.addActionListener(new ActionListener(){	  
		    public void actionPerformed(ActionEvent e){
		    	opcion = 2;
		    	getRutaImportar();
		    }  
		});
		
		frame.getContentPane().add(btnInforme2);
		
		JButton btnInforme3 = new JButton("Informe 3"); 
		btnInforme3.setBounds(187, 259, 89, 23);	
		btnInforme3.addActionListener(new ActionListener(){	  
		    public void actionPerformed(ActionEvent e){  
		    	opcion = 3;
		    	getRutaImportar();
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
    
    public static void getRutaImportar() {
    	JFrame sec = new JFrame();
    	sec.setSize(375,125);
    	sec.setResizable(false);
    	sec.getContentPane().setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 37, 339, 20);
		sec.getContentPane().add(textField);
		textField.setColumns(10);		
		JLabel lblRuta = new JLabel("Introduzca la ruta en la que se encuentra el fichero");
		lblRuta.setBounds(34, 11, 307, 14);
		sec.getContentPane().add(lblRuta);
		sec.setVisible(true);
		textField.addActionListener(new ActionListener(){	  
		    public void actionPerformed(ActionEvent e){
		    	rutaImportacion = textField.getText();	
		    	sec.dispose();
		    	if(opcion == 3) {
		    		getFecha1();
		    	}
		    	else {
		    		getRutaExportar();	
		    	}		    	
		    }  
		});
		sec.add(textField);
		sec.setVisible(true);
    }
    
    public static void getRutaExportar() {
    	JFrame sec = new JFrame();
    	sec.setSize(375,125);
    	sec.setResizable(false);
    	sec.getContentPane().setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 37, 339, 20);
		sec.getContentPane().add(textField);
		textField.setColumns(10);		
		JLabel label = new JLabel("Introduzca la ruta en la que se exportar\u00E1 el fichero");
		label.setBounds(34, 11, 307, 14);
		sec.getContentPane().add(label);
		sec.setVisible(true);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rutaExportacion = textField.getText();
				if(opcion == 1) {
					try {
						sec.dispose();
						getReport1(rutaExportacion, rutaImportacion);					
					} catch (ClassNotFoundException | JRException | SQLException e1) {
						e1.printStackTrace();
					}	
				}
				else if(opcion == 2) {
					try {
						sec.dispose();
						getReport2(rutaExportacion, rutaImportacion);
					} catch (ClassNotFoundException | JRException | SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(opcion == 3) {									
					try {
						sec.dispose();
						getReport3();
					} catch (ClassNotFoundException | JRException | SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		sec.add(textField);
		sec.setVisible(true);		
    }
    
    public static void getFecha1() {
    	JFrame sec = new JFrame();
    	sec.setSize(375,125);
    	sec.setResizable(false);
    	sec.getContentPane().setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 37, 339, 20);
		sec.getContentPane().add(textField);
		textField.setColumns(10);		
		JLabel lblRuta = new JLabel("Introduzca la fehca de inicio de la busqueda");
		lblRuta.setBounds(34, 11, 307, 14);
		sec.getContentPane().add(lblRuta);
		sec.setVisible(true);
		textField.addActionListener(new ActionListener(){	  
		    public void actionPerformed(ActionEvent e){
		    	String noFormated = textField.getText();
		    	fechaInicio = formatDate(noFormated);
		    	sec.dispose();
		    	getFecha2();
		    }  
		});
		sec.add(textField);
		sec.setVisible(true);
    }
    
    public static void getFecha2() {
    	JFrame sec = new JFrame();
    	sec.setSize(375,125);
    	sec.setResizable(false);
    	sec.getContentPane().setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 37, 339, 20);
		sec.getContentPane().add(textField);
		textField.setColumns(10);		
		JLabel lblRuta = new JLabel("Introduzca la fehca de fin de la busqueda");
		lblRuta.setBounds(34, 11, 307, 14);
		sec.getContentPane().add(lblRuta);
		sec.setVisible(true);
		textField.addActionListener(new ActionListener(){	  
		    public void actionPerformed(ActionEvent e){
		    	String noFormated = textField.getText();
		    	fechaFinal = formatDate(noFormated);
		    	sec.dispose();
		    	getRutaExportar();
		    }  
		});
		sec.add(textField);
		sec.setVisible(true);
    }
    
    public static void getReport1(String rutaExportar, String rutaImportar) throws JRException, ClassNotFoundException, SQLException {
        JasperDesign jd = JRXmlLoader.load(rutaImportar); //OJO CON ESTA RUTA !!!!
        JRDesignQuery query = new JRDesignQuery();
        
        //Aquí hacemos el dropdown
        String department = showDepartments();        
        
        query.setText("SELECT first_name, last_name, dept_name FROM employees E INNER JOIN dept_emp DE ON E.emp_no = DE.emp_no INNER JOIN departments DEP ON DE.dept_no = DEP.dept_no WHERE dept_name = '"+department+"' ORDER BY first_name");
        jd.setQuery(query);
    	JasperReport jasperReport = JasperCompileManager.compileReport(jd); //Importante pasarle el jd aquí porque si no el report tendrá la query que le pasemos en el jaspersoft
        Connection conn = MySQLConnUtils.getMySQLConnection("194.224.79.42", "employees", "alumne", "tofol"); //Ojo Aquí por la IP y en la clase MYSQL porque allí se define el puerto y puede generar casques !!
        Map<String, Object> parameters = new HashMap<String, Object>();
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

        File outDir = new File(rutaExportar);
        outDir.mkdirs();
        JRPdfExporter exporter = new JRPdfExporter();
        ExporterInput exporterInput = new SimpleExporterInput(print);
        exporter.setExporterInput(exporterInput);
        
        String nombreFichero = department + "Report";
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(rutaExportar + "//" + nombreFichero + ".pdf");
        exporter.setExporterOutput(exporterOutput);
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();      
        
        showSuccessMessage(nombreFichero);

        System.out.print("\n Se ha creado el fichero " + nombreFichero+ ".pdf con exito");
    }
    
    public static void getReport2(String rutaExportar, String rutaImportar) throws JRException, ClassNotFoundException, SQLException{  	
    	JasperDesign jd = JRXmlLoader.load(rutaImportar); //OJO CON ESTA RUTA !!!!
        JRDesignQuery query = new JRDesignQuery();
        
    	String employee = showEmployees();    
    	
    	query.setText("SELECT E.emp_no, first_name, last_name, title FROM employees E INNER JOIN titles T ON E.emp_no = T.emp_no WHERE first_name = '"+employee+"'");
        jd.setQuery(query);
    	JasperReport jasperReport = JasperCompileManager.compileReport(jd); //Importante pasarle el jd aquí porque si no el report tendrá la query que le pasemos en el jaspersoft
        Connection conn = MySQLConnUtils.getMySQLConnection("194.224.79.42", "employees", "alumne", "tofol"); //Ojo Aquí por la IP y en la clase MYSQL porque allí se define el puerto y puede generar casques !!
        Map<String, Object> parameters = new HashMap<String, Object>();
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

        File outDir = new File(rutaExportar);
        outDir.mkdirs();
        JRPdfExporter exporter = new JRPdfExporter();
        ExporterInput exporterInput = new SimpleExporterInput(print);
        exporter.setExporterInput(exporterInput);
        
        String nombreFichero = employee + "Report";
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(rutaExportar + "//" + nombreFichero + ".pdf");
        exporter.setExporterOutput(exporterOutput);
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();      
        
        showSuccessMessage(nombreFichero);

        System.out.print("\n Se ha creado el fichero " + nombreFichero+ ".pdf con exito");
    }
    
    public static void getReport3() throws JRException, ClassNotFoundException, SQLException {
    	JasperDesign jd = JRXmlLoader.load(rutaImportacion); //OJO CON ESTA RUTA !!!!
        JRDesignQuery query = new JRDesignQuery();        
        
    	
    	query.setText("SELECT E.emp_no, first_name, last_name, dept_name FROM employees E INNER JOIN dept_emp DE ON E.emp_no = DE.emp_no INNER JOIN departments DEP ON DE.dept_no = DEP.dept_no WHERE from_date > '"+fechaInicio+"' AND from_date < '"+fechaFinal+"'");
        jd.setQuery(query);
    	JasperReport jasperReport = JasperCompileManager.compileReport(jd); //Importante pasarle el jd aquí porque si no el report tendrá la query que le pasemos en el jaspersoft
        Connection conn = MySQLConnUtils.getMySQLConnection("194.224.79.42", "employees", "alumne", "tofol"); //Ojo Aquí por la IP y en la clase MYSQL porque allí se define el puerto y puede generar casques !!
        Map<String, Object> parameters = new HashMap<String, Object>();
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        
        //String rutaExportar = System.getProperty("user.home") + "/Desktop";

        File outDir = new File(rutaExportacion);
        outDir.mkdirs();
        JRPdfExporter exporter = new JRPdfExporter();
        ExporterInput exporterInput = new SimpleExporterInput(print);
        exporter.setExporterInput(exporterInput);
        
        String nombreFichero = fechaFinal + "Report";
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(rutaExportacion + "//" + nombreFichero + ".pdf");
        exporter.setExporterOutput(exporterOutput);
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();      
        
        showSuccessMessage(nombreFichero);

        System.out.print("\n Se ha creado el fichero " + nombreFichero+ ".pdf con exito");
    }
    
    public static String showDepartments() {
    	String department = "";
        Object[] departments = {"Customer Service", "Development", "Finance", "Human Resources", "Marketing", "Production", "Quality Management", "Research", "Sales"};
        JFrame f = new JFrame();
        String s = (String)JOptionPane.showInputDialog(f, "Departamento a visualizar", "Seleccione un departamento", JOptionPane.QUESTION_MESSAGE, null, departments, "Production");
        if((s != null) && s.length() > 0) {
        	for(int i = 0; i < departments.length; i++) {
            	if(s.equals(departments[i])) {
            		department = (String) departments[i];
            	}
            }	
        }       
        
        return department;
    }
    
    public static String showEmployees() {
    	String employee = "";
    	try {
			MySQLConnUtils BBDD = new MySQLConnUtils();
			Connection conexion = BBDD.getMySQLConnection("194.224.79.42", "employees", "alumne", "tofol"); //OJO igual que en el otro método
			Statement statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery("SELECT first_name FROM employees");
			fetchResults(resultado);			
			
			//Una vez tenemos los resultados de la BBDD creamos el dropdown y le metemos la información
	    	Object [] empleados = new Object[500];
	    	for(int i = 0; i<Empleados.size(); i++) {
	    		empleados[i] = Empleados.get(i);
	    	}
	    	JFrame f = new JFrame();
	    	String s = (String)JOptionPane.showInputDialog(f, "Seleccione un empleado", "Empleados", JOptionPane.QUESTION_MESSAGE, null, empleados, "IEEE");
	    	
	    	if((s != null) && s.length() > 0) {
	    		for(int i = 0; i < empleados.length; i++) {
	    			if(s.equals(empleados[i])) {
	    				employee = (String) empleados[i];
	    			}
	    		}
	    	}
		}
		catch (Exception a) {
			a.getMessage();
		}
    	return employee;
    }
    
    public static void fetchResults(ResultSet resultset) throws Exception{
    	try {
			while(resultset.next()) {
				String nombre = resultset.getString("first_name");				
				Empleados.add(nombre);
			}
		}
		catch(Exception e) {
			e.getMessage();
		}
    }
    
    public static void showSuccessMessage(String nombreFichero){
    	JFrame frame = new JFrame();    	
    	JOptionPane.showMessageDialog(frame, "Fichero " + nombreFichero + ".pdf creado con exito", "Success", JOptionPane.PLAIN_MESSAGE);
    }
    
    public static String formatDate(String fecha) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    	LocalDate date = LocalDate.parse(fecha, formatter);
    	String formatted = date.toString();
    	return formatted;
    }

    public static void main(String [] args) {
		JFrame frame = initializateFrame();
		initializateLabels(frame);		
		initializeButtons(frame);		
	}
}