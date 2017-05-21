package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	// TODO - RocketBLL RateException - RateDomainModel should be an attribute
	// of RateException
	// * Add RateRomainModel as an attribute
	// * Create a constructor, passing in RateDomainModel
	// * Create a getter (no setter, set value only in Constructor)

	private RateDomainModel inst;

	public RateException(RateDomainModel m) {
		super();
		inst = m;
	}

	public void printCustMsg(RateDomainModel m) {
		
		if (m.getiMinCreditScore() < 600) {
			System.out.println("The rate given, " +m.getiMinCreditScore()+", is less than the minimum possible: 600");
		} 
		
		else {
			System.out.println("The rate given, " +m.getiMinCreditScore()+", is greater than the max FICO: 850");
		}

	}

	public RateDomainModel getModelInstance() {
		return inst;
	}
}
