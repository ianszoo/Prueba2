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
public class CuentaPlazo extends CuentaBancaria{
    private Calendar fin;
    private double interes;

    public CuentaPlazo(int numero, double saldo, String cliente, String moneda, Calendar apertura, TipoCuenta tipo) {
        super(numero, saldo, cliente, moneda, apertura, tipo);
    }
    
    public void setPlazo(int anyo,int mes, int dia){
        fin=Calendar.getInstance();
        fin.set(anyo,mes-1,dia);
    }
    
    private boolean estaVencida() {
        return Calendar.getInstance().after(fin);
    }
    
    
    @Override
    public void registrarIntereses() {
        double ganancia= saldo * tipo.tasa();
        if (!estaVencida()) {
            interes += ganancia;
            registroHistorial("INTERES ACUMULADO +" + ganancia);
        } else {
            saldo += ganancia;
            registroHistorial("INTERES SUMADO EN SALDO +" + ganancia);
        }
    }
    
    @Override
    public boolean retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("Monto invalido");
        }
        if (!estaVencida()) {
            if (monto > interes) {
                throw new IllegalStateException("No puede retirar saldo principal antes del vencimiento");
            }
            interes -= monto;
            registroHistorial("El retiro de interes" + monto);
            return true;
        }
        return super.retirar(monto);
    }
    
    public String toString() {
        return super.toString() + "\nVencimiento: " + fin.getTime() + "\nIntereses: " + interes;
    }
}
