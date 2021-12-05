
public class Sellers extends Users {
	   public void login() {
			SellerLogin loginFrame;
			try {
				loginFrame = new SellerLogin(this);
				loginFrame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    public void register() {
	    SellerRegister regFrame;
		try {
			regFrame = new SellerRegister(this);
			regFrame.setVisible(true);
		
		} catch (Exception e1) {
			e1.printStackTrace();
		
	}
	    }
}
