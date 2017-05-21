package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {
	
	//The other tests mentioned in the todo's here are the same as RateBLL, so I wrote those there
	// and checked just the ordering and the list size

	@Test
	public void testgetRates(){
		List<RateDomainModel> l = RateDAL.getAllRates();
		assertEquals(l.size(),5);
		assertEquals(l.get(0).getiMinCreditScore(),600);
	}
	//TODO - RocketDAL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketDAL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score


}
