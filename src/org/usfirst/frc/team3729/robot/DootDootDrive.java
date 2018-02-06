package org.usfirst.frc.team3729.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DootDootDrive {
	WPI_TalonSRX RightMotor, LeftMotor;
	PlayStationController playStation;
	ADXRS450_Gyro gyro;
	// Victor SPOODERMAN;
	// Victor testMotor;
	// Encoder RightEncode, LeftEncode;

	public DootDootDrive(PlayStationController playStation) {

		playStation = new PlayStationController(0);
		this.playStation = playStation;
		RightMotor = new WPI_TalonSRX(0);
		LeftMotor = new WPI_TalonSRX(1);
		gyro = new ADXRS450_Gyro();
		// SPOODERMAN = new Victor(2);
		// ENCODERS DIO
		// VICTORS GO IN PWM

	}

	public void DootDootDoIt() {
		double RightTrigger = playStation.GetRightTrigger();
		double LeftTrigger = playStation.GetLeftTrigger();
		double LeftStick = playStation.GetLeftStickAxisX();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double Limiter = 0.5;
		double turn = 2 * LeftStick;
		Power = RightTrigger - LeftTrigger;
		// System.out.println("left"+LeftStick);
		if (LeftStick > Deadzone) {

			RightPower = Power - (turn * Power);
			LeftPower = Power;
		} else if (LeftStick < -Deadzone) {

			LeftPower = Power + (turn * Power);
			RightPower = Power;
		} else {
			LeftPower = Power;
			RightPower = Power;
		}

		LeftMotor.set(-LeftPower * Limiter);
		RightMotor.set(RightPower * Limiter);
	}

	public void gyro() {
		gyro.reset();
	}

	public void getGyro() {
		SmartDashboard.putNumber("G-Stat", gyro.getAngle());
	}

	public void voltageReader() {
		if (playStation.GetButtonX() == true) {
			System.out.println(LeftMotor.getBusVoltage());
			System.out.println(RightMotor.getBusVoltage());
		}
	}

	// public void Victor() {
	// if (playStation.GetButtonTriangle()) {
	// SPOODERMAN.set(1);
	// } else {
	// SPOODERMAN.set(0);
	// }
	//
	// }

	public void AutoGoForeward(double speed, int time) {

		LeftMotor.set(-speed);
		RightMotor.set(speed);
		try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LeftMotor.stopMotor();
		RightMotor.stopMotor();

	}

	public void AutoGoOverLine() {

		LeftMotor.set(-0.5);
		RightMotor.set(0.5);
		try {
			wait(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LeftMotor.stopMotor();
		RightMotor.stopMotor();

	}

	public void AutoGoRight(double speed, int time) {
		LeftMotor.set(-speed * 0.5);
		RightMotor.set(speed);
		try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LeftMotor.set(0.0);
		RightMotor.set(0);
	}

	public void AutoGoLeft(double speed, int time) {

		LeftMotor.set(-speed);
		RightMotor.set(speed * 0.5);
		try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LeftMotor.set(0.0);
		RightMotor.set(0);
	}

}
