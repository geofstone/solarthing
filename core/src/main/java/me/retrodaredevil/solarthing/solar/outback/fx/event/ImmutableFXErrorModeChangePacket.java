package me.retrodaredevil.solarthing.solar.outback.fx.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.retrodaredevil.solarthing.packets.identification.DefaultSupplementaryIdentifier;
import me.retrodaredevil.solarthing.packets.identification.IdentityInfo;
import me.retrodaredevil.solarthing.packets.identification.SupplementaryIdentifier;
import me.retrodaredevil.solarthing.solar.event.SolarEventPacketType;
import me.retrodaredevil.solarthing.solar.outback.OutbackIdentifier;
import me.retrodaredevil.solarthing.solar.outback.fx.FXIdentityInfo;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;

public class ImmutableFXErrorModeChangePacket implements FXErrorModeChangePacket {

	private final int address;
	private final int errorModeValue;
	private final Integer previousErrorModeValue;
	private final SupplementaryIdentifier identifier;
	private final IdentityInfo identityInfo;
	@JsonCreator
	private ImmutableFXErrorModeChangePacket(
			@JsonProperty(value = "address", required = true) int address,
			@JsonProperty(value = "errorModeValue", required = true) int errorModeValue,
			@JsonProperty(value = "previousErrorModeValue", required = true) Integer previousErrorModeValue
	) {
		this(new OutbackIdentifier(address), errorModeValue, previousErrorModeValue);
	}

	public ImmutableFXErrorModeChangePacket(OutbackIdentifier outbackIdentifier, int errorModeValue, Integer previousErrorModeValue) {
		this.errorModeValue = errorModeValue;
		this.previousErrorModeValue = previousErrorModeValue;

		address = outbackIdentifier.getAddress();
		identifier = new DefaultSupplementaryIdentifier<>(outbackIdentifier, SolarEventPacketType.FX_ERROR_MODE_CHANGE.toString());
		identityInfo = new FXIdentityInfo(address);
	}

	@Override
	public int getErrorModeValue() {
		return errorModeValue;
	}

	@Override
	public @Nullable Integer getPreviousErrorModeValue() {
		return previousErrorModeValue;
	}

	@NotNull
    @Override
	public SupplementaryIdentifier getIdentifier() {
		return identifier;
	}

	@NotNull
	@Override
	public IdentityInfo getIdentityInfo() {
		return identityInfo;
	}

	@Override
	public int getAddress() {
		return address;
	}
}
