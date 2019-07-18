package modelo;

public class Venta {

    private String IDVEN;
    private String TOTVEN ;
    private int IDCLI;
    private String IDEMP;
    private String ESTVEN;

    public String getIDVEN() {
        return IDVEN;
    }

    public void setIDVEN(String IDVEN) {
        this.IDVEN = IDVEN;
    }

    public String getTOTVEN() {
        return TOTVEN;
    }

    public void setTOTVEN(String TOTVEN) {
        this.TOTVEN = TOTVEN;
    }

    public int getIDCLI() {
        return IDCLI;
    }

    public void setIDCLI(int IDCLI) {
        this.IDCLI = IDCLI;
    }

    public String getIDEMP() {
        return IDEMP;
    }

    public void setIDEMP(String IDEMP) {
        this.IDEMP = IDEMP;
    }

    public String getESTVEN() {
        return ESTVEN;
    }

    public void setESTVEN(String ESTVEN) {
        this.ESTVEN = ESTVEN;
    }
}