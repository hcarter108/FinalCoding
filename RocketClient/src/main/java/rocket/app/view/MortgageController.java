package rocket.app.view;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;
import rocketServer.RocketHub;

public class MortgageController {

	@FXML TextField txtCreditScore;
	@FXML TextField txtIncome;
	@FXML TextField txtExpenses;
	@FXML TextField txtHouseCost;
	@FXML TextField txtDownPayment;
	@FXML TextField txtMortgageAmt;
	@FXML ObservableList<String> options = FXCollections.observableArrayList("15 year", "30 year");
	@FXML ComboBox<String> cmbTerm = new ComboBox(options);
	@FXML Label lblMortgage;
	
	private TextField txtNew;
	private MainApp mainApp;
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	/**I really wasn't sure how the program works here. If btnCalculate only did what it was 
	 * originally written to, I wasn't sure how an instance of loan request would be sent to the server.
	 * I understand it should go client->server message received->back to client as handleLoanRequestDetails, but
	 * as written it wasn't doing anything so I gave it my best shot as to what made sense to me
	 * so I did half the stuff that was said to be done in Message Received in handleloanrequestdetails
	 * instead.
	 */
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message Here...");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText(txtNew.getText());
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		        System.out.println("Pressed OK.");
		    }
		});
		
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest) throws RateException
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		lRequest.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lRequest.setIncome(Double.parseDouble(txtIncome.getText()));
		lRequest.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lRequest.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		lRequest.setiTerm(Integer.parseInt(cmbTerm.getValue()));
		double rate = RateBLL.getRate(lRequest.getiCreditScore());
		double loanAmount = Double.parseDouble(txtHouseCost.getText())-lRequest.getiDownPayment();
		lRequest.setdPayment(RateBLL.getPayment(rate, lRequest.getiTerm(), loanAmount, 0, false));
		double PITI = RateBLL.getPITI(lRequest);
		if (PITI<lRequest.getdPayment())
			lblMortgage.setText("House cost too high");
		else
			lblMortgage.setText(String.format("%02d", lRequest.getdPayment()));

	}
}
