/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicios;

import java.sql.CallableStatement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.*;

/**
 *
 * @author luisperalta
 */
@WebService(serviceName = "SATService")
public class SATService {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/sat";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "holi123";
   

    
    
    
    @WebMethod(operationName = "PagarImpuestoRemesa")     
    public String PagarImpuestoRemesa
        (@WebParam(name = "usuario")String u, @WebParam(name = "contrasena")String c, 
                @WebParam(name = "id_remesa")int idRemesa, @WebParam(name = "monto_remesa")double m){
            //(Monto de la Remesa en dólares * 0.05) * Tipo Cambio del día = Impuesto SAT en Quetzales.
            double monto_remesa = m;
            double porcentaje = monto_remesa * 0.05;
            double tipo_cambio = getTipoCambio();
             if (tipo_cambio == 0){
            String respuesta = "<ConsultaImpuestoPorRemesa>\n" +
"<Exito>0</Exito>\n" +
"<Descripcion>Error de\n" +
"conexión a la base de datos\n" +
"</Descripcion>\n" +
"</ConsultaImpuestoPorRemesa>";
            return respuesta;
        }
            double impuestoSatQuetzales = porcentaje * tipo_cambio;
            if (pagarImpuesto(monto_remesa * tipo_cambio, u,c,idRemesa,impuestoSatQuetzales, tipo_cambio)){
                String respuesta =  "<PagarImpuestoRemesa>\n" +
"<Exito>1</Exito>\n" +
"<MontoInicialUSD>" + monto_remesa + "</MontoInicialUSD>\n" +
"<MontoInicialQ>" + (monto_remesa*tipo_cambio) + "</MontoInicialQ>\n" +
"<ImpuestoCobradoUSD>" + porcentaje + "</ImpuestoCobradoUSD>\n" +
"<ImpuestoCobradoQ>" + impuestoSatQuetzales + "</ImpuestoCobradoQ>\n" +
"<CantidadSinImpuestoUSD>" + (monto_remesa-porcentaje) + "</CantidadSinImpuestoUSD>\n" +
"<CantidadSinImpuestoQ>" + ((monto_remesa * tipo_cambio) - impuestoSatQuetzales) + "</CantidadSinImpuestoQ>\n" +
"</PagarImpuestoRemesa>";
                
                return respuesta;
            }
            
            
            
            
        String respuesta = "<PagarImpuestoRemesa>\n" +
"<Exito>0</Exito>\n" +
"<Descripcion>Error de\n" +
"conexión a la base de datos\n" +
"</Descripcion>\n" +
"</PagarImpuestoRemesa";
        
        return respuesta;
    }
    
    @WebMethod(operationName = "ConsultaPorRemesa")   
    public String ConsultaPorRemesa(@WebParam(name = "monto")double m){
        String respuesta = "";
        
        double monto_remesa = m;
        double porcentaje = monto_remesa * 0.05;
        double tipo_cambio = getTipoCambio();
        if (tipo_cambio == 0){
            respuesta = "<ConsultaImpuestoPorRemesa>\n" +
"<Exito>0</Exito>\n" +
"<Descripcion>Error de\n" +
"conexión a la base de datos\n" +
"</Descripcion>\n" +
"</ConsultaImpuestoPorRemesa>";
            return respuesta;
        }
        double impuestoSatQuetzales = porcentaje * tipo_cambio;
        
         respuesta = "< ConsultaImpuestoPorRemesa>\n" +
"<Exito>1</Exito>\n" +
"<MontoInicialUSD>"+ monto_remesa +"</MontoInicialUSD>\n" +
"<MontoInicialQ>"+ monto_remesa*tipo_cambio +"</MontoInicialQ>\n" +
"<ImpuestoUSD>"+ porcentaje +"</ImpuestoUSD>\n" +
"<ImpuestoQ>"+ impuestoSatQuetzales +"</ImpuestoQ>\n" +
"<CantidadSinImpuestoUSD>"+ (monto_remesa-porcentaje) +"</CantidadSinImpuestoUSD>\n" +
"<CantidadSinImpuestoQ>"+ ((monto_remesa*tipo_cambio)-impuestoSatQuetzales) +"</CantidadSinImpuestoQ>\n" +
"</ ConsultaImpuestoPorRemesa>";
        
        
        
        return respuesta;
    }
    
    //    @WebMethod(operationName = "")     
    //    @WebParam(name = "")

    private double getTipoCambio() {
        return 7.55;
    }

    private boolean pagarImpuesto(double monto_remesa, String u, String c, int idRemesa, double impuestoSatQuetzales, double tipo_cambio) {
        Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      System.out.println("Creating database...");
      stmt = conn.createStatement();
      
      String sql = "select pagarImpuesto(" + monto_remesa + ",'" + u + "','" + c + "',"+ idRemesa + ","+ impuestoSatQuetzales + "," + tipo_cambio + ");";
      boolean val = stmt.execute(sql);
      return val;
   
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();

   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   return true;
    }

    
}
