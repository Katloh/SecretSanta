public class EMail {

    private String eMailadress;
    private String donorName;
    private String gifteeName;
    private String subjectText;
    private String eMailText;


    public EMail(String eMailadress, String donorName, String gifteeName, String year) {
        this.eMailadress = eMailadress;
        this.donorName = donorName;
        this.gifteeName = gifteeName;
        subjectText = setSubjectText(donorName, year);
        eMailText = seteMailText(donorName, gifteeName);
    }

    public String geteMailadress() {
        return eMailadress;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getGifteeName() {
        return gifteeName;
    }

    public String geteMailText() {
        return eMailText;
    }

    private String setSubjectText(String donorName, String year) {
        String subjectText = "Hallo " + donorName + " - deine Wichtel-Los-Ziehung für Weihnachten " + year;
        return subjectText;
    }

    public String getSubjectText() {
        return subjectText;
    }

    private String seteMailText(String donorName, String gifteeName) {

        eMailText = "Hallo " + donorName + ",<br/><br/>"
                + " Der digitale automatische Wichtel-Algorithmus hat gelost.<br/><br/> " +
                "Du darfst zu Weihnachten folgende Person beschenken: " + gifteeName;
        return eMailText;
    }
}
