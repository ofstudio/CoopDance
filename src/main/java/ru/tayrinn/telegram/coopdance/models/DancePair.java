package ru.tayrinn.telegram.coopdance.models;

public class DancePair {
    private Dancer girl;
    private Dancer boy;

    public DancePair(Dancer girl, Dancer boy) {
        this.girl = girl;
        this.boy = boy;
    }

    public Dancer getGirl() {
        return girl;
    }

    public Dancer getBoy() {
        return boy;
    }

    public void setGirl(Dancer girl) {
        this.girl = girl;
    }

    public void setBoy(Dancer boy) {
        this.boy = boy;
    }

    @Override
    public String toString() {
        return boy + " - " + girl;
    }

    public boolean isRandomPair() {
        return girl.user != null && boy.user != null;
    }
}
