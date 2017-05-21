package rocketBase;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketData.LoanRequest;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	// I'm not sure why a RateDomainModel is related to an exception? Has int parameter.
	// I tried to construct the exception as such, so it will print the minimum possible credit score to be valid
	// Also, by the design of this project, it seems my conditions guarantee the rate will be in the correct range
	// Since I'm assuming between each minimum is a range of scores that share the same interest rate
	
	public static RateDomainModel createModel(int creditScore){
		RateDomainModel m = new RateDomainModel();
		m.setiMinCreditScore(creditScore);
		return m;
	}
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		double dInterestRate = 0;
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		try{
			if (GivenCreditScore>850)
				throw new RateException(createModel(GivenCreditScore));
			for (int i=0; i<rates.size();i++)
			{
				if (GivenCreditScore<rates.get(i).getiMinCreditScore())
					if (i==0)
						throw new RateException(createModel(GivenCreditScore));
					else if (GivenCreditScore>=rates.get(i-1).getiMinCreditScore())
					{
						dInterestRate = rates.get(i-1).getdInterestRate();
						break;
						}
				dInterestRate=rates.get(rates.size()-1).getdInterestRate();
			}
		}
		catch(RateException e){
			if (GivenCreditScore<600)
				e.printCustMsg(createModel(GivenCreditScore));
			else
				e.printCustMsg(createModel(GivenCreditScore));
		}
		
		
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		
		

		//TODO: Filter the ArrayList...  look for the correct rate for the given credit score.
		//	Easiest way is to apply a filter using a Lambda function.
		//
		//	Example... how to use Lambda functions:
		//			https://github.com/CISC181/Lambda
		
		
		return dInterestRate;
		
		
	}
	
	
	
	
	
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
	
	public static double getPITI(LoanRequest lq){
		double PITI1 = (lq.getIncome()*0.28);
		double PITI2 = (lq.getIncome()*0.36-lq.getExpenses());
		return Math.min(PITI1, PITI2);
	}

}
