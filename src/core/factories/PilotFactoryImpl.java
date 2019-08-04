package core.factories;

import core.interfaces.PilotFactory;
import entities.interfaces.Pilot;
import entities.pilots.PilotImpl;

public class PilotFactoryImpl implements PilotFactory {
    @Override
    public Pilot createPilot(String name) {
        return new PilotImpl(name);
    }
}
