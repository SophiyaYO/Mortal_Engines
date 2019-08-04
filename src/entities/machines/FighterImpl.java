package entities.machines;

import entities.interfaces.Fighter;
import entities.interfaces.Pilot;

import java.util.List;

public class FighterImpl extends BaseMachine implements Fighter {
    private static final double INITIAL_HEALTH_POINTS = 200d;
    private static final double ATTACK_POINTS_MODIFIER = 50d;
    private static final double DEFENCE_POINTS_MODIFIER = 25d;

    private String name;
    private boolean aggressiveMode;
    private double attackPointsModifier;
    private double deffencePointsModifier;

    public FighterImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, INITIAL_HEALTH_POINTS);
        this.aggressiveMode = true;
        this.attackPointsModifier = ATTACK_POINTS_MODIFIER;
        this.deffencePointsModifier = DEFENCE_POINTS_MODIFIER;
    }


    @Override
    public boolean getAggressiveMode() {
        return this.aggressiveMode;
    }

    public void setAggressiveMode(boolean aggressiveMode) {
        this.aggressiveMode = aggressiveMode;
    }

    @Override
    public void toggleAggressiveMode() {
        if (this.getAggressiveMode()) {
            this.setAggressiveMode(false);
            super.setAttackPoints(getAttackPoints() - this.attackPointsModifier);
            super.setDefensePoints(getDefensePoints() + this.deffencePointsModifier);

        } else {
            this.setAggressiveMode(true);
            super.setAttackPoints(getAttackPoints() + this.attackPointsModifier);
            super.setDefensePoints(getDefensePoints() - this.deffencePointsModifier);

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
                .append("*Type: Fighter")
                .append(System.lineSeparator())
                .append(super.toString())
                .append(System.lineSeparator())
                .append(String.format("%s",
                        this.aggressiveMode
                                ? "ON"
                                : "OFF"))
                .append(System.lineSeparator())

        ;


        return super.toString();
    }
}
