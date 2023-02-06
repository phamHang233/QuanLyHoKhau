package models;

public class ChoThueNVHModel extends SuDungNVHModel{
    private int phi;
    private String nguoiThue;

    public void setPhi(int phiDV) {
        this.phi= phiDV;
    }

    public void setNguoiThue(String nguoiThue) {
        this.nguoiThue = nguoiThue;
    }

    public int getPhi() {
        return phi;
    }

    public String getNguoiThue() {
        return nguoiThue;
    }


}
