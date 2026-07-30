/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.Calendar;

/**
 *
 * @author Ian Suazo Palao
 */
public class CuentaBancaria {
    
    protected int numero;
    protected double saldo;
    protected String cliente;
    protected String moneda;
    protected Calendar apertura;
    protected TipoCuenta tipo;
    protected String[] historial=new String[50];
    protected int CantidadMovimentos;

    public CuentaBancaria(int numero, double saldo, String cliente, String moneda, Calendar apertura, TipoCuenta tipo) {
        this.numero = numero;
        this.saldo = 0.0;
        this.cliente = cliente;
        this.moneda = moneda;
        this.apertura = Calendar.getInstance();
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public void depositar (double monto){
        if (monto <=0){
            throw new IllegalArgumentException("El monto a depositar debe ser mayor que 0.");
        }
        
        saldo+= monto;
        registroHistorial("Deposito: "+monto+ " "+moneda+ "saldo: "+saldo);
    }
    
    public boolean retirar (double monto){
        if (monto <=0){
            throw new IllegalArgumentException("El monto a retirar debe ser mayor que 0.");
        }
        
        if (monto>saldo){
            return false;
        }
        
        saldo-=monto;
        registroHistorial("Retiro: "+monto+ " "+moneda+ "saldo: "+saldo);
        return true;
    }
    
    public void registrarIntereses (){
        double int_g;
        
        int_g=(saldo * tipo.tasa());
        saldo+=int_g;
        
        if (int_g>0){
            registroHistorial("Interes: "+int_g+ " "+moneda+ "saldo: "+saldo);
        }
    }
    
    @Override
    public String toString (){
        return "Numero: "+numero+"\nCliente: "+cliente+"\nSaldo: "+saldo+"\nTipo de Cuenta: "+tipo+"/nApertura: "+apertura.getTime();
    }
    
    protected void registroHistorial(String index){
        try {
            if (CantidadMovimentos>=historial.length){
                throw new IndexOutOfBoundsException("Historial se encuentra lleno.");
            }
            
            historial[CantidadMovimentos]=index;
            
            CantidadMovimentos++;
            
        } catch(IndexOutOfBoundsException e){
            System.out.println("El historial se ha llenado"+e.getMessage());
        }
    }
    
    public void ImprimirHistorial(){
        if(CantidadMovimentos==0){
            System.out.println("Sin movimientos de cuenta registrados");
            return;
        }
        
        for (int i = 0; i < CantidadMovimentos; i++) {
            System.out.println(i+1+" "+historial[i]);
        }
        
    }
}
