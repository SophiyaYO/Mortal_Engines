package entities.machines;

import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMachine implements Machine {
    private String name;
    private Pilot pilot;
    private double attackPoints;
    private double defensePoints;
    private double healthPoints;
    private List<String> targets;

    protected BaseMachine(String name, double attackPoints,
                          double defensePoints, double healthPoints) {
        this.setName(name);
        this.setAttackPoints(attackPoints);
        this.setDefensePoints(defensePoints);
        this.setHealthPoints(healthPoints);
        targets = new ArrayList<>();
    }


    @Override
    public void setName(String name) {
        if (name == null || name.equals("Machine name cannot be null or empty.")) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
        }
    }

    public void setAttackPoints(double attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setDefensePoints(double defensePoints) {
        this.defensePoints = defensePoints;
    }

    @Override
    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    @Override
    public void setPilot(Pilot pilot) {
        if (pilot == null) {
            throw new NullPointerException("Pilot cannot be null.");
        } else {
            this.pilot = pilot;
        }
    }

    @Override
    public Pilot getPilot() {
        return this.pilot;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public double getDefensePoints() {
        return this.defensePoints;
    }

    @Override
    public double getAttackPoints() {
        return this.attackPoints;
    }

    @Override
    public List<String> getTargets() {
        return this.targets;
    }


    @Override
    public void attack(String target) {
        if (target == null || target.equals("")) {
            throw new IllegalArgumentException("Attack target cannot be null or empty string.");
        } else {
            this.targets.add(target);
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder
                .append(String.format("*Health: %.2f", this.getHealthPoints()))
                .append(System.lineSeparator())
                .append(String.format("*Attack: %.2f", this.getHealthPoints()))
                .append(System.lineSeparator())
                .append(String.format("*Defense: %.2f", this.getDefensePoints()))
                .append(System.lineSeparator())
                .append(String.format("*Targets: %s",
                        this.getTargets().size() != 0 ?
                                String.join(", ", this.getTargets()) :
                                "None"))
                .append(System.lineSeparator());

        return builder.toString().trim();
    }
}
