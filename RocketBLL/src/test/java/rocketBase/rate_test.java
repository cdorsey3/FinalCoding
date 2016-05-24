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
	@Test
	public void rateTest() throws RateException{
		double rate = RateBLL.getRate(800);
		equals(rate == 5);
	}
	@Test(expected = RateException.class) 
	public void rateExeptionTest() throws RateException{
		double rate = RateBLL.getRate(0);
	}
	@Test
	public void test() {
		assert(1==1);
	}

}
