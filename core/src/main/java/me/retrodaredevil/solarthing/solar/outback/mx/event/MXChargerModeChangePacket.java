package me.retrodaredevil.solarthing.solar.outback.mx.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.retrodaredevil.solarthing.annotations.JsonExplicit;
import me.retrodaredevil.solarthing.packets.Modes;
import me.retrodaredevil.solarthing.solar.event.SolarEventPacketType;
import me.retrodaredevil.solarthing.solar.event.SupplementarySolarEventPacket;
import me.retrodaredevil.solarthing.solar.outback.OutbackData;
import me.retrodaredevil.solarthing.solar.outback.mx.ChargerMode;
import org.jetbrains.annotations.Nullable;

@JsonExplicit
public interface MXChargerModeChangePacket extends SupplementarySolarEventPacket, OutbackData {
	@Override
	default SolarEventPacketType getPacketType(){
		return SolarEventPacketType.MXFM_CHARGER_MODE_CHANGE;
	}

	@JsonProperty("chargerMode")
	int getChargerModeValue();
	@JsonProperty("previousChargerMode")
	@Nullable Integer getPreviousChargerModeValue();

	default ChargerMode getChargingMode(){
		return Modes.getActiveMode(ChargerMode.class, getChargerModeValue());
	}
	default @Nullable ChargerMode getPreviousChargingMode(){
		Integer previousChargerModeValue = getPreviousChargerModeValue();
		if(previousChargerModeValue == null){
			return null;
		}
		return Modes.getActiveMode(ChargerMode.class, previousChargerModeValue);
	}
}
