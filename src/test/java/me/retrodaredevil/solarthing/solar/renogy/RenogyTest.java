package me.retrodaredevil.solarthing.solar.renogy;

import me.retrodaredevil.solarthing.solar.renogy.rover.ChargingMethod;
import me.retrodaredevil.solarthing.solar.renogy.rover.StreetLight;
import me.retrodaredevil.solarthing.solar.renogy.rover.special.MutableSpecialPowerControl_E02D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class RenogyTest {
	@Test
	void testStreetLight(){
		assertThrows(IllegalArgumentException.class, () -> StreetLight.OFF.isActive(256));
		
		assertTrue(StreetLight.OFF.isActive(0b01111111));
		assertTrue(StreetLight.OFF.isActive(0b01111011));
		assertTrue(StreetLight.OFF.isActive(0b00111011));
		assertTrue(StreetLight.OFF.isActive(0b00111010));
		
		assertTrue(StreetLight.ON.isActive(0b11111111));
		assertTrue(StreetLight.ON.isActive(0b11111011));
		assertTrue(StreetLight.ON.isActive(0b10111011));
		assertTrue(StreetLight.ON.isActive(0b10111010));
		
		assertEquals(100, StreetLight.getBrightnessValue(100));
		assertEquals(100, StreetLight.getBrightnessValue(100 | (1 << 7)));
		assertEquals(49, StreetLight.getBrightnessValue(49));
		assertEquals(49, StreetLight.getBrightnessValue(49 | (1 << 7)));
	}
	@Test
	void testSpecialPower_E02D(){
		MutableSpecialPowerControl_E02D power = new MutableSpecialPowerControl_E02D();
		
		power.setIntelligentPowerEnabled(true);
		assertTrue(power.isIntelligentPowerEnabled());
		power.setIntelligentPowerEnabled(false);
		assertFalse(power.isIntelligentPowerEnabled());
		
		power.setEachNightOnEnabled(true);
		assertTrue(power.isEachNightOnEnabled());
		power.setEachNightOnEnabled(false);
		assertFalse(power.isEachNightOnEnabled());
		
		power.setIsLithiumBattery(true);
		assertTrue(power.isLithiumBattery());
		power.setIsLithiumBattery(false);
		assertFalse(power.isLithiumBattery());
		
		power.setChargingMethod(ChargingMethod.DIRECT);
		assertEquals(ChargingMethod.DIRECT, power.getChargingMethod().getChargingMethod());
		power.setChargingMethod(ChargingMethod.PWM);
		assertEquals(ChargingMethod.PWM, power.getChargingMethod().getChargingMethod());
		
		power.setNoChargingBelow0CEnabled(true);
		assertTrue(power.isNoChargingBelow0CEnabled());
		power.setNoChargingBelow0CEnabled(false);
		assertFalse(power.isNoChargingBelow0CEnabled());
		
		power.setIs24VSystem(true);
		assertTrue(power.is24VSystem());
		power.setIs24VSystem(false);
		assertFalse(power.is24VSystem());
		
	}
}