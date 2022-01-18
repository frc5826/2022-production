package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.LidarLitePWM;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;

public class SensorSubsystem {

    final LidarLitePWM lidar;
    final AnalogInput sonar;
    final Rev2mDistanceSensor dist2m;

    boolean targetRed;

    public SensorSubsystem(int lidarIn, int sonarIn, boolean targetRed){
        this.lidar = new LidarLitePWM(new DigitalInput(lidarIn));
        this.sonar = new AnalogInput(sonarIn);
        this.dist2m = new Rev2mDistanceSensor(Port.kOnboard);
        this.targetRed = targetRed;
    }



    public double getDistanceLidar() {
        return 0;
    }

    public double getDistanceSonar(){
        return 0;
    }

    public double getDistance2m(){
        return 0;
    }

    public boolean isBall() { return false; }

    public double getBallYaw() { return 0; }

    public boolean isBlue() { return !this.targetRed; }

    public boolean isRed() { return this.targetRed; }

    public double getBallDistance() { return 0; }

    public void setBlue() {
        this.targetRed = false;
    }

    public void setRed() {
        this.targetRed = true;
    }
}
