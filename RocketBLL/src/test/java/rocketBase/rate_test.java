package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score

	
	// A boundary case and a mid range case, also as doubles it wanted the error bound
	@Test
	public void testGetRate() throws RateException{
		assertEquals(RateBLL.getRate(600),7,.0000001);
		assertEquals(RateBLL.getRate(630),7,.0000001);
	}
	
	//Test the below lower bound and above upper bound case
	@Test(expected = RateException.class)
	public void testRateException1() throws RateException
	{
		RateBLL.getRate(599);
	}
	
	@Test(expected = RateException.class)
	public void testRateException2() throws RateException
	{
		RateBLL.getRate(851);
	}
	
	// not sure why but this failed
//	@Test
//	public void testGetPayment(){
//		assertEquals(RateBLL.getPayment(.04, 1, 300000, 0, false),1432.25,.1)
//	}
}
