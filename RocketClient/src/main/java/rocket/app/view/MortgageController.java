package rocket.app.view;

import java.io.IOException;

import org.apache.poi.ss.formula.functions.FinanceLib;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	@FXML
	private AnchorPane layout;
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private TextField txtDownPayment;
	@FXML
	ObservableList<String> terms = (ObservableList<String>) FXCollections.observableArrayList("15 Years","30 Years");
	@FXML
	final ComboBox<String> comboBox = new ComboBox<String>(terms);
	//private ComboBox loan_term;
	@FXML
	private Label lIncome;
	@FXML
	private Label lExpenses;
	@FXML
	private Label lCreditScore;
	@FXML
	private Label lHouseCost;
	@FXML
	private Label lTerm;
	@FXML
	private Button loanPayment;
	@FXML
	private Label lOutput;
	@FXML
	private Label lPiti;
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
		//showMainView();
	}
	/*private void showMainView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MortgageController.class.getResource("view/Mortgage.fxml"));
		layout = loader.load();
		Scene scene = new Scene(layout);
		mainApp.setScene(scene);
	}*/
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event) throws NumberFormatException, RateException
	{
		
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		lq.setdRate(RateBLL.getRate(Integer.parseInt(txtCreditScore.getText())));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		lq.setiTerm(Integer.parseInt(comboBox.getValue()));
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq

		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		double n = lRequest.getiTerm() * 12;
		double payment;
		double pv;
		double piti1 = lRequest.getIncome() * .28;
		double piti2 = (lRequest.getIncome() * .36) - lRequest.getExpenses();
		pv = lRequest.getdAmount() - lRequest.getiDownPayment();
		//double tAmt = lRequest.getdAmount()- lRequest.getiDownPayment();
		payment = FinanceLib.pmt(lRequest.getdRate(), n, pv, 0, true);
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		lOutput.setText(String.valueOf(payment));
		if (piti1 < piti2){
			lPiti.setText(String.valueOf(piti1));
		}
		else {
			lPiti.setText(String.valueOf(piti2));
		}
		
	}
	
}
