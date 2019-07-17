package modelo;

import java.util.Date;

public class Cliente {

    private int IDCLI;
    private String NOMCLI;
    private String APECLI;
    private Date NACCLI;
    private String SEXCLI;
    private String ESTCLI;

    public int getIDCLI() {
        return IDCLI;
    }

    public void setIDCLI(int IDCLI) {
        this.IDCLI = IDCLI;
    }

    public String getNOMCLI() {
        return NOMCLI;
    }

    public void setNOMCLI(String NOMCLI) {
        this.NOMCLI = NOMCLI;
    }

    public String getAPECLI() {
        return APECLI;
    }

    public void setAPECLI(String APECLI) {
        this.APECLI = APECLI;
    }

    public Date getNACCLI() {
        return NACCLI;
    }

    public void setNACCLI(Date NACCLI) {
        this.NACCLI = NACCLI;
    }

    public String getSEXCLI() {
        return SEXCLI;
    }

    public void setSEXCLI(String SEXCLI) {
        this.SEXCLI = SEXCLI;
    }

    public String getESTCLI() {
        return ESTCLI;
    }

    public void setESTCLI(String ESTCLI) {
        this.ESTCLI = ESTCLI;
    }

}
