package Helper;

public class DersItem {
private int id;
private int ders_id;
private String ders_name;
private String akts;
private String kredi;
private String kesinle�tirme;
private int useid;
private int s�n�f_id;
private String ��retmen_name;
private String g�nderilme_durum;
DBConnection conn = new DBConnection();

public DersItem(int id, int ders_id,String ders_name,String kredi,String akts, int useid, int s�n�f_id,String ��retmen_name,String kesinle�tirme ,String g�nderilme_durum) {
	super();
	this.id = id;
	this.ders_id = ders_id;
	this.useid = useid;
	this.s�n�f_id = s�n�f_id;
	this.��retmen_name = ��retmen_name;
	this.ders_name = ders_name;
	this.akts = akts;
	this.kredi = kredi;
	this.kesinle�tirme = kesinle�tirme;
	this.g�nderilme_durum=g�nderilme_durum;
}


public String getG�nderilme_durum() {
	return g�nderilme_durum;
}


public void setG�nderilme_durum(String g�nderilme_durum) {
	this.g�nderilme_durum = g�nderilme_durum;
}


public String get��retmen_name() {
	return ��retmen_name;
}

public void set��retmen_name(String ��retmen_name) {
	this.��retmen_name = ��retmen_name;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getDers_id() {
	return ders_id;
}
public void setDers_id(int ders_id) {
	this.ders_id = ders_id;
}
public int getUseid() {
	return useid;
}
public void setUseid(int useid) {
	this.useid = useid;
}
public int getS�n�f_id() {
	return s�n�f_id;
}
public void setS�n�f_id(int s�n�f_id) {
	this.s�n�f_id = s�n�f_id;
}
public String getDers_name() {
	return ders_name;
}

public void setDers_name(String ders_name) {
	this.ders_name = ders_name;
}

public String getAkts() {
	return akts;
}

public void setAkts(String akts) {
	this.akts = akts;
}

public String getKredi() {
	return kredi;
}

public void setKredi(String kredi) {
	this.kredi = kredi;
}

public String getKesinle�tirme() {
	return kesinle�tirme;
}

public void setKesinle�tirme(String kesinle�tirme) {
	this.kesinle�tirme = kesinle�tirme;
}

}
