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
public class CuentaCheques extends CuentaBancaria{
    private double limite;

    public CuentaCheques(int numero, double saldo, String cliente, String moneda, Calendar apertura, TipoCuenta tipo) {
        super(numero, saldo, cliente, moneda, apertura, tipo);
        this.limite=limite;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        if (monto > (saldo + limite)) {
            return false;
        }
        saldo -= monto;
        registroHistorial("Retiro: "+monto+ " "+moneda+ "saldo: "+saldo);
        return true;
    }
    
    public void giroCheque(double monto){
        if (monto>(saldo + limite)){
            throw new IllegalStateException ("Fondos insuficientes para el giro");
        }
        retirar(monto);
    }
    
    @Override
    public String toString() {
        double disponible =saldo<0 ? limite+saldo: limite;
        return super.toString() + "\nSobregiro disp: " + disponible;
    }
}
