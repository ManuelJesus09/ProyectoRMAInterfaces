package adicional;

import adicional.RMAException;

/**
 *
 * @author Manuel Jesus Sanchez Vega
 */
public class Producto {

    private String nombre, problema;
    private int ean, numeroFactura;

    public Producto(String nombre, int ean, int numeroFactura, String problema) throws RMAException {

        setNombre(nombre);
        setEan(ean);
        setNumeroFactura(numeroFactura);
        setProblema(problema);
    }

    public void setNombre(String nombre) throws RMAException {
        if (nombre.equals("")) {
            throw new RMAException("Debes introducir un nombre.");
        }
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Producto con " + "nombre: " + nombre + ", problema: " + problema + ", ean: " + ean + ", numero de factura de compra: " + numeroFactura + '.';
    }

    public void setProblema(String problema) throws RMAException {
        if (problema.equals("")) {
            throw new RMAException("Debes introducir un problema.");
        }
        this.problema = problema;
    }

    public void setEan(int ean) throws RMAException {
        //Se compara con -1 ya que la referencia 0 puede existir, y si el usuario no ha introducido nada, el valor por defecto es -1
        if (ean==-1) {
            throw new RMAException("Debes introducir un codigo del producto(EAN).");
        }
        this.ean = ean;
    }

    public void setNumeroFactura(int numeroFactura) throws RMAException {
        if (numeroFactura==-1) {
            throw new RMAException("Debes introducir un numero de Factura.");
        }
        this.numeroFactura = numeroFactura;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProblema() {
        return problema;
    }

    public int getEan() {
        return ean;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.ean;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (this.ean != other.ean) {
            return false;
        }
        return true;
    }
    

}
