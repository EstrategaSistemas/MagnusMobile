package com.salemobile.essis.magnus.EventsFragments.Providers;

/**
 * Created by Adan on 14/12/2015.
 */
import com.squareup.otto.Bus;

public final class BusProvider {
    private static final Bus BUS = new Bus();
    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}