package org.usfirst.frc.team3668.robot;

import edu.wpi.first.wpilibj.Talon;

public class OurTalon extends Talon {

	public OurTalon(int channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}

	public void ourSetBounds(double max, double deadbandmax, double center, double deadbandmin, double min){
		setBounds(max,deadbandmax,center,deadbandmin,min);
	}
}
