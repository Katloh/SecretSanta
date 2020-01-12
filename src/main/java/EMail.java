public class EMail {

    private String eMailadress;
    private String donorName;
    private String gifteeName;
    private String eMailText;


    public EMail(String eMailadress, String donorName, String gifteeName) {
        this.eMailadress = eMailadress;
        this.donorName = donorName;
        this.gifteeName = gifteeName;
        eMailText = geteMailText(eMailadress, donorName, gifteeName);
    }

    public String geteMailadress() {
        return eMailadress;
    }

    public void seteMailadress(String eMailadress) {
        this.eMailadress = eMailadress;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getGifteeName() {
        return gifteeName;
    }

    public void setGifteeName(String gifteeName) {
        this.gifteeName = gifteeName;
    }

    public void seteMailText(String eMailText) {
        this.eMailText = eMailText;
    }

    public String getSubjectText(String donorName){
        String subjectText = "Hallo " + donorName + " - deine Wichtel-Los-Ziehung für Weihnachten 2019";
        return subjectText;
    }

    public String geteMailText(String eMailadress, String donorName, String gifteeName){

        String eMailText = "Hallo " + donorName + ",<br/><br/>"
                + " Der digitale automatische Wichtel-Algorithmus hat gelost.<br/><br/> " +
                "Du darfst zu Weihnachten folgende Person beschenken: " + gifteeName;

        return eMailText;
    }
}
