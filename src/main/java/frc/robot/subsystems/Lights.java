package frc.robot.subsystems;

import com.ctre.phoenix6.controls.SolidColor;
import com.ctre.phoenix6.hardware.CANdle;
import com.ctre.phoenix6.signals.RGBWColor;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lights extends SubsystemBase {
    public static Lights lights;
    private final CANdle candle;

    public Lights() {
        candle = new CANdle(5);
        SmartDashboard.putNumber("Light", 0);
    }

    public static Lights getInstance() {
        if (lights == null)
            lights = new Lights();
        return lights;

    }

    public void setColor(Color color) {
        candle.setControl(new SolidColor(0, 7).withColor(new RGBWColor(color)));
    }

    @Override
    public void periodic() {
        switch ((int) SmartDashboard.getNumber("Light", 0)) {
            case 0:
                setColor(Color.kRed);
                break;
            case 1:
                setColor(Color.kYellow);
                break;
            case 2:
                setColor(Color.kBlue);
                break;
            case 3:
                setColor(Color.kWhite);
                break;
            case 4:
                setColor(Color.kBlack);
                break;
        }
    }
}