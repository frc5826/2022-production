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

    public SensorSubsystem(int lidarIn, int sonarIn){
        this.lidar = new LidarLitePWM(new DigitalInput(lidarIn));
        this.sonar = new AnalogInput(sonarIn);
        this.dist2m = new Rev2mDistanceSensor(Port.kOnboard);
    }

    //TODO return distance
    public double getDistance(){
        return 0;
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
}
