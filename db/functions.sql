DELIMITER $$
DROP FUNCTION IF EXISTS pagarImpuesto $$


CREATE FUNCTION pagarImpuesto(monto_remesa double, u text,  c text,  idRemesa  int,  montoPagado double, tipo_cambio double) 
returns boolean
begin


	insert into remesas(fecha_recepcion, fecha_pago_impuesto, monto, usuario, contrasena, correlativoRemesa, montoPagado, montoTasaCambio)
    values (sysdate(), null, monto_remesa, u,c,idRemesa, montoPagado, tipo_cambio);
    
Return 1;
end;    
$$

