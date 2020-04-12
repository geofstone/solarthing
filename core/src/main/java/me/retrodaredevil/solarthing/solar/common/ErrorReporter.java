package me.retrodaredevil.solarthing.solar.common;

import me.retrodaredevil.solarthing.packets.BitmaskMode;
import me.retrodaredevil.solarthing.packets.identification.Identifiable;

import java.util.Collection;

public interface ErrorReporter extends Identifiable {
	/**
	 * Although this is usually serialized, there is no standard key for serialization. "errorMode" and "errorModeValue" are commonly used.
	 * @return The value of the error mode.
	 */
	int getErrorModeValue();
	Collection<? extends BitmaskMode> getErrorModes();
}
