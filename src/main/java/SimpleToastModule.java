import jaci.openrio.toast.core.StateTracker;
import jaci.openrio.toast.core.loader.simulation.SimulationData;
import jaci.openrio.toast.lib.module.ToastModule;
import jaci.openrio.toast.lib.state.RobotState;
import jaci.openrio.toast.lib.state.StateListener;

/**
 * Created by Arnav Sankaran on 5/23/2015.
 */
public abstract class SimpleToastModule extends ToastModule implements StateListener.Ticker, StateListener.Transition{

    public abstract void robotInit();

    public abstract void autonomousInit();

    public abstract void autonomousPeriodic();

    public abstract void teleopInit();

    public abstract void teleopPeriodic();

    public abstract void disabledInit();

    public abstract void disabledPeriodic();

    public abstract void testInit();

    public abstract void testPeriodic();

    public void prestart() {
        StateTracker.addTicker(this);
        StateTracker.addTransition(this);
    }

    public void start() {
        robotInit();
    }

    boolean autonomousInitAlreadyRun;
    boolean teleopInitAlreadyRun;
    boolean disabledInitAlreadyRun;
    boolean testInitAlreadyRun;
    public void tickState(RobotState state) {
        if (state == RobotState.AUTONOMOUS) {
            if (!autonomousInitAlreadyRun) {
                autonomousInit();
                autonomousInitAlreadyRun = true;
            }
            autonomousPeriodic();
            SimulationData.currentState = RobotState.TELEOP;
        } else if(state == RobotState.TELEOP) {
            if (!teleopInitAlreadyRun) {
                teleopInit();
                teleopInitAlreadyRun = true;
            }
            teleopPeriodic();
        } else if(state == RobotState.DISABLED) {
            if (!disabledInitAlreadyRun) {
                disabledInit();
                disabledInitAlreadyRun = true;
            }
            disabledPeriodic();
        } else if (state == RobotState.TEST) {
            if (!testInitAlreadyRun) {
                testInit();
                testInitAlreadyRun = true;
            }
            testPeriodic();
        }
    }

    public void transitionState(RobotState newState, RobotState oldState) {
        if(oldState == RobotState.AUTONOMOUS) {
            autonomousInitAlreadyRun = false;
        } else if (oldState == RobotState.TELEOP) {
            teleopInitAlreadyRun = false;
        } else if (oldState == RobotState.DISABLED) {
            disabledInitAlreadyRun = false;
        } else if (oldState == RobotState.TEST) {
            testInitAlreadyRun = false;
        }
    }

    public  String getModuleVersion() {
        return "0.0.1";
    }

    public String getModuleName() {
        return "Simple Toast Module";
    }
}
