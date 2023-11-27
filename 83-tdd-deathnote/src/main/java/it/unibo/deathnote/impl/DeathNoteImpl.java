package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote{
    private Map<String, String> namesAndCauses = new HashMap<>();
    private Map<String, String> namesAndDetails = new HashMap<>();


    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Rule index " + ruleNumber + " does not exist");
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        if (name == null) {
            throw new NullPointerException("name can not be blank");
        }
        namesAndCauses.put(name, "Heart attack");
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if (cause == null || namesAndCauses.isEmpty()) {
            throw new IllegalStateException("Deathnote is empty or you didn't specify a cause");
        }
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, String> entry : namesAndCauses.entrySet()) {
            entry.setValue(cause);
        }
        return true;
    }

    @Override
    public boolean writeDetails(String details) {
        if (namesAndCauses.isEmpty()) {
            throw new IllegalStateException("Deathnote is empty");
        }
        try {
            Thread.sleep(6040);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, String> entry : namesAndDetails.entrySet()) {
            entry.setValue(details);
        }
        return true;
    }

    @Override
    public String getDeathCause(String name) {
        if (!namesAndCauses.containsKey(name)) {
            throw new IllegalArgumentException(name + " is not written in Deathnote");
        }
        return namesAndCauses.get(name);
    }

    @Override
    public String getDeathDetails(String name) {
        if (!namesAndDetails.containsKey(name)) {
            throw new IllegalArgumentException(name + " is not written in Deathnote");
        }
        return namesAndDetails.get(name);
    }

    @Override
    public boolean isNameWritten(String name) {
        return namesAndCauses.containsKey(name);
    }
    
}
