
public class Customers extends Users {
    public void login() {
		CustomerLogin loginFrame;
		try {
			loginFrame = new CustomerLogin(this);
			loginFrame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void register() {
    CustomerRegister regFrame;
	try {
		regFrame = new CustomerRegister(this);
		regFrame.setVisible(true);
	} catch (Exception e1) {
		e1.printStackTrace();
	
}
    }
}
