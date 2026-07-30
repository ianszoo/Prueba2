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
public class CuentaAhorro extends CuentaBancaria {
    
    private Calendar uso;

    public CuentaAhorro(int numero, double saldo, String cliente, String moneda, Calendar apertura, TipoCuenta tipo) {
        super(numero, saldo, cliente, moneda, apertura, tipo);
        this.uso= Calendar.getInstance();
    }
    
    private void verificarActiva(){
        Calendar limit= Calendar.getInstance();
        limit.add(Calendar.MONTH, 6);
        
        if (uso.before(limit)){
            throw new IllegalArgumentException(" Cuenta inactiva por no interaccion");
        }
    }
    @Override
    public void depositar(double monto){
        verificarActiva();
        super.depositar(monto);
        uso=Calendar.getInstance();
    }
    
    @Override
    public boolean retirar(double monto){
        verificarActiva();
        boolean perm=super.retirar(monto);
        
        if(perm){
            uso=Calendar.getInstance();
        }
        return perm;
    }
    
    @Override
    public String toString(){
        return super.toString()+"Ultimo Uso: "+uso.getTime();
    }
}
