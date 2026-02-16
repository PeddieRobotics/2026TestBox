package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.servohub.ServoHub;
import com.revrobotics.servohub.ServoChannel.ChannelId;
import com.revrobotics.servohub.ServoChannel;
import com.revrobotics.ResetMode;
import com.revrobotics.servohub.config.ServoHubConfig;

public class Servo extends SubsystemBase{

    private ServoHubConfig config;
    private ServoHub servoHub;
    private ServoChannel channel0, channel1, channel2, channel3, channel4, channel5;
    public static Servo servo;

    public Servo() {
        servoHub = new ServoHub(???);

        // fill in this and do it for the rest of the channels!
        channel0 = servoHub.getServoChannel(???);


        // fill in this and do it for the rest of the channels!
        channel0.setPowered(true);
        channel0.setEnabled(true);

        config = new ServoHubConfig();
        config.channel0.pulseRange(???,???,???);


        servoHub.configure(config, ResetMode.kResetSafeParameters).toString();

        servoHub.setBankPulsePeriod(???, ???).toString();
        servoHub.setBankPulsePeriod(???, ???).toString();

        // use this after servos are set up
        // SmartDashboard.putNumber("b2 pulse width", 1500);
        // SmartDashboard.putNumber("b3 pulse width", 1500);

    }

    public static Servo getInstance() {
        if (servo == null)
          servo = new Servo();
        return servo;
    }

    @Override
    public void periodic() {
        // channel2.setPulseWidth((int)SmartDashboard.getNumber("b2 pulse width", ???));
        // channel3.setPulseWidth((int)SmartDashboard.getNumber("b3 pulse width", ???));
    }
}
