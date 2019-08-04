package core;

import core.interfaces.PilotFactory;
import entities.interfaces.Pilot;
import entities.PilotImpl;

public class PilotFactoryImpl implements PilotFactory {
    @Override
    public Pilot createPilot(String name) {
        return new PilotImpl(name);
    }
}
