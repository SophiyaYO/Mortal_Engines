package entities;

import entities.interfaces.Pilot;
import entities.interfaces.Tank;

import java.util.List;

public class TankImpl extends BaseMachine implements Tank {
    private static final double INITIAL_HEALTH_POINTS = 200d;
    private static final double ATTACK_POINTS_MODIFIER = 40d;
    private static final double DEFENCE_POINTS_MODIFIER = 30d;

    private boolean defenceMode;
    private double attackPointsModifier;
    private double deffencePointsModifier;

    public TankImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, INITIAL_HEALTH_POINTS);
        this.defenceMode = true;
        this.attackPointsModifier = ATTACK_POINTS_MODIFIER;
        this.deffencePointsModifier = DEFENCE_POINTS_MODIFIER;
    }

    public void setDefenceMode(boolean defenceMode) {
        this.defenceMode = defenceMode;
    }

    @Override
    public double getHealthPoints() {
        return super.getHealthPoints();
    }

    @Override
    public boolean getDefenseMode() {
        return this.defenceMode;
    }

    @Override
    public void toggleDefenseMode() {
        if (getDefenseMode()) {
            this.setDefenceMode(false);
            super.setAttackPoints(getAttackPoints() + this.attackPointsModifier);
            super.setDefensePoints(getDefensePoints() - this.deffencePointsModifier);

        } else {
            this.setDefenceMode(true);
            setAttackPoints(getAttackPoints() - this.attackPointsModifier);
            setDefensePoints(getDefensePoints() + this.deffencePointsModifier);
        }

    }

    @Override
    public Pilot getPilot() {
        return super.getPilot();
    }

    @Override
    public List<String> getTargets() {
        return super.getTargets();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder
                .append(String.format("- %s", this.getName()))
                .append(System.lineSeparator())
                .append("*Type: Tank")
                .append(System.lineSeparator())
                .append(super.toString())
                .append(System.lineSeparator())
                .append(String.format("*Defense Mode(%s)",
                        this.defenceMode
                                ? "ON"
                                : "OFF"))
                .append(System.lineSeparator());

        return builder.toString().trim();
    }
}
