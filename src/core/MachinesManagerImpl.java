package core;

import common.OutputMessages;
import common.interfaces.OutputWriter;
import core.factories.MachineFactoryImpl;
import core.factories.PilotFactoryImpl;
import core.interfaces.MachineFactory;
import core.interfaces.MachinesManager;
import core.interfaces.PilotFactory;
import entities.interfaces.Fighter;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;
import entities.interfaces.Tank;
import entities.machines.FighterImpl;
import entities.machines.TankImpl;
import entities.pilots.PilotImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class MachinesManagerImpl implements MachinesManager {
    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;


    public MachinesManagerImpl(PilotFactory pilotFactory,
                               MachineFactory machineFactory,
                               Map<String, Pilot> pilots,
                               Map<String, Machine> machines) {
        this.pilotFactory = pilotFactory;
        this.machineFactory = machineFactory;
        this.pilots = new LinkedHashMap<>();
        this.machines = new LinkedHashMap<>();

    }

    @Override
    public String hirePilot(String name) {
        PilotFactory pilotFactory = new PilotFactoryImpl();
        Pilot pilot = pilotFactory.createPilot(name);

        if (pilots.containsKey(name)) {
            return String.format(OutputMessages.pilotExists, name);
        }

        pilots.put(name, pilot);

        return String.format(OutputMessages.pilotHired, pilot.getName());
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        MachineFactory machineFactory = new MachineFactoryImpl();
        Tank tank = machineFactory.createTank(name, attackPoints, defensePoints);

        String output ;

        if (!machines.containsKey(name)) {
            machines.putIfAbsent(name, tank);

            output = String.format(OutputMessages.tankManufactured,
                    tank.getName(),
                    tank.getAttackPoints(),
                    tank.getDefensePoints());
        } else {
            output = String.format(OutputMessages.machineExists, tank.getName());
        }

        return output;
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        MachineFactory machineFactory = new MachineFactoryImpl();
        Fighter fighter = machineFactory.createFighter(name, attackPoints, defensePoints);

        String output ;

        if (!machines.containsKey(name)) {
            machines.putIfAbsent(name, fighter);

            output = String.format(OutputMessages.fighterManufactured,
                    fighter.getName(),
                    fighter.getAttackPoints(),
                    fighter.getDefensePoints(),
                    fighter.getAggressiveMode() ? "ON" : "OFF");
        } else {
            output = String.format(OutputMessages.machineExists, fighter.getName());
        }

        return output;
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {
            pilots.get(selectedPilotName).addMachine(machines.get(selectedMachineName));
            machines.get(selectedMachineName).setPilot(pilots.get(selectedPilotName));

        return String.format(OutputMessages.machineEngaged,
                pilots.get(selectedPilotName).getName(),
                machines.get(selectedMachineName).getName());
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {
        String output;

        if (machines.containsKey(attackingMachineName) && machines.containsKey(defendingMachineName)) {

            machines.get(attackingMachineName).attack(defendingMachineName);

            if (machines.get(attackingMachineName).getAttackPoints() >
                    machines.get(defendingMachineName).getDefensePoints()) {

                double defendingMachineHealth = machines.get(defendingMachineName).getHealthPoints();
                double attackingMachineAttackPoints = machines.get(attackingMachineName).getAttackPoints();

                double attackResult = defendingMachineHealth - attackingMachineAttackPoints;

                if (attackResult <= 0) {
                    machines.get(defendingMachineName).setHealthPoints(0d);

                }

                output = String.format(OutputMessages.attackSuccessful,
                        defendingMachineName,
                        attackingMachineName,
                        machines.get(defendingMachineName).getHealthPoints());
            }

        } else {

            if (!machines.containsKey(attackingMachineName)){
                output = String.format(OutputMessages.machineNotFound,
                        attackingMachineName);
            } else {
                output = String.format(OutputMessages.machineNotFound,
                        defendingMachineName);
            }
        }

        return null;
    }

    @Override
    public String pilotReport(String pilotName) {
        if (!pilots.containsKey(pilotName)) {
            return String.format(OutputMessages.pilotNotFound, pilotName);
        }

        return pilots.get(pilotName).report();

    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {
        if (machines.containsKey(fighterName)) {
            ( (Fighter) machines.get(fighterName)).toggleAggressiveMode();

        }
        return String.format(OutputMessages.fighterOperationSuccessful,
                fighterName);
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {
        if (machines.containsKey(tankName)) {
            ((Tank) machines.get(tankName)).toggleDefenseMode();
        }

        return String.format(OutputMessages.tankOperationSuccessful,
                tankName);
    }
}
