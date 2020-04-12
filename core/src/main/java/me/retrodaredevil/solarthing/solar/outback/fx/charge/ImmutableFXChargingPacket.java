package me.retrodaredevil.solarthing.solar.outback.fx.charge;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.retrodaredevil.solarthing.packets.identification.DefaultSupplementaryIdentifier;
import me.retrodaredevil.solarthing.packets.identification.SupplementaryIdentifier;
import me.retrodaredevil.solarthing.solar.extra.SolarExtraPacketType;
import me.retrodaredevil.solarthing.solar.outback.OutbackIdentifier;

import javax.validation.constraints.NotNull;

public class ImmutableFXChargingPacket implements FXChargingPacket {
	private final int masterFXAddress;

	private final FXChargingMode fxChargingMode;

	private final long remainingAbsorbTimeMillis;
	private final long remainingFloatTimeMillis;
	private final long remainingEqualizeTimeMillis;

	private final long totalAbsorbTimeMillis;
	private final long totalFloatTimeMillis;
	private final long totalEqualizeTimeMillis;

	private final SupplementaryIdentifier identifier;

	public ImmutableFXChargingPacket(
			OutbackIdentifier outbackIdentifier,
			FXChargingMode fxChargingMode,
			long remainingAbsorbTimeMillis,
			long remainingFloatTimeMillis,
			long remainingEqualizeTimeMillis,
			long totalAbsorbTimeMillis,
			long totalFloatTimeMillis,
			long totalEqualizeTimeMillis) {
		this.fxChargingMode = fxChargingMode;
		this.remainingAbsorbTimeMillis = remainingAbsorbTimeMillis;
		this.remainingFloatTimeMillis = remainingFloatTimeMillis;
		this.remainingEqualizeTimeMillis = remainingEqualizeTimeMillis;
		this.totalAbsorbTimeMillis = totalAbsorbTimeMillis;
		this.totalFloatTimeMillis = totalFloatTimeMillis;
		this.totalEqualizeTimeMillis = totalEqualizeTimeMillis;

		this.masterFXAddress = outbackIdentifier.getAddress();

		identifier = new DefaultSupplementaryIdentifier<>(outbackIdentifier, SolarExtraPacketType.FX_CHARGING.toString());
	}
	@JsonCreator
	private ImmutableFXChargingPacket(
			@JsonProperty(value = "masterFXAddress", required = true) int masterFXAddress,
			@JsonProperty(value = "fxChargingMode", required = true) FXChargingMode fxChargingMode,
			@JsonProperty(value = "remainingAbsorbTimeMillis", required = true) long remainingAbsorbTimeMillis,
			@JsonProperty(value = "remainingFloatTimeMillis", required = true) long remainingFloatTimeMillis,
			@JsonProperty(value = "remainingEqualizeTimeMillis", required = true) long remainingEqualizeTimeMillis,
			@JsonProperty(value = "totalAbsorbTimeMillis", required = true) long totalAbsorbTimeMillis,
			@JsonProperty(value = "totalFloatTimeMillis", required = true) long totalFloatTimeMillis,
			@JsonProperty(value = "totalEqualizeTimeMillis", required = true) long totalEqualizeTimeMillis) {
		this(new OutbackIdentifier(masterFXAddress), fxChargingMode, remainingAbsorbTimeMillis, remainingFloatTimeMillis, remainingEqualizeTimeMillis, totalAbsorbTimeMillis, totalFloatTimeMillis, totalEqualizeTimeMillis);
	}

	@NotNull
    @Override
	public SupplementaryIdentifier getIdentifier() {
		return identifier;
	}

	@Override
	public int getMasterFXAddress() {
		return masterFXAddress;
	}

	@Override
	public FXChargingMode getFXChargingMode() {
		return fxChargingMode;
	}

	@Override
	public long getRemainingAbsorbTimeMillis() {
		return remainingAbsorbTimeMillis;
	}

	@Override
	public long getRemainingFloatTimeMillis() {
		return remainingFloatTimeMillis;
	}

	@Override
	public long getRemainingEqualizeTimeMillis() {
		return remainingEqualizeTimeMillis;
	}

	@Override
	public long getTotalAbsorbTimeMillis() {
		return totalAbsorbTimeMillis;
	}

	@Override
	public long getTotalFloatTimeMillis() {
		return totalFloatTimeMillis;
	}

	@Override
	public long getTotalEqualizeTimeMillis() {
		return totalEqualizeTimeMillis;
	}
}
