package Helper;

public class DersItem {
private int id;
private int ders_id;
private String ders_name;
private String akts;
private String kredi;
private String kesinleþtirme;
private int useid;
private int sýnýf_id;
private String öðretmen_name;
private String gönderilme_durum;
DBConnection conn = new DBConnection();

public DersItem(int id, int ders_id,String ders_name,String kredi,String akts, int useid, int sýnýf_id,String öðretmen_name,String kesinleþtirme ,String gönderilme_durum) {
	super();
	this.id = id;
	this.ders_id = ders_id;
	this.useid = useid;
	this.sýnýf_id = sýnýf_id;
	this.öðretmen_name = öðretmen_name;
	this.ders_name = ders_name;
	this.akts = akts;
	this.kredi = kredi;
	this.kesinleþtirme = kesinleþtirme;
	this.gönderilme_durum=gönderilme_durum;
}


public String getGönderilme_durum() {
	return gönderilme_durum;
}


public void setGönderilme_durum(String gönderilme_durum) {
	this.gönderilme_durum = gönderilme_durum;
}


public String getÖðretmen_name() {
	return öðretmen_name;
}

public void setÖðretmen_name(String öðretmen_name) {
	this.öðretmen_name = öðretmen_name;
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
public int getSýnýf_id() {
	return sýnýf_id;
}
public void setSýnýf_id(int sýnýf_id) {
	this.sýnýf_id = sýnýf_id;
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

public String getKesinleþtirme() {
	return kesinleþtirme;
}

public void setKesinleþtirme(String kesinleþtirme) {
	this.kesinleþtirme = kesinleþtirme;
}

}
