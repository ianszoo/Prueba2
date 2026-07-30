/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

/**
 *
 * @author Ian Suazo Palao
 */
public class TipoCuenta {
    
    private final String nombre;
    private final double tasa;
    
    public static final TipoCuenta AHORRO=new TipoCuenta("AHORRO",0.03);
    public static final TipoCuenta CHEQUES=new TipoCuenta("CHEQUES",0.00);
    public static final TipoCuenta PLAZO=new TipoCuenta("PLAZO",0.05);

    public TipoCuenta(String nombre, double tasa) {
        this.nombre = nombre;
        this.tasa = tasa;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTasa() {
        return tasa;
    }
    
    @Override
    
    public String toString(){
        return nombre;
    }
    
    
    
}
