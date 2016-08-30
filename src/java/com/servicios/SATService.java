/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicios;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author luisperalta
 */
@WebService(serviceName = "SATService")
public class SATService {
    
    @WebMethod(operationName = "PagarImpuestoRemesa")     
    public String PagarImpuestoRemesa
        (@WebParam(name = "usuario")String u, @WebParam(name = "contrasena")String c, 
                @WebParam(name = "id_remesa")int i, @WebParam(name = "monto_remesa")double m){
        return new String();
    }
    
    @WebMethod(operationName = "ConsultaPorRemesa")   
    public String ConsultaPorRemesa(@WebParam(name = "monto")double m){
        return new String();
    }
    
    
    
//    @WebMethod(operationName = "")     
//    @WebParam(name = "")
}
