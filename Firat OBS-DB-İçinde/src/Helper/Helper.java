package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonTex","Iptal");
		UIManager.put("OptionPane.noButtonTex","Hayýr");
		UIManager.put("OptionPane.okButtonTex","Tamam");
		UIManager.put("OptionPane.yesButtonTex","Evet");
	}
	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch(str) {
		case "fill":
			msg="Lütfen tüm alanlarý doldurunuz.";
			break;
		case"success":
			msg="Ýþlem Baþarýlý";
			break;
			default: 
				msg = str;
		}
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}
	public static boolean confirm(String str) {
		String msg = null;
		switch(str) {
		case "sure":
			msg="Bu iþlemi gerçekleþtirmek istiyor musun ?";
			break;
			
		}
		int res=JOptionPane.showConfirmDialog(null, msg,"Dikkat !",JOptionPane.YES_NO_OPTION);
				if(res==0) {
					return true;
				}else {
					return false;
				}
	}
}
