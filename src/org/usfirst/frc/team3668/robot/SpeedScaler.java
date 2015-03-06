package org.usfirst.frc.team3668.robot;

public class SpeedScaler {
	public static double scaledSpeed(double speed, double scalingFactor){
//		double scaledSpeed = Math.pow(speed, Settings.universalSpeedScaleFactor);
//		boolean speedIsNegative = Math.signum(speed) == -1;
//		boolean scaledSpeedIsNegative = Math.signum(scaledSpeed) == -1;
		return (Math.abs(Math.pow(speed, scalingFactor)) * Math.signum(speed));
		
		
		//		
//		if(speedIsNegative && scaledSpeedIsNegative){
//		
//			return scaledSpeed;			
//		
//		} else if(speedIsNegative && !scaledSpeedIsNegative){
//		
//			return -scaledSpeed;
//		
//		} else {
//		
//			return scaledSpeed;
//		
//		}
	}
}
